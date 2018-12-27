package dijkstra;

import java.util.ArrayList;
import java.util.HashSet;

/* Implementation of ASetInterface
 * 
 * We used java collection HashSet that ensures that each element is unique;
 * No parameters in the constructor.
 * +4 methods
 */
public class ASet implements ASetInterface {
	
	private HashSet<VertexInterface> set;

	public ASet() {
		set = new HashSet<VertexInterface>();
	}

	@Override
	public void addVertex(VertexInterface s) {
		set.add(s);
		
	}

	//Returns an ArrayList of all the vertices in the set.
	@Override
	public ArrayList<VertexInterface> getVertices() {
		ArrayList<VertexInterface> list = new ArrayList<VertexInterface>();
		for(VertexInterface a : set) {
			list.add(a);
		}
		return list;
	}

	@Override
	public int getSize() {
		return set.size();
	}
	
	//True if a is in the set.
	@Override
	public boolean contains(VertexInterface a) {
		return set.contains(a);
	}

}
