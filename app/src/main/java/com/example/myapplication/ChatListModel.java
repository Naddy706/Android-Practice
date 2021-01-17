package com.example.myapplication;

public class ChatListModel {

    private int imageIcon;
     String Title;
     String Body;

    public ChatListModel(int imageIcon, String Title, String Body) {
        this.imageIcon = imageIcon;
        this.Title =Title;
        this.Body = Body ;

    }
    public int getImageIcon() {
        return imageIcon;
    }



    public String getTitle() {
        return Title;
    }



    public String getBody() {
        return Body;
    }




}
