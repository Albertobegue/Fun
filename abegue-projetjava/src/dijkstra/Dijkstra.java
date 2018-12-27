package dijkstra;

import java.util.ArrayList;

/*
 * Implementation of Dijkstra's algorithm that allows to find the shortest paths in a graph.
 */
public class Dijkstra {
	
	public Dijkstra() { //Constructor
	}
	
	//Dijkstra algorithm
	private static PreviousInterface dijkstra(GraphInterface g, 
			VertexInterface r, ASetInterface a,
			PiInterface pi, PreviousInterface previous){
		
		a.addVertex(r);
		pi.setPi(r, 0);
		VertexInterface pivot = r; //pivot has the same id as r.
					
		//Vertices that are in the graph but not yet in a.
		ArrayList<VertexInterface> allVertices = g.getAllVertices();
		ArrayList <VertexInterface> leftVertices = g.getAllVertices(); 
		int n = allVertices.size();
		
		leftVertices.remove(r); //We remove the root as we already put it in a.
		
		for(VertexInterface x : leftVertices) {
			pi.setPi(x, -1); //-1 represents the infinity.
		}
		
		for(int j=1; j<=n-1;j++) {
			
			ArrayList<VertexInterface> successors = g.getSuccessors(pivot); 
			
			for(VertexInterface y : successors) {
				
				int piPivot = pi.getPi(pivot);
				int edgeYPivot = g.getEdge(y, pivot);
				
				if(leftVertices.contains(y)) { //To avoid to take vertices already visited		
					
					if (piPivot+edgeYPivot < pi.getPi(y) || pi.getPi(y)==-1) {
							pi.setPi(y,piPivot+edgeYPivot);
							previous.setPrevious(y,pivot);
					}
				}
									
			}
			
			//We must now look for the minimal PI-distance in the vertices that are not yet in a.
			VertexInterface vertexWithMinPi = pi.getMinimalPi(leftVertices);
			
			//We prepare our visit of the vertex for the next step in the loop.
			pivot = vertexWithMinPi;
			
			a.addVertex(pivot); //We add pivot to a, so we must remove it from leftVertices
			leftVertices.remove(pivot);
		}
		
		return previous; //We return the previous function modified by the algorithm. It sums up what the algorithm does.
		
	}
	
	/*BE VERY CAREFUL : The argument pi needs to have no results specified when given in the arguments. However, we need to put it as an argument
	so that we can use it in the UI*/
	public static PreviousInterface dijkstra(GraphInterface g, VertexInterface r, Pi pi) {
		ASet set = new ASet();//Initially empty
		Previous previous = new Previous(); //idem
		PreviousInterface prev = dijkstra(g,r,set,pi,previous);
		
		
		return prev;
		
	}

}
