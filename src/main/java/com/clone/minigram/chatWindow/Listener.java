package com.clone.minigram.chatWindow;

import com.clone.minigram.LoginController;
import com.clone.minigram.MainController;
import com.clone.minigram.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

import static com.clone.minigram.models.MessageType.CONNECTED;


public class Listener implements  Runnable {

    private static final String HASCONNECTED = "has connected";
    private Socket socket;
    public static String username;
    public MainController controller;
    private static ObjectOutputStream oos;
    private InputStream is;
    private ObjectInputStream input;
    private OutputStream outputStream;
    Logger logger = LoggerFactory.getLogger(Listener.class);

    public Listener(String username, MainController controller) {
        Listener.username = username;
        this.controller = controller;
    }

    public void run() {
        try {
            socket = new Socket("localhost", 8189);
            LoginController.getInstance().showScene();
            outputStream = socket.getOutputStream();
            oos = new ObjectOutputStream(outputStream);
            is = socket.getInputStream();
            input = new ObjectInputStream(is);
        } catch (IOException e) {
            LoginController.getInstance().showErrorDialog("Could not connect to server");
            logger.error("Could not Connect");
        }
        logger.info("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

        try {
            connect();
            logger.info("Sockets in and out ready!");
            while (socket.isConnected()) {
                MessageModel message = null;
                message = (MessageModel) input.readObject();

                if (message != null) {
                    logger.debug("Message recieved:" + message.getMessage() + " MessageType:" + message.getType() + "Name:" + message.getUsername());
                    switch (message.getType()) {
                        case USER:
                            controller.addToChat(message);
                            break;
                        case NOTIFICATION:
                            controller.newUserNotification(message);
                            break;
                        case SERVER:
                            controller.addAsServer(message);
                            break;
                        case CONNECTED:
                            controller.setUserList(message);
                            break;
                        case DISCONNECTED:
                            controller.setUserList(message);
                            break;
                        case STATUS:
                            controller.setUserList(message);
                            break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            controller.logoutScene();
        }

    }

    public static void send(String msg) throws IOException {
        MessageModel createMessage = new MessageModel();
        createMessage.setUsername(username);
        createMessage.setType(MessageType.USER);
        createMessage.setMessage(msg);
        oos.writeObject(createMessage);
        oos.flush();
    }

    public static void connect() throws IOException {
        MessageModel createMessage = new MessageModel();
        createMessage.setUsername(username);
        createMessage.setType(CONNECTED);
        createMessage.setMessage(HASCONNECTED);
        oos.writeObject(createMessage);
    }
}
