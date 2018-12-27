package UI;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import dijkstra.Dijkstra;
import dijkstra.Previous;
import maze.MBox;
import maze.Maze;
import model.DijkstraAppModel;

public final class DijkstraApp extends JFrame implements Observer {

	private final DijkstraMenuBar dijkstraMenuBar;
	private final WindowPanel windowPanel;
	private DijkstraAppModel dijkstraAppModel;
	
	public DijkstraAppModel getDijkstraAppModel() {
		return dijkstraAppModel;
	}

	public void setDijkstraAppModel(DijkstraAppModel dijkstraAppModel) {
		this.dijkstraAppModel = dijkstraAppModel;
	}
	
	public DijkstraApp() {
		super("Dijkstra");
		
		dijkstraAppModel = new DijkstraAppModel();
		dijkstraAppModel.addObserver(this);
		Maze maze = dijkstraAppModel.getMaze();
		//MenuBar
		setJMenuBar(dijkstraMenuBar = new DijkstraMenuBar(this));
		dijkstraMenuBar.setPreferredSize(new Dimension(500,30));
		
		//Content
		setContentPane(windowPanel = new WindowPanel(this));
		
		setPreferredSize(new Dimension(1800,1000));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		pack();
		setVisible(true);
	}
	
	public void update(Observable observable, Object parameter) {
		windowPanel.notifyForUpdate();
		dijkstraMenuBar.notifyForUpdate();
	}

}
