package ChatServer;

import java.util.Scanner;
import java.util.logging.Logger;

public class Server {
    private static Logger logger = Logger.getLogger(Server.class.getName());
    private static final int PORT = 3030;

    public static void main(String[] args) {
        ChatServer server = new ChatServer(PORT);
        new Thread(server).start();
        System.out.println("Server started, press q for exit");
        logger.info("Server started...");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if ("q".equalsIgnoreCase(scanner.nextLine())) {
                logger.info("Server stopped...");
                System.exit(0);
            }
        }
    }

}