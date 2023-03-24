package com.clone.minigram.connector;

import javafx.scene.image.Image;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Network {
    private ConnectionThread connectionThread = new ConnectionThread();
    public Consumer<Serializable> receiveCallBack;
    public String ip = "localhost";
    public boolean isServer;
    public int port = 8188;

    public Network(Consumer<Serializable> receiveCallBack, String ip, boolean isServer, int port) {
        this.receiveCallBack = receiveCallBack;
        this.ip = ip;
        this.isServer = isServer;
        this.port = port;
    }

    public void openConnection() {
        connectionThread.start();
    }

    public void sendData(Serializable data) throws IOException {
        connectionThread.outputStream.writeObject(data);
    }

    public void sendImage(Image image) throws IOException {
        connectionThread.outputStream.defaultWriteObject();
        connectionThread.outputStream.writeObject(image);
    }

    public void closeConnection() throws IOException {
        connectionThread.socket.close();
    }

    private class ConnectionThread extends Thread {
        private Socket socket;
        private ObjectOutputStream outputStream;

        @Override
        public void run() {
            super.run();
            try {
                ServerSocket server = isServer ? new ServerSocket(port) : null;
                Socket socket = isServer ? server.accept() : new Socket(ip, port);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                socket.setTcpNoDelay(true);
                this.socket = socket;
                this.outputStream = outputStream;
                while (true) {
                    Serializable data = (Serializable) inputStream.readObject();
                    receiveCallBack.accept(data);
                    break;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
