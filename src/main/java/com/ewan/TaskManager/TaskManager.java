package com.ewan.TaskManager;

import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

//import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;

//import java.lang.reflect.Type;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;
import org.joda.time.LocalDate;

class TaskManager {
  private final List<Task> taskStorage = new ArrayList<Task>();
 /**/ 
  private Task stringToJodaTask(String title, String dateAsText, String timeAsText) {
    DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
    LocalDate parsedDate = LocalDate.parse(dateAsText, fmt);
    LocalTime parsedTime = LocalTime.parse(timeAsText);
    return new Task(title, parsedDate, parsedTime);
  }

  public void add(String title, String date, String time) {
    
    Task t = stringToJodaTask(title, date, time);
    taskStorage.add(t);
  }

  public void viewAll() {
    for (Task task : taskStorage) {
      task.print();
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
      //Use the string "lines" from the read file to recreate the list of tasks.
      Type listType = new TypeToken<ArrayList<Task>>(){}.getType();
      List<Task> listOfTasks = new GsonBuilder()
                          .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                          .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
                          .create()
                          .fromJson(lines, listType);
      taskStorage.addAll(listOfTasks);
  }
 }
