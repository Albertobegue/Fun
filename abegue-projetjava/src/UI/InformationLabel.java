package UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import dijkstra.Pi;
import model.DijkstraAppModel;

public final class InformationLabel extends JLabel {
	
	private final DijkstraApp dijkstraApp;
	private int distance = 0;
	
	public InformationLabel(DijkstraApp dijkstraApp) {
		this.dijkstraApp = dijkstraApp;
		this.setFont(new Font("Arial", Font.BOLD, 20));
		this.setBackground(Color.WHITE);
		this.setText("Welcome! In edition mode, click on a box to change its state. Have fun :-)");
	}

	public void notifyForUpdate() {
		DijkstraAppModel dap = dijkstraApp.getDijkstraAppModel();
		Pi pi = dap.getPi();
		if(dap.getCurrentBox() == null || dap.getCurrentBox() == dap.getDepartureBox() || dap.isBeingEdited())
			distance = 0;
		else
			distance = pi.getPi(dijkstraApp.getDijkstraAppModel().getCurrentBox());
		
		if(!dap.isBeingEdited())
			this.setText("Current distance : "+distance);
		else 
			this.setText(dap.getMessageForValidPath());
		repaint();
	}

}
