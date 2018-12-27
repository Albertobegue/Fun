package UI;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Box;
import model.Grid;

public class WindowPanel extends JPanel {
	
	private ArrayList<BoxButton> gridUI;
	
	public WindowPanel(App2048 app2048) {
		gridUI = new ArrayList<BoxButton>();
		Grid grid = app2048.getModel().getGrid();
		int height = grid.getHeight();
		int width = grid.getWidth();
		
		setLayout(new GridLayout(height,width));
		
		for(int i = 0; i<height;i++) {
			for(int j = 0; j<width;j++) {
				gridUI.add(new BoxButton(app2048,grid.getBox(i, j)));
				add(gridUI.get(i*width+j));
			}
		}
		
	}

	public void notifyForUpdate() {
		for(BoxButton b : gridUI)
			b.notifyForUpdate();
	}
	
	

}
