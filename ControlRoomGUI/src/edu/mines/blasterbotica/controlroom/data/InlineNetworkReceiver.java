package edu.mines.blasterbotica.controlroom.data;

import java.util.ArrayList;

import edu.mines.blasterbotica.controlroom.widgets.NetworkWidget;
import edu.mines.blasterbotica.controlroom.widgets.XBoxControllerWidget;
import edu.mines.blasterbotica.server.NetworkRunner;

public class InlineNetworkReceiver implements Runnable{

	private int localControllerPort;
//	private int sendToControllerProgramPort;
	private int remotePort;
	private String remoteIp;
	private NetworkRunner runner;
	private ArrayList<byte[]> receivedLocalDataBuffer;
	private ArrayList<byte[]> receivedRemoteDataBuffer;
	private XBoxControllerWidget controllerwidget;
	private NetworkWidget networkwidget;
	public InlineNetworkReceiver(String remoteIp, int localToRemotePort, int remoteToLocalPort, NetworkWidget networkwidget, XBoxControllerWidget controllerwidget) {
		localControllerPort = localToRemotePort;
		remotePort = remoteToLocalPort;
		this.remoteIp = remoteIp;
		this.controllerwidget=controllerwidget;
		this.networkwidget=networkwidget;
		receivedLocalDataBuffer=new ArrayList<byte[]>();
		receivedRemoteDataBuffer=new ArrayList<byte[]>();
		runner = new NetworkRunner(this.remoteIp, localControllerPort, remotePort);
	}
	
	@Override
	public void run() {
		Thread runnerThread = new Thread() {
			public void run() {
				runner.runNetwork(receivedRemoteDataBuffer, receivedLocalDataBuffer, this);
			}
		};
		runnerThread.start();
		while(true) {
//			runner.notifyAll();
			synchronized(runner) {
				
				if(receivedLocalDataBuffer.size() > 0) {
					System.out.println("here................................................................");
					for(byte[] bytes:receivedLocalDataBuffer) {
						controllerwidget.updateState(bytes);
						
					}
//					receivedLocalDataBuffer.clear();
					receivedLocalDataBuffer = new ArrayList<byte[]>();
				}
				if(receivedRemoteDataBuffer.size() > 0) {
					for(byte[] bytes:receivedRemoteDataBuffer) {
						networkwidget.addDataToBuffer(bytes);
					}
//					receivedRemoteDataBuffer.clear();
					receivedRemoteDataBuffer = new ArrayList<byte[]>();
				}
			}
			try {
				synchronized(this) {
//					System.out.println("here................................................................");
					wait(50);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
