package com.ewan.TaskManager;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.ewan.TaskManager.Task.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.io.*;
import java.lang.reflect.Type;
import org.joda.time.LocalDate;

class TaskManager {
  private final List<Task> taskStorage = new ArrayList<Task>();
  private static int taskId = 0;
  private final HashMap<Integer, Task> tasks = new HashMap<Integer, Task>();

  private void findMaxTaskId(List<Task> list) {
    for(Task t: list) {
        if(t.getId() > taskId){
          taskId = t.getId();
        }
    }
  }

  private Task stringToJodaTask(String title, String dateAsText, String timeAsText) {
    DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
    LocalDate parsedDate = LocalDate.parse(dateAsText, fmt);
    LocalTime parsedTime = LocalTime.parse(timeAsText);
    taskId++;
    return new Task(taskId, title, parsedDate, parsedTime);
  }

  public void add(String title, String date, String time) {
    
    Task t = stringToJodaTask(title, date, time);
    taskStorage.add(t);
    tasks.put(taskId, t);
  }

  public void viewAll() {
    for (Task task : taskStorage) {
      task.print();
    }
  }
  public void defineTaskStatus(int taskId, String status) {
    Task oneTask = tasks.get(taskId);
    try {
      switch(status.toUpperCase()) {
        case "PENDING":
          oneTask.setStatus(Status.valueOf(status.toUpperCase()));
          break;
        case "DONE":
          oneTask.setStatus(Status.valueOf(status.toUpperCase()));
          break;
        case "UNSPECIFIED":
          oneTask.setStatus(Status.valueOf(status.toUpperCase()));
          break;
        default:
          throw new IllegalArgumentException();    
      }
    } catch(Exception e) {
       e.printStackTrace();
        System.exit(1);
    }
  }

  public void save(String fileName) {
        Gson jsonObject = new GsonBuilder()
                          .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                          .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
                          .create();

        String jsonObjectAsString = jsonObject.toJson(taskStorage);

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(jsonObjectAsString);
            fileWriter.flush();
            fileWriter.close();
        }
        
        catch(Exception e){
          e.printStackTrace();
          System.exit(1);
        }
  }
  
  public void load(String file) {
  
      String lines = "";  
      try {
         String oneLine = null;
         BufferedReader buffRead = new BufferedReader(new FileReader(file));
         while((oneLine = buffRead.readLine())!= null ){
            lines += oneLine;
         }
         buffRead.close();
      }
      catch(FileNotFoundException e) {
        e.printStackTrace();
        System.exit(1);
      }
      catch(IOException e) {
        e.printStackTrace();
        System.exit(1);
      }
      Type listType = new TypeToken<ArrayList<Task>>(){}.getType();
      List<Task> listOfTasks = new GsonBuilder()
                          .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                          .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
                          .create()
                          .fromJson(lines, listType);

      taskStorage.addAll(listOfTasks);
      findMaxTaskId(taskStorage);

      for(Task t : taskStorage){
        tasks.put(t.getId(), t);
      }
    }
 }
