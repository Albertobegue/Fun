package UI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public final class ControlPanel extends JPanel {
	
	private final ButtonsPanel buttonsPanel;
	private final InformationLabel informationLabel;
	
	public ControlPanel(DijkstraApp dijkstraApp) {
		setLayout(new GridLayout(1,2));
		
		this.setPreferredSize(new Dimension(100,100));
		
		add(buttonsPanel = new ButtonsPanel(dijkstraApp));
		add(informationLabel = new InformationLabel(dijkstraApp));
	}

	public void notifyForUpdate() {
		buttonsPanel.notifyForUpdate();
		informationLabel.notifyForUpdate();
	}

}
