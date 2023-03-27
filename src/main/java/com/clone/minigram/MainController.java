package com.clone.minigram;

import com.clone.minigram.connector.Network;
import com.clone.minigram.models.MessageModel;
import com.clone.minigram.models.UserModel;
import javafx.application.Platform;
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
import java.io.InputStream;
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

    Network con;
    private ObservableList<UserModel> usersList = FXCollections.observableArrayList();
    UserModel selectedUser, localUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String name = "Chat";
        usersList.add(new UserModel(name, "message "));
        
        Color messagesColor = Color.rgb(161, 136, 168);
        Color usersColor = Color.rgb(34, 30, 42);


        localUser = new UserModel(LoginController.userName, "message");
        usersList.add(localUser);
        chatNameLabel.setText(localUser.getUserName());

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

        con = new Network(data -> Platform.runLater(() -> {
            Image image = null;
            String[] messageInfo = data.toString().split(">"); //Data type > Sender and Receiver and data
            if (messageInfo[2].matches(localUser.getUserName())) {
                if (messageInfo[0].matches("image")){
                    image = new Image((InputStream) data);
                }
                int userSender = findUser(messageInfo[1]);
                if (messageInfo[3].matches("null")) {
                    usersList.get(userSender).lastMessage.setValue(messageInfo[3]);
                }
                usersList.get(userSender).messagesList.add(new MessageModel(messageInfo[3],false, image != null, image));
                messageListView.scrollTo(selectedUser.messagesList.size());
                System.out.println("Sender: " + usersList.get(userSender).userName
                        + "\n" + "Receiver: " + localUser.getUserName()
                        + "\n" + "Image : " + image + messageInfo[0]);
            }
        }), "localhost", name.matches("Chat"),8189);
        con.openConnection();
        usersListView.getSelectionModel().select(0);
    }

    @FXML
    void sendMessage(){
        try {
            selectedUser.messagesList.add(new MessageModel(messageField.getText(), true, false, null));
            messageField.clear();
            messageListView.scrollTo(selectedUser.messagesList.size());
            con.sendData("text>" + localUser.getUserName() + ">" + selectedUser.getUserName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void attachFile(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            File imageFile = fileChooser.showOpenDialog(new Stage());
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            selectedUser.messagesList.add(new MessageModel("", true, true, image));
            messageListView.scrollTo(selectedUser.messagesList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int findUser(String userName) {
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUserName().matches(userName)) {
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
