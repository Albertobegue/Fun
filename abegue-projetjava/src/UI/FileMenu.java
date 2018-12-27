package UI;

import java.awt.Font;

import javax.swing.JMenu;

public final class FileMenu extends JMenu {
	
	private final QuitMenuItem quitMenuItem;
	private final SaveItem saveItem;
	
	public FileMenu(DijkstraApp dijkstraApp) {
		super("File");
		add(saveItem = new SaveItem(dijkstraApp));
		add(quitMenuItem = new QuitMenuItem(dijkstraApp));
	}

}
