package com.clone.minigram.models;

import javafx.scene.image.Image;

public class MessageModel {
    Image image;
    String message;
    public boolean isOutgoing;
    public boolean isImage;

    public MessageModel(String message, boolean isOutgoing, boolean isImage, Image image) {
        this.image = image;
        this.message = message;
        this.isOutgoing = isOutgoing;
        this.isImage = isImage;
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
}
