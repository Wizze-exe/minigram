package com.clone.minigram;

import com.clone.minigram.connector.Server;
import com.clone.minigram.models.MessageModel;
import com.clone.minigram.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public ListView<UserModel> usersListView;
    @FXML
    public Label chatNameLabel;
    @FXML
    public ListView<MessageModel> messageListView;
    @FXML
    public TextField messageField;

    Server con;
    private ObservableList<UserModel> usersList = FXCollections.observableArrayList();
    UserModel selectedUser, localUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String name = "Chat";
        
        Color messagesColor = Color.rgb(161, 136, 168);
        Color usersColor = Color.rgb(34, 30, 42);


        localUser = new UserModel();
        localUser.setUsername(LoginController.userName);
        localUser.setLastMessage("message");
        usersList.add(localUser);
        chatNameLabel.setText(localUser.getUsername());

        usersListView.setItems(usersList);
        usersListView.setCellFactory(param -> new UserCellController() {
            {
                prefWidthProperty().bind(usersListView.widthProperty().subtract(0));
                backgroundProperty().setValue(Background.fill(usersColor));
            }
        });
        messageListView.setCellFactory(param -> new MessageCellController() {
            {
                prefWidthProperty().bind(messageListView.widthProperty().subtract(0)); // 1
                backgroundProperty().setValue(Background.fill(messagesColor));
            }
        });
        usersListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    selectedUser = usersListView.getSelectionModel().getSelectedItem();
                    messageListView.setItems(selectedUser.messagesList);
                    chatNameLabel.setText(selectedUser.userName);
                    messageListView.scrollTo(selectedUser.messagesList.size());
            }
        );

        usersListView.getSelectionModel().select(0);
    }

    @FXML
    void sendMessage(){
        MessageModel msg = new MessageModel();
        msg.setMessage(messageField.getText());
        msg.setOutgoing(true);
        msg.setImage(false);
        msg.setImage(null);
        selectedUser.messagesList.add(msg);
        messageField.clear();
        messageListView.scrollTo(selectedUser.messagesList.size());
    }

    @FXML
    void attachFile(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            File imageFile = fileChooser.showOpenDialog(new Stage());
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            MessageModel msg = new MessageModel();
            msg.setMessage("");
            msg.setOutgoing(true);
            msg.setImage(true);
            msg.setImage(image);
            selectedUser.messagesList.add(msg);
            messageListView.scrollTo(selectedUser.messagesList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int findUser(String userName) {
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUsername().matches(userName)) {
                return i;
            }
        }
        return -1;
    }

    public void sendByEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            sendMessage();
        }
    }
}
