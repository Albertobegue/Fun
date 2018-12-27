package UI;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public final class WindowPanel extends JPanel {
	
	private final MazePanel mazePanel;
	private final ControlPanel controlPanel;
	
	public WindowPanel(DijkstraApp dijkstraApp) {
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(SOMEBITS);
		setLayout(borderLayout);
		
		
		add(mazePanel = new MazePanel(dijkstraApp), BorderLayout.CENTER);
		add(controlPanel = new ControlPanel(dijkstraApp), BorderLayout.SOUTH);
	}

	public void notifyForUpdate() {
		mazePanel.notifyForUpdate();
		controlPanel.notifyForUpdate();
	}

}
