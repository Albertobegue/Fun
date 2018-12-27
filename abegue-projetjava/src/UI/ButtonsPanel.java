package UI;

import java.awt.GridLayout;

import javax.swing.JPanel;

public final class ButtonsPanel extends JPanel {
	
	private final NextStepButton nextStepButton;
	private final ShortestPathButton shortestPathButton;
	
	public ButtonsPanel(DijkstraApp dijkstraApp) {
		setLayout(new GridLayout(1,2));
		

		add(shortestPathButton = new ShortestPathButton(dijkstraApp));
		add(nextStepButton = new NextStepButton(dijkstraApp));
	}

	public void notifyForUpdate() {
		nextStepButton.notifyForUpdate();
		shortestPathButton.notifyForUpdate();
	}


}
