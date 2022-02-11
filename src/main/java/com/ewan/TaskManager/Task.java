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
    static int idGenerator = 0;
    static DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
    static DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("hh:mm");

    enum Status {
        PENDING,
        DONE,
        UNSPECIFIED;
    }

    private Status taskStatus = Status.PENDING;

    public Task(int id, String title, LocalDate date, LocalTime time) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
    }

    static Task build(String title, String date, String time) {
        LocalDate parsedDate = LocalDate.parse(date, dateFormatter);
        LocalTime parsedTime = LocalTime.parse(time, timeFormatter);
        idGenerator++;
        int id = idGenerator;
        return new Task(id, title, parsedDate, parsedTime);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return taskStatus;
    }

    public void setStatus(Status s) {
        taskStatus = s;
    }

    public void print() {
        System.out.println(
                "Task id: " + id + " " +
                        "title: " + "\"" + title + "\"" +
                        " " + taskStatus + " " +
                        "Date: " + date +
                        " Time: " + time);
    }
}
