package model;
import java.util.ArrayList;

public class Box {
	
	private int value = 0;
	private int id;
	private int x;
	private int y;
	
	private Grid grid;
	
	public Box(int x,int y, Grid grid) {
		this.x = x;
		this.y = y;
		this.grid = grid;
		id = grid.getNewId();
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean hasNeighbour(String direction){
		if(x>0 && direction.equals("down")) {
			return true;
		}
		if(x<grid.getHeight()-1 && direction.equals("up")) {
			return true;
		}
		if(y>0 && direction.equals("left")) {
			return true;
		}
		if(y<grid.getWidth()-1 && direction.equals("right")) {
			return true;
		}
		return false;
	}
	
	public Box getNeighbour(String direction) {
		
		if(!hasNeighbour(direction)) //If there is no neighbour, the return is the box itself.
			return this;
		
		else {
			if(x>0 && direction.equals("down")) {
				return grid.getBox(x-1, y);
			}
			if(x<grid.getHeight()-1 && direction.equals("up")) {
				return grid.getBox(x+1, y);
			}
			if(y>0 && direction.equals("left")) {
				return grid.getBox(x, y-1);
			}
			if(y<grid.getWidth()-1 && direction.equals("right")) {
				return grid.getBox(x, y+1);
			}		
		}
		return null;
	}
	
	//If there is a collision between this box and another one
	public void hittenBy(Box b) {
		if(b == this) { //It means the method has been called for an unexisting neighbour.
			return;
		}
		if(b.getValue() == value) {
			setValue(2*value);
			b.setValue(0);
		}
		if(b.getValue() != 0 && value == 0) {
			setValue(b.getValue());
			b.setValue(0);
		}
	}
	
}
