package edu.mines.blasterbotica.controlroom.widgets;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class XBoxControllerWidget extends Widget {
	
	private ImageIcon xIcon = new ImageIcon("xboxicons/xButton.png", "X button");
	private ImageIcon bIcon = new ImageIcon("xboxicons/bButton.png", "B button");
	private ImageIcon yIcon = new ImageIcon("xboxicons/yButton.png", "Y button");
	private ImageIcon backIcon = new ImageIcon("xboxicons/backButton.png", "Back button");
	private ImageIcon startIcon = new ImageIcon("xboxicons/startButton.png", "Start button");
	private ImageIcon upIcon = new ImageIcon("xboxicons/dpadUp.png", "Dpad Up");
	private ImageIcon downIcon = new ImageIcon("xboxicons/dpadDown.png", "Dpad Down");
	private ImageIcon leftIcon = new ImageIcon("xboxicons/dpadLeft.png", "Dpad Left");
	private ImageIcon rightIcon = new ImageIcon("xboxicons/dpadRight.png", "Dpad Right");
	private ImageIcon ryIcon = new ImageIcon("xboxicons/rStick.png", "Right Stick Y");
	private ImageIcon lyIcon = new ImageIcon("xboxicons/lStick.png", "Left Stick Y");
	private ImageIcon rtIcon = new ImageIcon("xboxicons/rTrigger.png", "Right Trigger");
	private ImageIcon ltIcon = new ImageIcon("xboxicons/lTrigger.png", "Left Trigger");
	private ImageIcon rsIcon = new ImageIcon("xboxicons/rBumper.png", "Right Shoulder");
	private ImageIcon lsIcon = new ImageIcon("xboxicons/lBumper.png", "Left Shoulder");
	
	private JToggleButton XButton = new JToggleButton(xIcon);
	private JToggleButton BButton=new JToggleButton(bIcon);
	private JToggleButton YButton=new JToggleButton(yIcon);
	private JToggleButton BackButton=new JToggleButton(backIcon);
	private JToggleButton StartButton=new JToggleButton(startIcon);
	private JToggleButton DpadUp=new JToggleButton(upIcon);
	private JToggleButton DpadDown=new JToggleButton(downIcon);
	private JToggleButton DpadLeft=new JToggleButton(leftIcon);
	private JToggleButton DpadRight=new JToggleButton(rightIcon);
	private JToggleButton RightStickY=new JToggleButton(ryIcon);
	private JToggleButton LeftStickY=new JToggleButton(lyIcon);
	private JToggleButton RightTrigger=new JToggleButton(rtIcon);
	private JToggleButton LeftTrigger=new JToggleButton(ltIcon);
	private JToggleButton RightShoulder=new JToggleButton(rsIcon);
	private JToggleButton LeftShoulder=new JToggleButton(lsIcon);
	private JLabel rStickLabel = new JLabel("Right Stick Value:");
	private JTextField rightStick = new JTextField(5);
	private JLabel lStickLabel = new JLabel("Left Stick Value:");
	private JTextField leftStick = new JTextField(5);
	private JLabel rightTriggerLabel = new JLabel("Right Trigger Value:");
	private JTextField rightTrigger = new JTextField(5);
	private JLabel leftTriggerLabel = new JLabel("Left Trigger Value:");
	private JTextField leftTrigger = new JTextField(5);
	private ArrayList<JToggleButton> buttons;
	
	public enum XboxControls{ X, B, Y, BACK, START, DPAD_UP, DPAD_DOWN, DPAD_LEFT, DPAD_RIGHT, RIGHT_STICK, LEFT_STICK, 
		RIGHT_TRIGGER, LEFT_TRIGGER, RIGHT_SHOULDER, LEFT_SHOULDER
	}
	
	public enum NetworkCodes{ GENERAL_ERROR(0x09), FATAL_ERROR(0x0A), GENERAL_ERROR_STOP(0x0D), FATAL_ERROR_STOP(0x0E),
		START(0x11), STOP(0x12), CHANGE_RIGHT(0x14), CHANGE_LEFT(0x18),
		ROBOT_START(0x21), AUTO_MANUAL_TOGGLE(0x24), TEST_MODE_TOGGLE(0x28),
		RAISE_LADDER(0x41), LOWER_LADDER(0x42), RUN_LADDER(0x44), RUN_HOPPER(0x48),
		STOP_HOPPER(0x50), GET_MAP(0x81), TRANSMIT_MAP(0x82); 
	
		private final int hexCode;
		NetworkCodes(int hex) {
			hexCode = hex;
		}
		public int code() {
			return hexCode;
		}
	}
	
	
	public XBoxControllerWidget(int x, int y, String title) {
		super(x, y, title);
		JPanel buttonPanel = new JPanel();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		GridLayout grid = new GridLayout(1,0);
		buttonPanel.setLayout(grid);
		buttonPanel.setPreferredSize(new Dimension(600, 50));
		buttons=new ArrayList<JToggleButton>();
		
		buttons.add(XButton);
		buttons.add(BButton);
		buttons.add(YButton);
		buttons.add(BackButton);
		buttons.add(StartButton);
		buttons.add(DpadUp);
		buttons.add(DpadDown);
		buttons.add(DpadLeft);
		buttons.add(DpadRight);
		buttons.add(RightStickY);
		buttons.add(LeftStickY);
		buttons.add(RightTrigger);
		buttons.add(LeftTrigger);
		buttons.add(RightShoulder);
		buttons.add(LeftShoulder);
		
		JPanel dataPanel = new JPanel();
		dataPanel.add(rStickLabel);
		dataPanel.add(rightStick);
		dataPanel.add(lStickLabel);
		dataPanel.add(leftStick);
		dataPanel.add(rightTriggerLabel);
		dataPanel.add(rightTrigger);
		dataPanel.add(leftTriggerLabel);
		dataPanel.add(leftTrigger);
		
		for(JToggleButton button : buttons) {
			buttonPanel.add(button);
//			button.setEnabled(false);
			button.setSelected(true);
			
		}
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		dataPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(buttonPanel);
		add(dataPanel);
	}
	
	private NetworkCodes determineNetworkConstant(int intVal) {
		for(NetworkCodes currentCode : NetworkCodes.values()) {
			if(intVal == currentCode.code()) {
				return currentCode;				
			}
		}
		return null;
	}
	
	public void updateState(byte[] bytes) {
		for(JToggleButton button : buttons) {
			button.setEnabled(false);
			button.setSelected(true);
		}
		String received;
		try {
			received = new String(bytes, "US-ASCII");
			Integer intVal;
			try{
				intVal = Integer.parseInt(received.toString());
				NetworkCodes code = determineNetworkConstant(intVal);
				System.out.println(intVal);
				if(code!=null) {
					switch(code) {
					case AUTO_MANUAL_TOGGLE:
						BackButton.setEnabled(true);
						break;
					case CHANGE_LEFT:
						LeftStickY.setEnabled(true);
						break;
					case CHANGE_RIGHT:
						RightStickY.setEnabled(true);
						break;
					case FATAL_ERROR:
					case FATAL_ERROR_STOP:
					case GENERAL_ERROR:
					case GENERAL_ERROR_STOP:
					case TRANSMIT_MAP:
					case GET_MAP:
						break;
					case LOWER_LADDER:
						LeftTrigger.setEnabled(true);
						break;
					case RAISE_LADDER:
						LeftShoulder.setEnabled(true);
						break;
					case ROBOT_START:
						break;
					case RUN_HOPPER:
						YButton.setEnabled(true);
						break;
					case RUN_LADDER:
						RightTrigger.setEnabled(true);
						break;
					case START:
						StartButton.setEnabled(true);
						break;
					case STOP_HOPPER:
						XButton.setEnabled(true);
						break;
					case STOP:
						BButton.setEnabled(true);
					case TEST_MODE_TOGGLE:
						DpadLeft.setEnabled(true);
						break;
					}
				}
			}
			catch(NumberFormatException e) {
				System.out.println(received.toString());
			}
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
