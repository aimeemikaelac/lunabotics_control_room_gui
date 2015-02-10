package edu.mines.blasterbotica.controlroom.gui;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.mines.blasterbotica.controlroom.data.InlineNetworkReceiver;
import edu.mines.blasterbotica.controlroom.widgets.ButtonWidget;
import edu.mines.blasterbotica.controlroom.widgets.IMUWidget;
import edu.mines.blasterbotica.controlroom.widgets.KinectWidget;
import edu.mines.blasterbotica.controlroom.widgets.LidarWidget;
import edu.mines.blasterbotica.controlroom.widgets.MapWidget;
import edu.mines.blasterbotica.controlroom.widgets.MotorWidget;
import edu.mines.blasterbotica.controlroom.widgets.NetworkWidget;
import edu.mines.blasterbotica.controlroom.widgets.Widget;
import edu.mines.blasterbotica.controlroom.widgets.XBoxControllerWidget;

public class ControlRoomGUI extends JFrame {
	
	private Widget lidarWidget = new LidarWidget(300,300,"Lidar");
	private Widget kinectWidget = new KinectWidget(300,300,"Kinect");
	private Widget imuWidget = new IMUWidget(300,200,"IMU");
	private Widget mapWidget = new MapWidget(900,600,"Maps");
	private Widget motorWidget = new MotorWidget(300,200, "Motors");
	private XBoxControllerWidget xboxWidget = new  XBoxControllerWidget(50,50,"XBox Controller");
	private ButtonWidget buttonWidget = new ButtonWidget(300,200,"Buttons");
	private NetworkWidget networkWidget = new NetworkWidget(50,50, "Network");
//	private NetworkWidget networkWidget2 = new NetworkWidget(300,200, "Network");
//	private NetworkWidget networkWidget3 = new NetworkWidget(300,200, "Network");
	private final int GUI_HEIGHT = 400;
	private final int GUI_WIDTH = 900;
	private InlineNetworkReceiver network;
	
	public ControlRoomGUI() {
		// Frame Parameters
		network = new InlineNetworkReceiver("localhost", 2000, 2400, networkWidget, xboxWidget);
		Thread netThread = new Thread(network);
		netThread.start();
		setTitle("Control Room");
		setSize(GUI_WIDTH, GUI_HEIGHT); 
		setLocation(0,0); 
	
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		  } 
		});
//		setResizable(false);
		JPanel wholePanel = new JPanel();
		wholePanel.setLayout(new BoxLayout(wholePanel, BoxLayout.Y_AXIS));
//		GridBagConstraints c = new GridBagConstraints();
//		c.gridx = 0;
//		c.gridy = 0;
//		c.gridwidth = 3;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		c.weightx = .1;
//		c.weighty = .1;
		xboxWidget.setAlignmentX(Component.CENTER_ALIGNMENT);
		wholePanel.add(xboxWidget);
//		setResizable(false);
		
//		c = new GridBagConstraints();
//		c.fill = GridBagConstraints.VERTICAL;
//		c.gridx = 1;
//		c.gridy = 1;
//		c.weightx = .9;
//		c.weighty = .9;
//		c.gridwidth = 1;
//		c.anchor = GridBagConstraints.CENTER;
		networkWidget.setAlignmentX(Component.CENTER_ALIGNMENT);
		wholePanel.add(networkWidget);
		
		JPanel dummy = new JPanel();
		dummy.setSize(100,100);
		buttonWidget.setAlignmentX(Component.CENTER_ALIGNMENT);
		wholePanel.add(buttonWidget);
//		c = new GridBagConstraints();
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.gridx = 0;
//		c.gridy = 2;
//		c.weightx = .9;
//		c.weighty = .4;
//		c.gridwidth = 3;
//		c.anchor = GridBagConstraints.LAST_LINE_START;
//		wholePanel.add(dummy);
//		wholePanel.add(networkWidget2, BorderLayout.LINE_START);
//		wholePanel.add(networkWidget3, BorderLayout.LINE_END);
		add(wholePanel);
		
//		
//		JPanel westPanel = new JPanel(new GridLayout(2,1));
//		westPanel.setPreferredSize(new Dimension(300, 600));
//		lidarWidget.setPreferredSize(new Dimension(300,300));
//		westPanel.add(lidarWidget);
//		kinectWidget.setPreferredSize(new Dimension(300,300));
//		westPanel.add(kinectWidget);
//		
//		JPanel mapPanel = new JPanel(new BorderLayout());
//		mapPanel.setPreferredSize(new Dimension(900,600));
//		mapPanel.add(mapWidget);
//		
//		JPanel southPanel = new JPanel(new GridLayout(1,4));
//		southPanel.setPreferredSize(new Dimension(GUI_WIDTH, 200));
//		southPanel.add(imuWidget);
//		southPanel.add(xboxWidget);
//		southPanel.add(motorWidget);
//		southPanel.add(buttonWidget);
//
//		wholePanel.add(southPanel, BorderLayout.SOUTH);
//		wholePanel.add(westPanel, BorderLayout.WEST);
//		wholePanel.add(mapPanel, BorderLayout.CENTER);
//		
//		add(wholePanel);
	}
	
	public static void main(String[] args) {
		JFrame f = new ControlRoomGUI();
		f.pack();
		f.setVisible(true);

	}

}
