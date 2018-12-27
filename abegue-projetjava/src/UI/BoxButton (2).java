package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import maze.EBox;
import maze.MBox;
import maze.Maze;
import maze.WBox;

public class BoxButton extends JButton implements ActionListener {
	
	private final DijkstraApp dijkstraApp;
	private MBox mBox;
	private boolean selected = false;
	private ImageIcon icon = new ImageIcon("data/wall.jpeg");
	
	public BoxButton(DijkstraApp dijkstraApp, MBox mBox) {
		super(mBox.getLabel());
		if(mBox.getLabel().equals("E"))
			setText("");
		
		if(dijkstraApp.getDijkstraAppModel().getMaze().getHeight()*dijkstraApp.getDijkstraAppModel().getMaze().getHeight() < 100)
			setFont(new Font("Arial", Font.PLAIN, 20));
		
		addActionListener(this);
		setEnabled(dijkstraApp.getDijkstraAppModel().isBeingEdited());
		
		if(!mBox.isVisitable()) 
			this.setIcon(icon);
		else
			setBackground(Color.WHITE);
		
		this.dijkstraApp = dijkstraApp;
		this.mBox = mBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int id = mBox.getId();
		if(mBox.isVisitable()) {
			mBox = dijkstraApp.getDijkstraAppModel().convert(id, "W");
		}
		else{
			mBox = dijkstraApp.getDijkstraAppModel().convert(id, "E");
		}
		dijkstraApp.getDijkstraAppModel().notifyBoxButton();
	}
	
	public void setSelected(boolean a) {
		selected = a;
		if(selected) 
			setBackground(Color.CYAN);
		else {
			if(!mBox.isVisitable())
				setIcon(icon);
			else
				setBackground(Color.WHITE);
		}
	}

	public void notifyForUpdate() {
		if(dijkstraApp.getDijkstraAppModel().isBeingEdited()) {
			setEnabled(true);
			setText(mBox.getLabel());
			if(mBox.getLabel().equals("E"))
				setText("");
			if(!mBox.isVisitable())
				setIcon(icon);
			else
			{
				setBackground(Color.WHITE);
				setIcon(null);
			}
		}
		else
			setEnabled(false);
		
		if(dijkstraApp.getDijkstraAppModel().getCurrentBox() == mBox && dijkstraApp.getDijkstraAppModel().isShortestPathBeingShown())
			setSelected(true);
		if(!dijkstraApp.getDijkstraAppModel().isShortestPathBeingShown())
			setSelected(false);
		
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if(!mBox.isVisitable())
			g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
		else
			g.drawImage(null, 0, 0, getWidth(), getHeight(), this);
	}

}
