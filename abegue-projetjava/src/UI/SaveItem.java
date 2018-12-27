package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public final class SaveItem extends JMenuItem implements ActionListener {
	
	private final DijkstraApp dijkstraApp;
	
	public SaveItem(DijkstraApp dijkstraApp) {
		super("Save");
		this.dijkstraApp = dijkstraApp;
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		dijkstraApp.getDijkstraAppModel().getMaze().saveToTextFile("data/labyrinthe.txt");
		dijkstraApp.getDijkstraAppModel().setModified(false); //Isn't modified anymore because it is saved.
	}
}
