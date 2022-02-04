package com.ewan.TaskManager;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

class Task {
    private String title;
    private LocalDate date;
    private LocalTime time;
    private String status = "";

    public Task(String title, LocalDate date, LocalTime time) {
        this.title = title;
        this.date = date;
        this.time = time;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void print() {
        System.out.println("title: " + title +
        " " + status + " " +
        "Date: " + date +
        " Time: " + time);
    }
}
