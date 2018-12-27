package UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

	private App2048 app2048;
	
	public KeyboardListener(App2048 app2048) {
		this.app2048 = app2048;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		String direction;
	    switch(keyCode) { 
	        case KeyEvent.VK_UP:
	            direction = "up";
	            break;
	        case KeyEvent.VK_DOWN:
	            direction = "down"; 
	            break;
	        case KeyEvent.VK_LEFT:
	        	direction = "right"; //WHY ??? That's so strange.
	            break;
	        case KeyEvent.VK_RIGHT :
	            direction = "left"; //WHY ??? That's so strange.
	            break;
	        default:
	        	return;
	     }

		app2048.getModel().move(direction);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		return;		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		return;
	}
}
