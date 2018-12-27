package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;


public final class CloseEditionItem extends JMenuItem implements ActionListener {
	
	private final DijkstraApp dijkstraApp;
	
	public CloseEditionItem(DijkstraApp dijkstraApp) {
		super("Close edition");
		this.dijkstraApp = dijkstraApp;
		setEnabled(false);
		addActionListener(this);
	}
	
	public final void actionPerformed(ActionEvent evt) {		
		dijkstraApp.getDijkstraAppModel().setPathIDs();
		//Allow to go through the path.
		dijkstraApp.getDijkstraAppModel().setBeingEdited(false);
		dijkstraApp.getDijkstraAppModel().setCurrentBox(dijkstraApp.getDijkstraAppModel().getDepartureBox());
	}

	public void notifyForUpdate() {
		if(dijkstraApp.getDijkstraAppModel().isBeingEdited())
			setEnabled(dijkstraApp.getDijkstraAppModel().checkValidPath());
		else
			setEnabled(false);
	}
}
