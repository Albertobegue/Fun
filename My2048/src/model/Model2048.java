package model;

import java.util.Observable;

public class Model2048 extends Observable {
	
	private Grid grid;
	
	public Model2048() {
		Grid grid = new Grid();
		setGrid(grid);
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		setChanged();
		notifyObservers();
		this.grid = grid;
	}
	
	public void move(String direction) {
		grid.nextMove(direction);
		setChanged();
		notifyObservers();
	}
	
	

}
