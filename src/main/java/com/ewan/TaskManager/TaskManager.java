package com.ewan.TaskManager;

import java.util.HashMap;
import com.ewan.TaskManager.Task.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.joda.time.LocalTime;
import java.io.*;
import java.lang.reflect.Type;
import org.joda.time.LocalDate;

class TaskManager {
  private final HashMap<Integer, Task> tasks = new HashMap<Integer, Task>();
  static int idGenerator = 0;

  private int findMaxTaskId(HashMap<Integer, Task> map) {
    int maxId = 0;
    for (Task task : map.values()) {
      if (task.getId() > maxId) {
        maxId = task.getId();
      }
    }
    return maxId;
  }

  public HashMap<Integer, Task> getTasks() {
    return tasks;
  }

  public void add(String title, String date, String time) {
    idGenerator++;
    Task task = Task.build(idGenerator, title, date, time);
    tasks.put(task.getId(), task);
  }

  public void viewAll() {
    for (Task task : tasks.values()) {
      task.print();
    }
  }

  public void updateTaskStatus(int taskId, String status) {
    tasks.get(taskId)
        .setStatus(Status.valueOf(status.toUpperCase()));
  }

  public void save(String fileName) {
    Gson jsonObject = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
        .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
        .create();

    String jsonObjectAsString = jsonObject.toJson(tasks);

    try {
      FileWriter fileWriter = new FileWriter(fileName);
      fileWriter.write(jsonObjectAsString);
      fileWriter.flush();
      fileWriter.close();
    }

    catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public void load(String file) {

    String lines = "";
    try {
      String line = null;
      BufferedReader buffRead = new BufferedReader(new FileReader(file));
      while ((line = buffRead.readLine()) != null) {
        lines += line;
      }
      buffRead.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
    Type mapType = new TypeToken<HashMap<Integer, Task>>() {
    }.getType();
    HashMap<Integer, Task> loadedTasks = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
        .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
        .create()
        .fromJson(lines, mapType);

    if (loadedTasks != null) {
      tasks.putAll(loadedTasks);
    } else {
      return;
    }
    TaskManager.idGenerator = findMaxTaskId(tasks);
  }
}
