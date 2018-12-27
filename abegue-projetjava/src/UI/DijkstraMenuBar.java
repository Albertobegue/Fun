package UI;

import javax.swing.JMenuBar;

public final class DijkstraMenuBar extends JMenuBar {

	private final FileMenu fileMenu;
	private final EditMenu editMenu;
	
	public DijkstraMenuBar(DijkstraApp dijkstraApp) {
		add(fileMenu = new FileMenu(dijkstraApp));
		add(editMenu = new EditMenu(dijkstraApp));
	}

	public void notifyForUpdate() {
		editMenu.notifyForUpdate();		
	}
}
