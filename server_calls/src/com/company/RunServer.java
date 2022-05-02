package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class RunServer {

    public static void main(String[] args) {

	// write your code here
    try {
        DatagramSocket socket = new DatagramSocket(8080);
        System.out.println("Server is running");

        while (true) {
            byte[] buffer = new byte[100];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Recieved Text from user " + message);

            String response = "echo: " + new String(buffer, 0, packet.getLength());
            byte[] responseBuffer = response.getBytes();
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(responseBuffer, responseBuffer.length, address, port);
            socket.send(packet);
        }
    } catch (SocketException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
        }
    }
}
