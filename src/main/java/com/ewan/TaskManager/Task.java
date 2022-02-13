package com.ewan.TaskManager;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

class Task {
    private final int id;
    private String title;
    private LocalDate date;
    private LocalTime time;
    static DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
    static DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("hh:mm");

    enum Status {
        PENDING,
        DONE,
        UNSPECIFIED;
    }

    private Status status = Status.PENDING;

    public Task(int id, String title, LocalDate date, LocalTime time) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
    }

    static Task build(int id, String title, String date, String time) {
        LocalDate parsedDate = LocalDate.parse(date, dateFormatter);
        LocalTime parsedTime = LocalTime.parse(time, timeFormatter);
        return new Task(id, title, parsedDate, parsedTime);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status s) {
        status = s;
    }

    public void print() {
        System.out.println(
                "Task id: " + id + " " +
                        "Title: " + "\"" + title + "\" " +
                        "Status: \"" + status + "\" " +
                        "Date: " + date + " " +
                        " Time: " + time);
    }
}
