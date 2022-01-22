package com.ewan.TaskManager;

//import java.util.*;
import java.io.*;
//import java.time.*;

class Task implements Serializable {
    private String title;
    private String date;
    private String time;
    private String status = "";

    public Task(String title, String date, String time) {
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String s) {
        title = title + s;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String s) {
        date = s;
    }

    public void setTime(String t) {
        time = t;
    }

    public void setStatus(String s) {
        status = s;
    }

    public void print() {
        System.out.println("title: " + title + status + " date: " +
                date + " time:" + time);
    }
}
