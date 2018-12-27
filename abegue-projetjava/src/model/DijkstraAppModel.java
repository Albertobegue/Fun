package model;

import UI.BoxButton;
import dijkstra.Dijkstra;
import dijkstra.Pi;
import dijkstra.Previous;
import dijkstra.VertexInterface;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;
import java.util.Observable;

import javax.swing.JOptionPane;

import maze.MBox;
import maze.Maze;
import maze.MazeReadingException;

//Model for the UI. Methods are only getters and setters.
public final class DijkstraAppModel extends Observable{

	//Related to the maze itself
	private Maze maze;
	private MBox departureBox;
	private MBox arrivalBox;
	
	//Related to what is shown
	private MBox currentBox = null;
	private boolean beingEdited = false; //To know when we are changing the boxes in the graph.
	private boolean showTheWholePath = false;
	
	//Related to algorithms and save functions
	private boolean isModified = false; //Will only be modified if beingEdited becomes true, at least once.
	private ArrayList<Integer> boxesOfthePathIDs = new ArrayList<Integer>();
	private int boxesCount = 1; //To go through boxesOfthePathIDs.
	private Pi pi = null; //To get the pi-distances of each box.
	private String messageForValidPath = "You can close edition...";
	
	//Constructor
	public DijkstraAppModel() {
		int h = getDimensions("data/labyrinthe.txt").get(1);
		int w = getDimensions("data/labyrinthe.txt").get(0);
		this.maze = new Maze(h,w);
		maze.initFromTextFile("data/labyrinthe.txt");
		int n = maze.getAllVertices().size();
		for(int i = 0 ; i<n; i++) {
			if(maze.getBoxWithId(i).getLabel() == "D")
				this.departureBox = maze.getBoxWithId(i);
			if(maze.getBoxWithId(i).getLabel() == "A")
				this.arrivalBox = maze.getBoxWithId(i);
		}
		this.currentBox = departureBox;
	}
	
