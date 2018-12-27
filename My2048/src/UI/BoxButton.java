package UI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

import model.Box;

public class BoxButton extends JButton {
	
	private App2048 app2048;
	private Box box;
	
	public BoxButton(App2048 app2048,Box box) {
		this.app2048 = app2048;
		this.box = box;
		Integer i = new Integer(box.getValue());
		if(!i.equals(0))
			setText(i.toString());
		setBackground(Color.WHITE);
		if(box.getValue() == 2)
			setBackground(Color.GRAY);
		this.setForeground(Color.BLACK);
		setEnabled(false);
	}
	
	
	public void notifyForUpdate() {
		int value = box.getValue();
		String valueSTR = String.valueOf(value);
		if(value != 0)
			setText(valueSTR);
		else
			setText("");
		switch(value) {
		case 0:
			setBackground(Color.WHITE);
			break;
		case 2:
			setBackground(Color.GRAY);
			break;
		case 4:
			setBackground(Color.GREEN);
			break;
		case 8:
			setBackground(Color.BLUE);
			break;
		case 16:
			setBackground(Color.CYAN);
			break;
		case 32:
			setBackground(Color.ORANGE);
			break;
		case 64:
			setBackground(Color.RED);
			break;
		case 128:
			setBackground(Color.PINK);
			break;
		case 256:
			setBackground(Color.YELLOW);
			break;
		case 512:
			setBackground(Color.MAGENTA);
			break;
		case 1024:
			setBackground(Color.LIGHT_GRAY);
			break;
		case 2048:
			setBackground(Color.BLACK);
			break;
		}
		
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setForeground(Color.BLACK);
	}
}
