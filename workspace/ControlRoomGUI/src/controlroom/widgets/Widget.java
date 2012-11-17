package controlroom.widgets;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

//TODO: This should be an abstract class.
public abstract class Widget extends JPanel {
	
	protected int sizeX, sizeY;
	protected String title;
	
	public Widget(int x, int y, String title) {
		sizeX = x;
		sizeY = y;
		this.title = title;
		
		setSize(sizeX, sizeY);
		add(new JLabel(title));
		setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		g.fillRect(0, 20, sizeX, sizeY);
	}
	
	
}
