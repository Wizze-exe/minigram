package com.clone.minigram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Sarvar extends Application {
    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Sarvar.class.getResource("sarvarView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,400);
        primaryStage.setScene(scene);
        stage = primaryStage;
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}