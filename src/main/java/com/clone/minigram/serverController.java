package com.clone.minigram;

import com.clone.minigram.connector.Server;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class serverController {
    Server connection;

    public VBox serverMessages;

    public void startServer(ActionEvent actionEvent) {

        serverMessages.getChildren().add(new Label("Сервер запущен"));
    }

    public void stopServer(ActionEvent actionEvent) {

        serverMessages.getChildren().add(new Label("Сервер отключен"));
    }
}
