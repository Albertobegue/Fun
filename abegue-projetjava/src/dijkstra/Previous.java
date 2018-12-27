package dijkstra;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/* Implementation of PreviousInterface
 * 
 * A Hashtable is used to associate each Vertex to another vertex (his father)
 * 
 * Except the constructor, all the methods are an implementation of the methods of the interface.
 */
public class Previous implements PreviousInterface {

	private Hashtable<VertexInterface, VertexInterface> fathers;
	
	public Previous() {
		fathers = new Hashtable<VertexInterface, VertexInterface>(); //Sons are the keys and fathers are the values.
	}
	
	@Override
	public VertexInterface getPrevious(VertexInterface a) {
		return fathers.get(a);
	}

	@Override
	public void setPrevious(VertexInterface a, VertexInterface previous) {
		fathers.put(a,previous); //son, father so that .get(a) gives the father.
	}
	
	@Override
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex){
		ArrayList<VertexInterface> path = new ArrayList<VertexInterface>();
		VertexInterface father = fathers.get(vertex);
		path.add(vertex);
		
		while(father != null) {
			path.add(father);
			father = fathers.get(father);
		}
				
		return path; // <first father, second father, ..., root> (be careful with the order)
	}

}
