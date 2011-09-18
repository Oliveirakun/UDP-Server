package br.com.nocompany.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import br.com.nocompany.beans.UDPServer;
import br.com.nocompany.interfaces.Server;

public class ServerMain {

	public static void main(String[] args){
		
		int port = 5100;		
				
		try {
			
			DatagramSocket socket = new DatagramSocket(port);
			DatagramPacket packet = new DatagramPacket(new byte[256],256);			
			Server server = new UDPServer(socket,packet);
			server.start();
			
		} 
		catch (SocketException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
