package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;

import maze.MBox;
import maze.Maze;
import model.DijkstraAppModel;

public final class NextStepButton extends JButton implements ActionListener {
	
	private final DijkstraApp dijkstraApp;
	
	public NextStepButton(DijkstraApp dijkstraApp) {
		
		super("Next");
		this.dijkstraApp = dijkstraApp;
		
		setFont(new Font("Arial", Font.PLAIN, 30));
		setBackground(Color.WHITE);
		addActionListener(this);
		
	}
	
	public final void actionPerformed(ActionEvent evt) {
		DijkstraAppModel dijkstraAppModel = dijkstraApp.getDijkstraAppModel();
		ArrayList<Integer> idList = dijkstraAppModel.getShortestPathIDs();
		
		Maze maze = dijkstraAppModel.getMaze();
		
		if(dijkstraAppModel.isShowTheWholePath()) {
			dijkstraAppModel.setShowTheWholePath(false);
			dijkstraAppModel.setCurrentBox(dijkstraAppModel.getCurrentBox());
			dijkstraAppModel.setBoxesCount(0);
		}
		
		int i = dijkstraAppModel.getBoxesCount();
		int n = 0;
		
		if(i == 1)
			dijkstraAppModel.setPathIDs();
		
		if(i<idList.size())
			n = idList.get(i);
		else {//It means that we finished to show the path. Reinitialize.
			dijkstraAppModel.setCurrentBox(dijkstraAppModel.getDepartureBox());
			dijkstraAppModel.setBoxesCount(1);
			return;
		}
		
		MBox box = maze.getBoxWithId(n);
		if(!box.isVisitable()) {
			dijkstraAppModel.setCurrentBox(null);
			return;
		}
		dijkstraAppModel.setCurrentBox(box);			
		
	}

	public void notifyForUpdate() {
		setEnabled(!dijkstraApp.getDijkstraAppModel().isBeingEdited());
		repaint();
	}

}
