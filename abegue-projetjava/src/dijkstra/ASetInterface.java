package dijkstra;

import java.util.ArrayList;


/* Interface for a set of vertices
 * 
 * 4 methods.
 */
public interface ASetInterface { 
	
	//Add a vertex to the set. The root will be put in the first element of the ArrayList.
	public void addVertex(VertexInterface s);
		
	//Returns an ArrayList of all the vertices in the set.
	public ArrayList<VertexInterface> getVertices();
	
	public int getSize();
	
	//True if a is in the set.
	public boolean contains(VertexInterface a);

}
