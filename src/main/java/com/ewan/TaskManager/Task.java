package com.ewan.TaskManager;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

class Task {
    private final int identification;
    private String title;
    private LocalDate date;
    private LocalTime time;
    enum Status {
        PENDING,
        DONE,
        UNSPECIFIED;  
    }
    private Status taskStatus = Status.PENDING;

    public Task( int id, String title, LocalDate date, LocalTime time) {
        identification = id;
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return identification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = this.title + title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return taskStatus;
    }

    public void setStatus(Status s) {
        taskStatus = s;
    }

    public void print() {
        System.out.println(
        "Task id: " + identification + " " +
        "title: " + "\"" + title + "\"" +       
        " " + taskStatus + " " +
        "Date: " + date +
        " Time: " + time
        );
    }
}
