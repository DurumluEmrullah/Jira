package com.emrullahdurumlu.jira.Classes;

import java.util.ArrayList;

public class Developer {
    private String email;
    private String name;
    private String lastName;
    private ArrayList<MyTask> currentTask;
    private ArrayList<MyTask> finishedTask;

    public Developer(String email,String name, String lastName) {
        this.email=email;
        this.name = name;
        this.lastName = lastName;
        currentTask=new ArrayList<>();
        finishedTask=new ArrayList<>();
    }
    public String getEmail(){
        return email;
    }
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<MyTask> getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(ArrayList<MyTask> currentTask) {
        this.currentTask = currentTask;
    }

    public ArrayList<MyTask> getFinishedTask() {
        return finishedTask;
    }

    public void setFinishedTask(ArrayList<MyTask> finishedTask) {
        this.finishedTask = finishedTask;
    }
}
