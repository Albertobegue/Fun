package maze;

import java.util.ArrayList;

import dijkstra.VertexInterface;

/* Abstract class that contains most informations on a box (of the maze)
 * Implementation of the VertexInterface + other getters and another method getNeighbours()
 * 
 * Concrete classes : ABox, DBox, EBox, WBox
 */
public abstract class MBox implements VertexInterface{ 
	
	private int x; //Coordinates in the maze
	private int y;
	
	private Maze maze;
	private String label; 
	private int id;
	private boolean isVisitable;
	
	public MBox(int x, int y, Maze maze, String label, boolean visitable) {
		this.x = x;
		this.y = y;
		this.maze = maze;
		this.label = label;
		isVisitable = visitable;
		id = maze.getNewId(); //Like this, each ID will be unique;
		
	}
	
	//A constructor that can ONLY be called from the maze class. Used to convert boxes. Allows to avoid to ask for a new ID.
	public MBox(int id, Maze maze, String label) {
		this.maze = maze;
		this.label = label;
		this.id = id;
		this.x = maze.getBoxWithId(id).getX();
		this.y = maze.getBoxWithId(id).getY();
		this.isVisitable = (label != "W");
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public String getLabel() {
		return label;
	}
	
	public boolean isVisitable() {
		return isVisitable;
	}

	public ArrayList<MBox> getNeighbours(){
		ArrayList<MBox> neighbours = new ArrayList<MBox>();
		
		if(x>0)
			neighbours.add(maze.getBox(x-1, y)); //Up
		if(x<maze.getHeight()-1)
			neighbours.add(maze.getBox(x+1, y)); //Down
		if(y>0)
			neighbours.add(maze.getBox(x, y-1)); //Left
		if(y<maze.getWidth()-1)
			neighbours.add(maze.getBox(x, y+1)); //Right
		
		return neighbours;
	}
	
	public final int getX() {
		return this.x;
	}
	
	public final int getY() {
		return this.y;
	}
	
}
