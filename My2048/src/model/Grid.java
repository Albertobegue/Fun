package model;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
	
	private int height = 4;
	private int width = 4;
	private ArrayList<Box> boxes;
	private ArrayList<Box> emptyBoxes;
	private int boxCounter = 0;
	private boolean changed = false;
	
	public Grid() {
		boxes = new ArrayList<Box>();
		for(int i = 0; i<height;i++) {
			for(int j = 0; j<width; j++) {
				Box b = new Box(i,j,this);
				boxes.add(b);
			}
		}
		getBox(3,1).setValue(2);
		emptyBoxes = (ArrayList<Box>) boxes.clone();
	}
	
	public int getNewId() {
		boxCounter++;
		return boxCounter;
	}
	
	public Box getBox(int x, int y) {
		return boxes.get(x*width+y);
	}
	
	
	public boolean isChanged() {	
		return changed;
	}
	
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	private void move(String direction) {
		if(direction.equals("up")) {
			Box box = getBox(0,0);
			Box neighbour = box.getNeighbour(direction);
			setChanged(false);
			for(int j = 0; j<width; j++) {
				for(int i = 0; i<height;i++) {
					box = getBox(i,j);
				    neighbour = box.getNeighbour(direction);
				    int value = box.getValue();
					if(box.getValue() != 0) {
						while(neighbour.getValue() == 0 && neighbour != neighbour.getNeighbour(direction)) 
							neighbour = neighbour.getNeighbour(direction);
						
						box.hittenBy(neighbour);
					}
					else {
						while(neighbour.getValue() == 0 && neighbour != neighbour.getNeighbour(direction)) 
							neighbour = neighbour.getNeighbour(direction);
						
						neighbour.hittenBy(neighbour.getNeighbour(direction));
						box.hittenBy(neighbour);
					}
					if(!changed)
						setChanged(value != box.getValue());
				}
			}
			return;
		}
		if(direction.equals("down")) {
			Box box = getBox(0,0);
			Box neighbour = box.getNeighbour(direction);
			setChanged(false);
			for(int j = width-1; j>=0; j--) {
				for(int i = height-1; i>=0;i--) {
					box = getBox(i,j);
				    neighbour = box.getNeighbour(direction);
				    int value = box.getValue();
					if(box.getValue() != 0) {
						while(neighbour.getValue() == 0 && neighbour != neighbour.getNeighbour(direction)) 
							neighbour = neighbour.getNeighbour(direction);
						
						box.hittenBy(neighbour);
					}
					else {
						while(neighbour.getValue() == 0 && neighbour != neighbour.getNeighbour(direction)) 
							neighbour = neighbour.getNeighbour(direction);
						neighbour.hittenBy(neighbour.getNeighbour(direction));
						box.hittenBy(neighbour);
					}
					if(!changed)
						setChanged(value != box.getValue());
				}
			}
			return;
		}
		if(direction.equals("left")) {
			Box box = getBox(0,0);
			Box neighbour = box.getNeighbour(direction);
			setChanged(false);
			for(int j = height-1; j>=0; j--) {
				for(int i = width-1; i>=0;i--) {
					box = getBox(i,j);
				    neighbour = box.getNeighbour(direction);
				    int value = box.getValue();
					if(box.getValue() != 0) {
						while(neighbour.getValue() == 0 && neighbour != neighbour.getNeighbour(direction)) 
							neighbour = neighbour.getNeighbour(direction);
						
						box.hittenBy(neighbour);
					}
					else {
						while(neighbour.getValue() == 0 && neighbour != neighbour.getNeighbour(direction)) 
							neighbour = neighbour.getNeighbour(direction);
						neighbour.hittenBy(neighbour.getNeighbour(direction));
						box.hittenBy(neighbour);
					}
					if(!changed)
						setChanged(value != box.getValue());
				}
			}
			return;
		}
		if(direction.equals("right")) {
			Box box = getBox(0,0);
			Box neighbour = box.getNeighbour(direction);
			setChanged(false);
			for(int j = 0; j<height; j++) {
				for(int i = 0; i<width;i++) {
					box = getBox(i,j);
				    neighbour = box.getNeighbour(direction);
				    int value = box.getValue();
					if(box.getValue() != 0) {
						while(neighbour.getValue() == 0 && neighbour != neighbour.getNeighbour(direction)) 
							neighbour = neighbour.getNeighbour(direction);
						
						box.hittenBy(neighbour);
					}
					else {
						while(neighbour.getValue() == 0 && neighbour != neighbour.getNeighbour(direction)) 
							neighbour = neighbour.getNeighbour(direction);
						neighbour.hittenBy(neighbour.getNeighbour(direction));
						box.hittenBy(neighbour);
					}
					if(!changed)
						setChanged(value != box.getValue());
				}
			}
			return;
		}
	}
	
	public void nextMove(String direction) {
		move(direction);
		if(getEmptyBoxes() == null)
			return;
		ArrayList<Box> list = getEmptyBoxes();
		
		if(isChanged()) {
			Random randomGenerator = new Random();
			int i = randomGenerator.nextInt(list.size());
			list.get(i).setValue(2);
		}
	}
	
	public ArrayList<Box> getEmptyBoxes() {
		updateEmptyBoxes();
		return emptyBoxes;
	}
	
	public void updateEmptyBoxes(){
		for(Box b : boxes) {
			if(b.getValue() != 0)
				emptyBoxes.remove(b);
			else if(!emptyBoxes.contains(b))
				emptyBoxes.add(b);
		}
	}
	
	public void print() {
		System.out.println("******");
		for(int i = 0; i<height;i++) {
			for(int j = 0; j<width; j++) {
				if(j==width-1)
					System.out.println(getBox(i,j).getValue());
				else
					System.out.print(getBox(i,j).getValue());
			}
		}
	}
	
	
}
