package maze;

import java.util.ArrayList;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/* Implementation of the GraphInterface
 * 
 * Don't forget the height and the width in the constructor.
 */
public final class Maze implements GraphInterface {
	
	private int height; //Height and width are the size of the lists (0...11 : height=12)
	private int width;
	private int vertexCounter; //To know how many vertices have been added to the graph
	private ArrayList<ArrayList<MBox>> maze; //A list of lines so that maze.get(i).get(j) is the box in (i,j)
	/*We use a matrix to represent the graph instead of a list of neighbours, although the size of the graph will be around n, because it will be
	easier to represent the maze graphically*/
	
	private ArrayList<ArrayList<Integer>> weights; //The matrix of the weights (coefficients are the IDs)
	
	
	public Maze(int h, int w) {
		height = h;
		width = w;
		maze = new ArrayList<ArrayList<MBox>>();
		weights = new ArrayList<ArrayList<Integer>>(); 
		/*The matrix of weights is the square matrix where the coordinates represent the IDs of the boxes:
		 *            0  1   2  .... n n+1 n+2 n+3 n+4 ... 2n+1 ....... 
		 *         0
		 *         1
		 *         2
		 *         .
		 *         .
		 *         .
		 *         .
		 *         .
		 *         
		 */
		
		for(int i=0;i<height*width;i++) { //We fill weights with zeros
			ArrayList<Integer> line = new ArrayList<Integer>();
			for(int j=0;j<height*width;j++) {
				line.add(0);
			}
			weights.add(line); //Weights initialized with 0.
		}
		vertexCounter = 0;
	}
	
	//Convert the box with id id to a new box (a WBox, an EBox, an ABox or a DBox according to the label)
	public MBox convert(int id, String label) {
		MBox oldBox = getBoxWithId(id);
		MBox newBox = null;
		if(label == "A")
			newBox = new ABox(id, this, label);
		else if(label == "W")
			newBox = new WBox(id,this,label);
		else if(label == "E")
			newBox = new EBox(id,this,label);
		else if(label == "D")
			newBox = new DBox(id,this,label);
		
		setBoxWithId(id, newBox);
		return getBoxWithId(id);
	}
	
	public int getNewId() {
		int a = vertexCounter;
		vertexCounter++;
		return a;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	//The return type of this method is a MBox (abstract class) : be careful.
	public MBox getBox(int i, int j) {
		return maze.get(i).get(j); 
	}
	
	//To get a box with his id and not his coordinates.
	public MBox getBoxWithId(int id) { 
		int i = 0;
		int j = 0;
		
		while(width*i+j != id && i<height) {
			while(j<width-1 && width*i+j != id)
				j++;
			if(width*i + j != id) {
				i++;
				j=0;
			}
		}
		return getBox(i,j);
	}

	@Override
	public ArrayList<VertexInterface> getAllVertices() {
		//Returns line1 then line 2 then line n in one big list.
		ArrayList<VertexInterface> all = new ArrayList<VertexInterface>();
		for(int i=0; i<height;i++) {
			for(int j=0; j<width;j++)
				all.add(this.getBox(i, j));
		}
		return all;
	}

	//To change the weight between a and b.
	@Override
	public void setEdge(VertexInterface a, VertexInterface b, int weight) {
		MBox A = (MBox)a; 
		MBox B = (MBox)b;
		int i = A.getId();
		int j = B.getId();
		
		weights.get(i).set(j, weight);
		weights.get(j).set(i, weight);
		
	}

	@Override
	public int getEdge(VertexInterface a, VertexInterface b) {
		MBox A = (MBox)a; 
		MBox B = (MBox)b;
		int i = A.getId();
		int j = B.getId();
		return weights.get(i).get(j);
	}

	//Gives the neighbours of p that are visitable (A,D or E but not W). 
	@Override
	public ArrayList<VertexInterface> getSuccessors(VertexInterface p) {
		MBox box = (MBox)p;
		ArrayList<VertexInterface> successors = new ArrayList<VertexInterface>();
		if(!box.isVisitable())
			return successors;
		
		ArrayList<MBox> neighbours = box.getNeighbours();
		
		for(MBox b : neighbours) {
			if(b.isVisitable()) {
				successors.add(b);
			}
		}
		return successors;
	}
	
	public void setBoxWithId(int id, MBox b) {
		int i = 0;
		int j = 0;
		
		while(width*i+j != id && i<height) {
			while(j<width-1 && width*i+j != id)
				j++;
			if(width*i + j != id) {
				i++;
				j=0;
			}
		}
		maze.get(i).set(j, b);
		ArrayList<MBox> neighbours = b.getNeighbours();
		if(!b.isVisitable()) {
			for(MBox m : neighbours) 
				setEdge(m,b,0);		
		}
		
		else {
			for(MBox m : neighbours) {
				if(b.isVisitable())
					setEdge(m,b,1);
				else
					setEdge(m,b,0);
			}
		}
	}
	
	/*Reads a text file and builds a maze if it looks like :
	* 
	* AEEEE
	* EEEEE
	* EWEEW
	* EWWDW
	*/
	public final void initFromTextFile(String fileName) {
		
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileName));
			String currentLine = in.readLine();
			int lineNumber=0;
			ArrayList<MBox> newLine = new ArrayList<MBox>();
			while(currentLine != null) {
				newLine = createBoxesFrom(currentLine,lineNumber,fileName);
				maze.add(newLine);
				lineNumber++;
				currentLine = in.readLine();
			}
			
