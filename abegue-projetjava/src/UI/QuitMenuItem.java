package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public final class QuitMenuItem extends JMenuItem implements ActionListener {

	private final DijkstraApp dijkstraApp;
	
	public QuitMenuItem(DijkstraApp dijkstraApp) {
		super("Quit");
		this.dijkstraApp = dijkstraApp;
		addActionListener(this);
	}
	
	public final void actionPerformed(ActionEvent evt) {
		if(dijkstraApp.getDijkstraAppModel().isModified()) {
			int response = JOptionPane.showOptionDialog(this, "The maze is not saved. Do you want to save it ?", "Save ?", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.YES_OPTION);
			
			switch(response) {
			case JOptionPane.YES_OPTION:
				dijkstraApp.getDijkstraAppModel().getMaze().saveToTextFile("data/labyrinthe.txt");
				System.exit(0);
				break;
			case JOptionPane.NO_OPTION:
				System.exit(0);
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			}
		}
		else
			System.exit(0);
	}
}
