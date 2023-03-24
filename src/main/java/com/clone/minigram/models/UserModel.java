package com.clone.minigram.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel {
    public String userName;
    public SimpleStringProperty lastMessage;
    public SimpleStringProperty notificationNumber;
    public ObservableList<MessageModel> messagesList;

    public UserModel(String userName, String lastMessage, String notificationNumber) {
        this.userName = userName;
        this.lastMessage = new SimpleStringProperty(lastMessage);
        this.notificationNumber = new SimpleStringProperty(notificationNumber);
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

    public String getNotificationsNumber() {
        return notificationNumber.get();
    }

    public SimpleStringProperty notificationsNumberProperty() {
        return notificationNumber;
    }

    public void setNotificationsNumber(String notificationsNumber) {
        this.notificationNumber.set(notificationsNumber);
    }
}
