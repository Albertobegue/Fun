package maze;

public final class ABox extends MBox {
	
	public ABox(int x, int y, Maze maze) {
		super(x, y, maze, "A", true); //The boolean is the answer to isVisitable ?
	}
	
	public ABox(int id, Maze maze, String label) {
		super(id,maze,label);
	}
	
}
