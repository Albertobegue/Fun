package dijkstra;

import java.util.ArrayList;

/* Interface for the previous function
 * 
 * Very similar to PiInterface.
 */
public interface PreviousInterface {
	
	//The access to the previous vertex in the array will be made with the ID, that is unique.
	
	public VertexInterface getPrevious(VertexInterface a);
	
	public void setPrevious(VertexInterface a, VertexInterface previous);
	
	//Returns the list of fathers in a path in the graph (the shortest, actually). Be careful with the order (normal or reverse)
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex);

}
