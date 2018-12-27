package dijkstra;

import java.util.ArrayList;

/* Interface for the function Pi that associates a distance to each Vertex
 * 
 * -1 represents the infinity.
 * 
 */
public interface PiInterface {

	//As all the distances are positive numbers, we are using -1 to represent the infinity.
	
	public int getPi(VertexInterface a);
	
	public void setPi(VertexInterface a, int distance);
	
	//Returns the Vertex which has the minimal value of PI in a list.
	public VertexInterface getMinimalPi(ArrayList<VertexInterface> list); 
	
}
