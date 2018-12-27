package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;



public final class ShortestPathButton extends JButton implements ActionListener {
	
	private final DijkstraApp dijkstraApp;
	
	public ShortestPathButton(DijkstraApp dijkstraApp) {
		
		super("Show shortest path");
		this.dijkstraApp = dijkstraApp;
		
		setFont(new Font("Arial", Font.PLAIN, 30));
		setBackground(Color.WHITE);
		setEnabled(true);
		addActionListener(this);
		
	}
	
	public final void actionPerformed(ActionEvent evt) {
		dijkstraApp.getDijkstraAppModel().setPathIDs();
		//Allow to go through the path.
		dijkstraApp.getDijkstraAppModel().setShowTheWholePath(true);
	}

	public void notifyForUpdate() {
		setEnabled(!dijkstraApp.getDijkstraAppModel().isBeingEdited());
		repaint();
	}

}
