package com.clone.minigram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("loginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 189);
        primaryStage.setScene(scene);
        stage = primaryStage;
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}