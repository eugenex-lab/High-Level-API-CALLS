package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class UserSayInput {

    public static void main(String[] args) {

        try {
            InetAddress address = InetAddress.getLocalHost();  //get the address of the local host
            System.out.println(address.getHostAddress());
            DatagramSocket datagramSocket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
            String echoString;
            System.out.println("Enter your name: ");

            do {
                System.out.println("Mesaaged to be sent and echoed: ");
                echoString = scanner.nextLine();

                byte[] echoBytes = echoString.getBytes();

                DatagramPacket echoPacket = new DatagramPacket(echoBytes, echoBytes.length, address, 8080);
                datagramSocket.send(echoPacket);

                byte[] receiveData = new byte[1024];
                echoPacket = new DatagramPacket(receiveData, receiveData.length);
                datagramSocket.receive(echoPacket);
                System.out.println("Echoed: " + new String(receiveData, 0, echoPacket.getLength()));

            } while (!echoString.equals("exit"));

        } catch (SocketTimeoutException e) {
            System.out.println(
                    "Socket timed out! No response from server!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}