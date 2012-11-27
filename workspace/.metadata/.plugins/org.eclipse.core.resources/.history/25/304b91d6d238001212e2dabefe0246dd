package controlroom.widgets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;

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
		//setLayout(new GridBagLayout());
		setSize(sizeX, sizeY);
		setBounds(0,0,sizeX,sizeY);
		add(new JLabel(title));
		
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 20, sizeX, sizeY);
	}
	
	
}
