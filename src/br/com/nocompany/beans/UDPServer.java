package br.com.nocompany.beans;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import br.com.nocompany.interfaces.Server;

public class UDPServer implements Server {

	private DatagramSocket server;
	private DatagramPacket message;
	
	
	public UDPServer(DatagramSocket server, DatagramPacket message){
		
		this.server = server;
		this.message = message;
		
	}
	
	@Override
	public void start() throws IOException {

		System.out.println("Server started....");
		server.receive(message);
		threatMessage();		
		
	}

	@Override
	public void stop() {

		server.close();		
		
	}
	
	private void threatMessage() throws IOException{
		
		String messageReceived = new String(message.getData()).trim();
		System.out.println("Message Received: "+messageReceived);
		
		if(messageReceived.equals("hello")){
			
			InetAddress address = message.getAddress();
			int port = message.getPort();
			
			String sendMessage = "ok";
			byte[] byteSendMessage = sendMessage.getBytes();
			
			DatagramPacket answerPacket = new DatagramPacket(byteSendMessage,byteSendMessage.length,address,port);
			
			server.send(answerPacket);
		}
		
		
	}

}
