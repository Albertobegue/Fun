package UI;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import model.Model2048;

public class App2048 extends JFrame implements Observer {

	private Model2048 model;
	private final WindowPanel windowPanel;
	
	public App2048() {
		super("2048");
		
		model = new Model2048();
		model.addObserver(this);
		setContentPane(windowPanel = new WindowPanel(this));
		
		setPreferredSize(new Dimension(512,512));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setVisible(true);

		KeyboardListener keyboardListener = new KeyboardListener(this);
		addKeyListener(keyboardListener);
		
	}
	
	public Model2048 getModel() {
		return model;
	}


	public void setModel(Model2048 model) {
		this.model = model;
	}


	@Override
	public void update(Observable observable, Object parameter) {
		windowPanel.notifyForUpdate();
	}

}
