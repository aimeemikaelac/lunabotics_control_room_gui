package controlroom.widgets;

import java.awt.Color;
import java.awt.Graphics;

public class KinectWidget extends Widget {

	public KinectWidget(int x, int y, String title) {
		super(x, y, title);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillOval(20, 20, 260, 260);
		
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, 0, sizeY-1);
		g.drawLine(0, 0, sizeX-1, 0);
		g.drawLine(sizeX-1, 0, sizeX-1, sizeY-1);
		g.drawLine(0, sizeY-1, sizeX-1, sizeY-1);
	}
	
}
