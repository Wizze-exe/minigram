package com.clone.minigram;

import com.clone.minigram.models.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class UserCellController extends ListCell<UserModel> {
    @FXML
    private GridPane root;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label lastMessageLabel;

    @Override
    protected void updateItem(UserModel item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userCell.fxml"));
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            userNameLabel.setText(String.valueOf(item.getUserName()));
            lastMessageLabel.setText(String.valueOf(item.getLastMessage()));
            setGraphic(root);
        }
    }
}
