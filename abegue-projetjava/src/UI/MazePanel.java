package UI;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public final class MazePanel extends JPanel {
	
	private ArrayList<BoxButton> mazeGrid;
	
	public MazePanel(DijkstraApp dijkstraApp) {
		mazeGrid = new ArrayList<BoxButton>();
		int height = dijkstraApp.getDijkstraAppModel().getMaze().getHeight();
		int width = dijkstraApp.getDijkstraAppModel().getMaze().getWidth();
		
		GridLayout gridLayout = new GridLayout(height,width);
		gridLayout.setHgap(SOMEBITS);
		gridLayout.setVgap(SOMEBITS);
		setLayout(gridLayout);
		
		for(int i = 0; i<height;i++) {
			for(int j = 0; j<width;j++) {
				mazeGrid.add(new BoxButton(dijkstraApp,dijkstraApp.getDijkstraAppModel().getMaze().getBox(i,j)));
				add(mazeGrid.get(i*width+j));
			}
		}
			
		
	}

	public void notifyForUpdate() {
		for(BoxButton b : mazeGrid) {
			b.notifyForUpdate();
		}
	}
}
