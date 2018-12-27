package dijkstra;

import java.util.ArrayList;

/* Interface for a representation of a graph.
 * 
 * 4 methods
 * A distance -1 represents the infinity. 
 */
public interface GraphInterface {
			
	//Returns a list of references on all the vertices that are in the graph.
	public ArrayList<VertexInterface> getAllVertices();
	
	//Specifies the weight between a and b.
	public void setEdge(VertexInterface a, VertexInterface b, int weight); 
	
	public int getEdge(VertexInterface a, VertexInterface b);
	
	//Returns a list of references on the successors of p in the graph.
	public ArrayList<VertexInterface> getSuccessors(VertexInterface p);
	
}
