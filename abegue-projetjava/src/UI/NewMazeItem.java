package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public final class NewMazeItem extends JMenuItem implements ActionListener{
	
	private DijkstraApp dijkstraApp;
	
	public NewMazeItem(DijkstraApp dijkstraApp) {
		super("New Maze");
		this.dijkstraApp = dijkstraApp;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		int response = JOptionPane.showOptionDialog(this, "The previous maze will be lost. Continue ?", "Warning", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.YES_OPTION);
			
		switch(response) {
		case JOptionPane.YES_OPTION:
			break;
		case JOptionPane.NO_OPTION:
			dijkstraApp.getDijkstraAppModel().getMaze().saveToTextFile("data/labyrinthe.txt");
			break;
		case JOptionPane.CANCEL_OPTION:
			return;
		}
		
		int h=0;
		int w=0;
		String answerForH = JOptionPane.showInputDialog("Enter the height of the maze : ", "10");
		
		while(h<=1) {
			try{
				h = Integer.parseInt(answerForH);
			}
			catch(NumberFormatException nfeE) {
				try{h = Integer.parseInt(JOptionPane.showInputDialog("The height needs to be an integer > 1","10"));}
				catch(NumberFormatException exception) {h=0;}
			}
		}
		
		String answerForW = JOptionPane.showInputDialog("Enter the width of the maze : ", "10");
		while(w<=1) {
			try{
				w = Integer.parseInt(answerForW);
			}
			catch(NumberFormatException nfeE) {
				try{w = Integer.parseInt(JOptionPane.showInputDialog("The width needs to be an integer > 1","10"));}
				catch(NumberFormatException exception2){w=0;}
			}
		}
		
		dijkstraApp.getDijkstraAppModel().newMaze(h, w);
		dijkstraApp.dispose();
		dijkstraApp = new DijkstraApp();
	}
	
	public void notifyForUpdate() {
		setEnabled(!dijkstraApp.getDijkstraAppModel().isBeingEdited());
	}
}
