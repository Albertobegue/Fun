package maze;

public final class DBox extends MBox {
		
	public DBox(int x, int y, Maze maze) {
		super(x, y, maze, "D", true);
	}
	
	public DBox(int id, Maze maze, String label) {
		super(id,maze,label);
	}

}
