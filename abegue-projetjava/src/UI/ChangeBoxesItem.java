package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;


public final class ChangeBoxesItem extends JMenuItem implements ActionListener {
	
	private final DijkstraApp dijkstraApp;
	
	public ChangeBoxesItem(DijkstraApp dijkstraApp) {
		super("Change boxes");
		this.dijkstraApp = dijkstraApp;
		addActionListener(this);
	}
	
	public final void actionPerformed(ActionEvent evt) {
		dijkstraApp.getDijkstraAppModel().setBeingEdited(true);
	}

	public void notifyForUpdate() {
		setEnabled(!dijkstraApp.getDijkstraAppModel().isBeingEdited());		
	}
}
