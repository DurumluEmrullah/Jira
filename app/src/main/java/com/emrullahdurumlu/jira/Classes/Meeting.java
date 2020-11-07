package com.emrullahdurumlu.jira.Classes;

import com.google.firebase.firestore.FieldValue;

public class Meeting {
    private String time;
    private String date;
    private String owner;
    private String topic;
    private FieldValue addTime;

    public Meeting(String time, String date, String owner, String topic, FieldValue addTime) {
        this.time = time;
        this.date = date;
        this.owner = owner;
        this.topic = topic;
        this.addTime = addTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public FieldValue getAddTime() {
        return addTime;
    }

    public void setAddTime(FieldValue addTime) {
        this.addTime = addTime;
    }
}
