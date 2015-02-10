package edu.mines.blasterbotica.controlroom.widgets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class NetworkWidget extends Widget {
	
	private ArrayList<byte[]> dataBuffer;
	private JTextArea textArea;
	private JLabel label;
	private final int MAX_LINES = 100;
	public NetworkWidget(int x, int y, String title) {
		super(x, y, title);
//		setLayout(new GridLayout(0,1));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(50,125));
		setMaximumSize(new Dimension(650,125));
		dataBuffer = new ArrayList<byte[]>();
		label = new JLabel("Network Stream");
		textArea = new JTextArea(5,5);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
//		add(textArea);
		add(label, BorderLayout.PAGE_START);
		add(scrollPane, BorderLayout.PAGE_END);
		textArea.append("blargblargblargblargblarg\nblargblargblargblargblargblargblargblarg\nblargblargblargblargblargblargblarg\nblargblargblargblargblargblargb\nlargblargblargblargblargblargblarg\nblargblargblargblargblargblargblargblargblarg\nblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblargblarg");
	}

	public synchronized void addDataToBuffer(byte[] dataBytes) {
		dataBuffer.add(dataBytes);
		updateDataField();
	}
	
	private synchronized void updateDataField() {
//		StringBuilder builder = new StringBuilder();
//		String oldText = textArea.getText();
//		if(oldText != null) {
//			builder.append(oldText);
//		}
		for(byte[] dataByte:dataBuffer) {
			String converted;
			String stringVal;
			try {
				converted = new String(dataByte, "US-ASCII");
				stringVal = String.valueOf((char) Integer.parseInt(converted.toString()));
				textArea.append(stringVal);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				stringVal = "Could not convert";
			} catch (NumberFormatException E) {
				System.out.println("could not convert");
			}
			
			
		}
	}
}
