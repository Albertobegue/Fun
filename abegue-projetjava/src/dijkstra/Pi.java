package dijkstra;

import java.util.ArrayList;
import java.util.Hashtable;

/* Implementation of the PiInterface
 * 
 * A Hashtable is used to make associate a Pi-Distance (as in Dijkstra's algorithm) to each Vertex.
 * 3 methods allow to get, set, or choose the shortest distances.
 * -1 is used to represent an infinite distance.
 * 
 */
public class Pi implements PiInterface {
	
	private Hashtable<VertexInterface, Integer> pi;
	
	public Pi() {
		pi = new Hashtable<VertexInterface, Integer>();
	}

	@Override
	public int getPi(VertexInterface a) {
		return pi.get(a);
	}

	@Override
	public void setPi(VertexInterface a, int distance) {
		Integer d = new Integer(distance);
		pi.put(a, d);
	}

	//Returns the element of a list which has the shortest Pi-distance
	@Override
	public VertexInterface getMinimalPi(ArrayList<VertexInterface> list) {
		int min = Integer.MAX_VALUE;
		VertexInterface vWithMinPi = list.get(0);
		
		for(VertexInterface s : list) {
			int a = pi.get(s).intValue();
			if(a < min && a != -1) {
				min = a;
				vWithMinPi = s;
			}
		}
		
		if(min!=-1)
			return vWithMinPi;
		else
			return null;
	}

}
