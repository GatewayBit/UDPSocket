package udpsocket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

/**
 *
 * @author joomlah
 */
public class ServerThread extends Thread {

    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;

    public ServerThread() throws IOException {
        this("ServerThread");
    }

    public ServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(7896);

        try {
            in = new BufferedReader(new FileReader("quoteList.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Could not open \"quoteList.txt\". Giving time instead.");
        }
    }

    @Override
    public void run() {

        while (moreQuotes) {
            try {
                byte[] buf = new byte[256];

                // Receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                // Figure out response
                String dString;
                if (in == null) {
                    dString = new Date().toString();
                } else {
                    dString = getNextQuote();
                }

                buf = dString.getBytes();

                // Send the response to the client at "address" and "port"
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }

    protected String getNextQuote() {
        String returnValue;
        try {
            if ((returnValue = in.readLine()) == null) {
                in.close();
                moreQuotes = false;
                returnValue = "No more things to read. Bye.";
            }
        } catch (IOException e) {
            returnValue = "IOException occurrred in server.";
        }
        return returnValue;
    }

}
