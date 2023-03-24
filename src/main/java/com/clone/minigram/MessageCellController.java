package com.clone.minigram;

import com.clone.minigram.models.MessageModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MessageCellController extends ListCell<MessageModel> {
    private GridPane root;
    private ImageView imageView;
    private Label messageLabel;
    @Override
    protected void updateItem(MessageModel item, boolean empty){
        super.updateItem(item, empty);
        FXMLLoader fxmlLoader;
        if (empty || item == null) {
            setGraphic(null);
            setText(null);
        } else {
            if (item.isOutgoing) {
                if (item.isImage) {
                    fxmlLoader = new FXMLLoader(getClass().getResource("outgoingImageCell.fxml"));
                } else {
                    fxmlLoader = new FXMLLoader(getClass().getResource("outgoingMessageCell.fxml"));
                }
            } else {
                if (item.isImage) {
                    fxmlLoader = new FXMLLoader(getClass().getResource("incomingImageCell.fxml"));
                } else {
                    fxmlLoader = new FXMLLoader(getClass().getResource("incomingMessageCell.fxml"));
                }
            }
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (item.isImage) imageView.setImage(item.getImage());
            else messageLabel.setText(item.getMessage());
            setGraphic(root);
        }
    }
}
