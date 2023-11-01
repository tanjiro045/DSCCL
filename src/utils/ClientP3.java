package utils;

import java.io.*;
import java.net.*;

public class ClientP3 {
	
	ClientP3(){
        try {
            InetAddress ia = InetAddress.getLocalHost();
            DatagramSocket ds = new DatagramSocket();
            byte b1[] = new byte[50];
            DatagramSocket ds1 = new DatagramSocket(1300);
            System.out.println("\nRPC Client\n");
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = br.readLine();
                byte b[] = str.getBytes();
                DatagramPacket dp = new DatagramPacket(b, b.length, ia, 1200);
                ds.send(dp);
                dp = new DatagramPacket(b1, b1.length);
                ds1.receive(dp);
                String s = new String(dp.getData(), 0, dp.getLength());
                System.out.println("\nResult = " + s);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 new ClientP3();
		

	}

}
