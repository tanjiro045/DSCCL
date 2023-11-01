package utils;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;

public class ServerP2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket ds = new DatagramSocket(1234);
		byte[] buf = null;
		DatagramPacket DpReceive = null;
		DatagramPacket DpSend = null;
		while (true) {
		buf = new byte[65535];
		DpReceive = new DatagramPacket(buf, buf.length);
		ds.receive(DpReceive);
		String inp = new String(buf, 0, buf.length);
		inp = inp.trim();
		System.out.println("Equation Received:- " + inp);
		if (inp.equals("close")) {
			System.out.println("Client sent closing");
			break;
		}
		int result;
		StringTokenizer st = new StringTokenizer(inp);
		int oprnd1 = Integer.parseInt(st.nextToken());
		String operation = st.nextToken();
		int oprnd2 = Integer.parseInt(st.nextToken());
		// Perform the required operation
		if (operation.equals("+"))
			result = oprnd1 + oprnd2;
		else if (operation.equals("-"))
			result = oprnd1 - oprnd2;
		else if (operation.equals("*"))
			result = oprnd1 * oprnd2;
		else
			result = oprnd1 / oprnd2;
		System.out.println("Sending the result...");
		String res = Integer.toString(result);
		buf = res.getBytes();
		int port = DpReceive.getPort();
		DpSend = new DatagramPacket(
		buf, buf.length, InetAddress.getLocalHost(), port);
		ds.send(DpSend);
		}
		

	}

}
