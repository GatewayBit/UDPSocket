package udpsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author joomlah
 */
public class Client {
    
    public static void startUDP() throws IOException {
        // get a datagram socket
        DatagramSocket socket = new DatagramSocket();
        
        // send request
        byte[] buf = new byte[256];
        final int PORT = 7896;
        InetAddress address = InetAddress.getByName("192.168.1.23");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
        socket.send(packet);
        
        // Get response
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        
        // Display response
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Quote: " + received);
        
        socket.close();
    }
    
    public static void startUDPMulti() throws IOException {
        
        MulticastSocket socket = new MulticastSocket(7899);
        InetAddress address = InetAddress.getByName("230.0.123.0");
        socket.joinGroup(address);
        
        DatagramPacket packet;
        
        for(int i = 0; i < 5; i++) {
            byte[] buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Quote of the moment: " + received);
        }
        
        socket.leaveGroup(address);
        socket.close();
    }
}
