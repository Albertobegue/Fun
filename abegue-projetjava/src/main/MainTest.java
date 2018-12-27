package main;

import maze.MBox;
import maze.Maze;

import java.util.ArrayList;

import UI.DijkstraApp;
import dijkstra.Dijkstra;
import dijkstra.Previous;
import dijkstra.VertexInterface;

public class MainTest {

	public static void main(String[] args) {
		
		/*Creation du labyrinthe et determination de l'arborescence
		//Maze building and initialization
		Maze maze = new Maze(10,10);
		maze.initFromTextFile("data/labyrinthe.txt");
		MBox a = maze.getBox(0, 2);
		Previous prev = new Previous();
		prev = (Previous) Dijkstra.dijkstra(maze, a);
		ArrayList<VertexInterface> list = prev.getShortestPathTo(maze.getBox(6,8));
		
		//Test in the terminal
		String path = "";
		for(int i=0; i<list.size();i++) {
			path += list.get(i).getId()+" ";
		}
		System.out.print(path);
		*/
		
		new DijkstraApp();
	}

}
