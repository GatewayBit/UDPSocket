package udpsocket;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joomlah
 */
public class Start {
    
    private static final Logger log = Logger.getLogger(Start.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner sc = new Scanner(System.in);

        System.out.println("1) Server");
        System.out.println("2) Client");
        System.out.println("3) Quit");

        System.out.print(":> ");

        boolean isRunning = true;
        String input = sc.nextLine();

        do {
            if (input.equalsIgnoreCase("Server") || input.equalsIgnoreCase("1")) {
                StartServer();
                break;
            } else if (input.equalsIgnoreCase("Client") || input.equalsIgnoreCase("2")) {
                StartClient();
                break;
            } else if (input.equalsIgnoreCase("Quit") || input.equalsIgnoreCase("3")) {
                isRunning = false;
            } else {
                System.out.println("Wrong Input");
                System.out.print(":> ");
                input = sc.nextLine();
            }
        } while (isRunning);
    }

    public static void StartServer() {
        log.log(Level.INFO, "Starting Server.");
        //Server.startSingleUDP();
        Server.startMultiUDP();
    }

    public static void StartClient() {
        log.log(Level.INFO, "Starting Client");
        try {
            Client.startUDPMulti();
        } catch (IOException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
