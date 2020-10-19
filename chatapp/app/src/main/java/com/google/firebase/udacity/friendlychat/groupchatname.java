package com.google.firebase.udacity.friendlychat;

import java.util.ArrayList;

public class groupchatname {
    private String chatname;
    private int nummembers;
    private ArrayList<String> chatmembers;

    public groupchatname(String chatname, int nummembers, ArrayList<String> chatmembers) {
        this.chatname = chatname;
        this.nummembers = nummembers;
        this.chatmembers = chatmembers;
    }

    public String getChatname() {
        return chatname;
    }

    public void setChatname(String chatname) {
        this.chatname = chatname;
    }

    public int getNummembers() {
        return nummembers;
    }

    public void setNummembers(int nummembers) {
        this.nummembers = nummembers;
    }

    public ArrayList<String> getChatmembers() {
        return chatmembers;
    }

    public void setChatmembers(ArrayList<String> chatmembers) {
        this.chatmembers = chatmembers;
    }
}
