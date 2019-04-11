package ChatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ChatServer implements Runnable{
    private static Logger logger = Logger.getLogger(ChatServer.class.getName());
    private ServerSocket serverSocket;
    private List<ChatServerClient> clients = new ArrayList<>();

    public ChatServer(int port) {
        try{
           serverSocket = new ServerSocket(port);
            logger.info("Сокет проинициализирован");
        } catch (IOException e) {
            logger.info("Проблемы с портом: " + port);
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true) {
            try{
                ChatServerClient client = new ChatServerClient(serverSocket.accept(), this);
                clients.add(client);
                new Thread(client).start();
                logger.info("Связь с клиентом установлена");
            } catch (IOException e) {
                logger.info("Не могу соединится с клиентом");
                e.printStackTrace();
            }
        }
    }

    public synchronized void sendMessageForAll(String message) {
        for (ChatServerClient client : clients) {
            client.sendMessage(message);
        }
    }
}

