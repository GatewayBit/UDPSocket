package udpsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joomlah
 */
public class MulticastThread extends ServerThread {
    
    private final long FIVE_SECONDS = 5000;
    
    public MulticastThread() throws IOException {
        super("MulticastServerThread");
    }
    
    @Override
    public void run() {
        while(moreQuotes) {
            try {
                byte[] buf = new byte[256];
                
                // Construct quote
                String dString = null;
                if (in == null)
                    dString = new Date().toString();
                else
                    dString = getNextQuote();
                buf = dString.getBytes();
                
                // Send it
                InetAddress group = InetAddress.getByName("230.0.123.0");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 7899);
                socket.send(packet);
                
                // Sleep for a while
                try {
                    sleep((long)(Math.random() * FIVE_SECONDS));
                } catch (InterruptedException e) {
                    // This is most likely 100% wrong
                    Logger.getLogger(MulticastThread.class.getName()).log(Level.SEVERE, null, e);
                }
            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }
}
