package edu.mines.blasterbotica.controlroom.widgets;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class ButtonWidget extends Widget {
	JCheckBox manualAutoCbox = new JCheckBox("Manual Mode");
	JButton startButton = new JButton("Start");
	JButton stopButton = new JButton ("Stop");

	public ButtonWidget(int x, int y, String title) {
		super(x, y, title);
		
		JPanel panel = new JPanel();//(new GridLayout(2,1));
		manualAutoCbox.setVisible(true);
		//panel.add(new JLabel(title));
		panel.add(manualAutoCbox);
		panel.add(startButton);
		panel.add(stopButton);
		
		this.add(panel);
	}
	
//	public void paintComponent(Graphics g) {
//		
//		//Use actual pictures of Buttons to improve the graphics of the GUI
//		g.setColor(Color.GREEN);
//		g.fillOval(20, 60, 120, 120);
//		
//		g.setColor(Color.RED);
//		g.fillOval(160, 60, 120, 120);
//		
//	}
}
