package UI;

import java.awt.Font;

import javax.swing.JMenu;

public final class EditMenu extends JMenu {
	
	private final NewMazeItem newMazeItem;
	private final ChangeBoxesItem changeBoxesItem;
	private final CloseEditionItem closeEditionItem;
	
	public EditMenu(DijkstraApp dijkstraApp) {
		super("Edition");
		add(newMazeItem = new NewMazeItem(dijkstraApp));
		add(changeBoxesItem = new ChangeBoxesItem(dijkstraApp));
		add(closeEditionItem = new CloseEditionItem(dijkstraApp));
	}

	public void notifyForUpdate() {
		changeBoxesItem.notifyForUpdate();
		closeEditionItem.notifyForUpdate();
		newMazeItem.notifyForUpdate();
	}

}
