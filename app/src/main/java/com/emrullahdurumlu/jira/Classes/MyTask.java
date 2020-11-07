package com.emrullahdurumlu.jira.Classes;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FieldValue;

public class MyTask {
    @Exclude private String id;
    private String ownerTask;
    private int priority;
    private String taskHeader;
    private String taskComment;
    private boolean isFinished;
    private FieldValue time;

    public MyTask(){}
    public MyTask(String id,String ownerTask, int priority, String taskHeader, String taskComment, boolean isFinished) {
        this.id=id;
        this.ownerTask = ownerTask;
        this.priority = priority;
        this.taskHeader = taskHeader;
        this.taskComment = taskComment;
        this.isFinished = isFinished;
    }

    public MyTask(String ownerTask, int priority, String taskHeader, String taskComment, boolean isFinished) {
        this.ownerTask = ownerTask;
        this.priority = priority;
        this.taskHeader = taskHeader;
        this.taskComment = taskComment;
        this.isFinished = isFinished;
    }

    public void setTime(FieldValue time) {
        this.time = time;
    }

    public FieldValue getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerTask() {
        return ownerTask;
    }

    public void setOwnerTask(String ownerTask) {
        this.ownerTask = ownerTask;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTaskHeader() {
        return taskHeader;
    }

    public void setTaskHeader(String taskHeader) {
        this.taskHeader = taskHeader;
    }

    public String getTaskComment() {
        return taskComment;
    }

    public void setTaskComment(String tasxComment) {
        this.taskComment = tasxComment;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
