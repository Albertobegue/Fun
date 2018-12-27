package maze;

//Exception restricted to the problem in reading the maze.
public final class MazeReadingException extends Exception {
	
	private int lineNumber;
	private String fileName;
	
	public MazeReadingException(String message, int lineNumber, String fileName) {
		super(message + " at line " + lineNumber);
		this.fileName = fileName;
		this.lineNumber = lineNumber;
	}
	
	public int getLine() {
		return lineNumber;
	}
	
	public String getFile() {
		return fileName;
	}
	
}
