package br.com.nocompany.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.junit.Test;

import br.com.nocompany.beans.UDPServer;
import br.com.nocompany.interfaces.Server;

public class UDPServerTest {

	@Test
	public void test() throws IOException {
		
		DatagramSocket socketMock = mock(DatagramSocket.class);
		
		//Mockito cant mock the DatagramPacket class
		DatagramPacket message = new DatagramPacket(new byte[256],256);
		
//		Server serverMock = mock(UDPServer.class);
		Server server = new UDPServer(socketMock, message);
		server.start();
		
		verify(socketMock).receive(message);			
		
		
		
	}

}