	//Methods used in the constructor
	public final ArrayList<Integer> getDimensions(String fileName) { //Returns <width, height>
		
		ArrayList<Integer> dimensions = new ArrayList<Integer>();
		int lineNumber = 0;
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileName));
			String currentLine = in.readLine();
			dimensions.add(currentLine.length());
			ArrayList<MBox> newLine = new ArrayList<MBox>();
			while(currentLine != null) {
				lineNumber++;
				currentLine = in.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File has not been found");
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("Problem during the opening.");
		} finally {
			try { in.close(); } catch(Exception e){
				e.printStackTrace();
				System.out.println("Error when we tried to close the file.");
			}
		}
		
		dimensions.add(lineNumber);
		return dimensions;
	}
	
	// -------------------------------------------------------- Getters and setters ------------------------------------------------------------------------------------- //
	
	public MBox getDepartureBox() {
		return departureBox;
	}

	public void setDepartureBox(MBox departureBox){
		this.departureBox = departureBox;
		if(departureBox != null) {
			setPathIDs();
			setChanged();
			notifyObservers();
		}
	}

	public boolean isShowTheWholePath() {
		return showTheWholePath;
	}

	public void setShowTheWholePath(boolean showTheWholePath) {
		this.showTheWholePath = showTheWholePath;
		
		if(showTheWholePath)
			setCurrentBox(getArrivalBox());
		else
			setCurrentBox(getDepartureBox());
		setChanged();
		notifyObservers();
	}

	public MBox getArrivalBox() {
		return arrivalBox;
	}
	
	public void setArrivalBox(MBox arrivalBox){
		this.arrivalBox = arrivalBox;
		if(arrivalBox != null) {
			setPathIDs();
			setChanged();
			notifyObservers();
		}
	}

	public boolean isBeingEdited() {
		return beingEdited;
	}

	public void setBeingEdited(boolean beingEdited){
		//If we edit, we need to reinitialize everything to avoid weird behaviors. So we'll do it when we close edition.
		if(!beingEdited) {
			setPathIDs();
			setCurrentBox(null);
		}
		else {
			setPathIDs(); //We do not worry about an invalid maze because we are currently going into the edition mode.
			setCurrentBox(getDepartureBox());
		}
		
		this.beingEdited = beingEdited;
		setModified(true);
		setChanged();
		notifyObservers();
	}
	
	public boolean isModified() {
		return isModified;
	}

	public void setModified(boolean isModified) {
		this.isModified = isModified;
		setChanged();
		notifyObservers();
	}

	public MBox getCurrentBox() {
		return currentBox;
	}

	public void setCurrentBox(MBox currentBox) {
		if(this.currentBox == null)
			this.currentBox = getDepartureBox();
		else
			this.currentBox = currentBox;
		
		if(currentBox == departureBox)
			boxesCount=0;
		
		boxesCount++;
		
		setChanged();
		notifyObservers();
	}

	public int getBoxesCount() {
		return boxesCount;
	}
	
	public void setBoxesCount(int i) {
		boxesCount = i;
	}

	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
		setChanged();
		notifyObservers();

	}
	
	public Pi getPi() {
		return pi;
	}
	
	public void setBoxWithId(int id, MBox b) {
		maze.setBoxWithId(id, b);
		setChanged();
		notifyObservers();
	}
	
	//Returns the IDs of the path but in the correct order !
	public ArrayList<Integer> getShortestPathIDs() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int n = boxesOfthePathIDs.size();
		for(int j=1; j<=n;j++)
		list.add(boxesOfthePathIDs.get(n-j));
		return list;
	}
		
	public String getMessageForValidPath() {
		return messageForValidPath;
	}

	// -------------------------------------------------------- Algorithm's methods ------------------------------------------------------------------------------------- //
	
	//Checks that it is possible to join A and D in the current maze.
	public boolean checkValidPath() {
		
		if(!isBeingEdited()) //If we are not in edition mode, it's too late and the path must be already valid.
			return true;
		
		boolean a;
		
		if(departureBox == null || arrivalBox == null)
		{
			a=false;
			messageForValidPath = "The maze is invalid. It needs a start (D) and an end (A).";
		}
		else if(departureBox.getId() == arrivalBox.getId()) {
			a=false;
			messageForValidPath = "The maze is invalid. It needs a start (D) and an end (A).";
		}
		else {
			setPathIDs();
			ArrayList<Integer> listOfIDs = getShortestPathIDs();
			if(listOfIDs.size() <= 1)
				a=false;
			else
				a = !(Math.abs(getDepartureBox().getX()-maze.getBoxWithId(listOfIDs.get(1)).getX()) > 1 
						|| Math.abs(getDepartureBox().getY()-maze.getBoxWithId(listOfIDs.get(1)).getY()) > 1);
			
			messageForValidPath = "There is no path between A and D!!!";
		}
		
		if(a)
			messageForValidPath = "You can close edition whenever you want...";
		
		return a;
	}

	
	
	//Converts a box to the "label"Box but to departure box or arrival box if there is none.
	public MBox convert(int id, String label){
		MBox b = null;
		
		if(getArrivalBox() != null && getDepartureBox() == null) { //If we need a departure box, it's the next one.
			b = getMaze().convert(id, "D");
			setDepartureBox(b);
			if(b.getId() == getArrivalBox().getId()) {
				setArrivalBox(null);
				setChanged();
				notifyObservers();
				return b;
			}
		}
		else if(getArrivalBox() == null) {
			b = getMaze().convert(id, "A");
			setArrivalBox(b);
			if(b.getId() == getDepartureBox().getId()) {
				setDepartureBox(null);
				setChanged();
				notifyObservers();
				return b;
			}
		}
		else{
			b = getMaze().convert(id, label);
			
			if(b.getId() == getDepartureBox().getId()) //If we delete the departure box...
				setDepartureBox(null); //... no more departure box
			else if(b.getId() == getArrivalBox().getId()) //Same for arrival.
				setArrivalBox(null);
		}
		
		
		setChanged();
		notifyObservers();
		return b;
	}

	//Returns the ids of the boxes in the path.
	public void setPathIDs() {
		
		boxesOfthePathIDs = new ArrayList<Integer>();
		Previous prev = new Previous();
		pi = new Pi(); //No results specified initially
		prev = (Previous) Dijkstra.dijkstra(maze, departureBox, pi);
		ArrayList<VertexInterface> list = prev.getShortestPathTo(arrivalBox);
	
		for(VertexInterface e : list) {
			boxesOfthePathIDs.add(new Integer(e.getId()));
		}
	}
	
	
	// ---------------------- A strange function that allows to update often enough. --------------- 
	
	//Not very clean, but it's the only solution I found to notify changes during the edition phase.
	public void notifyBoxButton() {
		setChanged();
		notifyObservers();
	}

	public void newMaze(int h, int w) {
		//We write an "empty" file.
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("data/labyrinthe.txt");
			for(int i=0; i<h;i++) {
				for(int j=0; j<w;j++) {
					if(i==0 && j==0)
						pw.print("D");
					else if(i==h-1 && j==w-1)
						pw.print("A");
					else
						pw.print("E");
				}
				pw.println("");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {pw.close();}catch(Exception e) {
				e.printStackTrace();
				System.out.println("Exception when we tried to close the file");
			}
		}
		
	}
}
