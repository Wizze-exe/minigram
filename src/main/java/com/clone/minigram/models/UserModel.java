package com.clone.minigram.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel {
    public String userName;
    public SimpleStringProperty lastMessage;
    public ObservableList<MessageModel> messagesList;

    public UserModel(String userName, String lastMessage) {
        this.userName = userName;
        this.lastMessage = new SimpleStringProperty(lastMessage);
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
}
