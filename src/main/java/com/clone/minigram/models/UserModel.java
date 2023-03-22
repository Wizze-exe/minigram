package com.clone.minigram.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class UserModel {
    public String userName;
    public SimpleStringProperty lastMessage;
    public SimpleStringProperty time;
    public SimpleStringProperty notificationNumber;
    public Image avatarImage;
    public ObservableList<MessageModel> messagesList;

    public UserModel(String userName, SimpleStringProperty lastMessage, SimpleStringProperty time, SimpleStringProperty notificationNumber, Image avatarImage) {
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.time = time;
        this.notificationNumber = notificationNumber;
        this.avatarImage = avatarImage;
        messagesList = FXCollections.observableArrayList();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastMessage() {
        return lastMessage.get();
    }

    public SimpleStringProperty lastMessageProperty() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage.set(lastMessage);
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public String getNotificationsNumber() {
        return notificationNumber.get();
    }

    public SimpleStringProperty notificationsNumberProperty() {
        return notificationNumber;
    }

    public void setNotificationsNumber(String notificationsNumber) {
        this.notificationNumber.set(notificationsNumber);
    }

    public Image getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(Image avatarImage) {
        this.avatarImage = avatarImage;
    }
}
