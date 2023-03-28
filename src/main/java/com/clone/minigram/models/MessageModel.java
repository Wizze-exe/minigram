package com.clone.minigram.models;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageModel {
    Image image;
    String message;
    public boolean isOutgoing;
    public boolean isImage;
    private MessageType type;
    private String username;
    private ArrayList<UserModel> list;
    private ArrayList<UserModel> users;

    public void setImage(boolean image) {
        isImage = image;
    }

    public Image getImage() {
        return image;
    }

    public String getMessage() {
        return message;
    }

    public boolean isOutgoing() {
        return isOutgoing;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOutgoing(boolean outgoing) {
        isOutgoing = outgoing;
    }
    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    public void setUserlist(HashMap<String, UserModel> userList) {
        this.list = new ArrayList<>(userList.values());
    }
    public ArrayList<UserModel> getUserlist() {
        return list;
    }

    public ArrayList<UserModel> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserModel> users) {
        this.users = users;
    }
}
