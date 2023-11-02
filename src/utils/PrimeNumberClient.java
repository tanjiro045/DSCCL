package utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class PrimeNumberClient {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();

        while (true) {
            System.out.print("Enter a number to check if it's prime (or 'bye' to exit): ");
            String input = sc.nextLine();

            byte[] sendData = input.getBytes();
            DatagramPacket dpSend = new DatagramPacket(sendData, sendData.length, ip, 1234);
            ds.send(dpSend);

            if (input.equals("bye")) {
                break;
            }

            byte[] receiveData = new byte[65535];
            DatagramPacket dpReceive = new DatagramPacket(receiveData, receiveData.length);
            ds.receive(dpReceive);

            String result = new String(dpReceive.getData(), 0, dpReceive.getLength()).trim();
            System.out.println(input + (result.equals("true") ? " is prime." : " is not prime."));
        }

        ds.close();
    }
}
