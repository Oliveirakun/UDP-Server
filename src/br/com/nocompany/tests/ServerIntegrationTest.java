package br.com.nocompany.tests;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import junit.framework.Assert;

import org.junit.Test;

import br.com.nocompany.beans.UDPServer;
import br.com.nocompany.interfaces.Server;

public class ServerIntegrationTest {

	private int port = 5100;
	
	@Test
	public void test() throws IOException {
		
		DatagramSocket socket = new DatagramSocket(port);
		DatagramPacket packet = new DatagramPacket(new byte[256],256);
		
		final Server server = new UDPServer(socket,packet);
		
		new Thread(new Runnable(){
		
			public void run(){
			
				try {
					server.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
		
		}}).start();
		
		String resposta = sendMessage();
		server.stop();
		
		Assert.assertEquals("ok", resposta);
		
	}

	
	private String sendMessage() {
		
			
			try {	
			
				DatagramSocket socket = new DatagramSocket();
		
				//Send message to the broadcast address
				InetAddress ipAddress = InetAddress.getByName("255.255.255.255");
			
				String message = "hello";
				DatagramPacket messagePacket = new DatagramPacket(message.getBytes(),message.length(),ipAddress,port);
				socket.send(messagePacket);
		
				DatagramPacket receivePacket = new DatagramPacket(new byte[256],256);
		
				socket.setSoTimeout(3000);
				
				socket.receive(receivePacket);
				String responseMessage = new String(receivePacket.getData()).trim();
				return responseMessage;
		
			}
			catch(SocketTimeoutException e){
				System.out.println("Time expired");
				return "";
			}
			catch (SocketException e) {
				e.printStackTrace();
				return "";
			}
			catch (UnknownHostException e) {
				e.printStackTrace();
				return "";
			}
			catch (IOException e) {
				e.printStackTrace();
				return "";
			} 
					
	}
}
