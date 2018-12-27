package maze;

public final class EBox extends MBox {
	
	public EBox(int x, int y, Maze maze) {
		super(x, y, maze, "E", true);
	}
	
	public EBox(int id, Maze maze, String label) {
		super(id,maze,label);
	}
}