			if(lineNumber!=height)
				throw new MazeReadingException("There are " + lineNumber +" lines but there should be " + height,lineNumber,fileName);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found.");
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("Exception during opening.");
		} catch (MazeReadingException e) {
			e.printStackTrace();
			e.getMessage();
		}finally {
			try { in.close(); } catch(Exception e){
				e.printStackTrace();
				System.out.println("Exception when we tried to close the file");
			}
		}
		
		setAllEdges(); //Now we can set edges
		 
	}
	
	//Used in initFromTextFile to create the boxes
	private final ArrayList<MBox> createBoxesFrom(String line, int lineNumber, String fileName) 
			throws MazeReadingException{ 
		
		//First, we create a list of boxes in reading the line.
		ArrayList<MBox> newLine = new ArrayList<MBox>();
		char a = 'm';
		
		if(line.length()!=width) {
			throw new MazeReadingException("The line " + lineNumber + " is too large or too small",lineNumber,fileName);
		}
			
		for(int i=0; i<line.length(); i++) {
			a = line.charAt(i);
			if(a=='E') {
				EBox e = new EBox(lineNumber,i, this);
				newLine.add(e);
			}
			else if(a=='D') {
				DBox d = new DBox(lineNumber,i, this);
				newLine.add(d);
			}
			else if(a=='W') {
				WBox w = new WBox(lineNumber,i, this);
				newLine.add(w);
			}
			else if(a=='A') {
				ABox ar = new ABox(lineNumber,i, this);
				newLine.add(ar);
			}
			else {
				throw new MazeReadingException("The character read is not A, E, W or D",lineNumber,fileName);
			}
		}
		return newLine;
	}
	
	/*We allege that the maze contains all the boxes but no links. We update weights to build the links.
	 * the method is used in initFromTextFile.
	 */
	public void setAllEdges() {
		for(int i=0;i<height*width;i++) {
			MBox currentBox = getBoxWithId(i);
			ArrayList<MBox> neighbours = currentBox.getNeighbours();
			if(!currentBox.isVisitable()) {
				for(MBox b : neighbours) 
					setEdge(b,currentBox,0);		
			}
			
			else {
				for(MBox b : neighbours) {
					if(b.isVisitable())
						setEdge(b,currentBox,1);
					else
						setEdge(b,currentBox,0);
				}
			}
		}
	}
	
	// Save the current maze in a data file
	public final void saveToTextFile(String fileName) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fileName);
			for(int i=0; i<height;i++) {
				for(int j=0; j<width;j++)
					pw.print(getBox(i,j).getLabel());
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
