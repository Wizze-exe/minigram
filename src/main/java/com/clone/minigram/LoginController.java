package com.clone.minigram;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    public static String userName;
    @FXML
    public TextField userNameTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void signUp(ActionEvent event) {
        try {
            userName = userNameTextField.getText();
            Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
            Scene scene = new Scene(root);
            try {
                App.stage.setScene(scene);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            try {
                App1.stage.setScene(scene);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
