package ChatServer;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class ChatServerClient implements Runnable {

    private BufferedReader socketReader;
    private BufferedWriter socketWriter;
    private ChatServer server;

    private static Logger logger = Logger.getLogger(ChatServerClient.class.getName());

    public ChatServerClient(Socket socket, ChatServer serverSocket) throws IOException {
        this.server = serverSocket;
        socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = socketReader.readLine();
                server.sendMessageForAll(message);
            }
        } catch (IOException ioe) {
            logger.info("Server error: " + ioe.getMessage());
            ioe.printStackTrace();
        }

    }

    public void sendMessage(String message) {
        try {
            socketWriter.write(message + "\n");
            socketWriter.flush();
        } catch (IOException ioe) {
            logger.info("Server error: " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }
}

