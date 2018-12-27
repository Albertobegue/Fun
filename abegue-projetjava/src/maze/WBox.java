package maze;

public final class WBox extends MBox {
	
	public WBox(int x, int y, Maze maze) {
		super(x, y, maze, "W", false); //False because a wall isn't visitable.
	}
	
	public WBox(int id, Maze maze, String label) {
		super(id,maze,label);
	}

}
