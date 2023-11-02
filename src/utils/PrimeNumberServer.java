package utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PrimeNumberServer {

    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;

        if (number % 2 == 0 || number % 3 == 0) return false;

        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(1234);

        while (true) {
            byte[] receiveData = new byte[65535];
            DatagramPacket dpReceive = new DatagramPacket(receiveData, receiveData.length);
            ds.receive(dpReceive);

            String input = new String(dpReceive.getData(), 0, dpReceive.getLength()).trim();
            System.out.println("Number Received: " + input);

            if (input.equals("bye")) {
                System.out.println("Client sent closing");
                break;
            }

            try {
                int number = Integer.parseInt(input);
                boolean result = isPrime(number);
                String resultString = String.valueOf(result);

                byte[] sendData = resultString.getBytes();
                DatagramPacket dpSend = new DatagramPacket(sendData, sendData.length, dpReceive.getAddress(), dpReceive.getPort());
                ds.send(dpSend);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input received. Please send a valid number.");
            }
        }

        ds.close();
    }
}
