package com.ewan.TaskManager;

import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;

class TaskManager {
  private final List<Task> taskStorage = new ArrayList<Task>();

  public void add(Task t) {
     taskStorage.add(t);
  }

  public void viewAll() {
    for (Task task : taskStorage) {
      task.print();
    }
  }

  public void save(String fileName) {
        Gson jsonObject = new Gson();
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

  public void load(String fileName) {
      String lines = "";
      
      try {
         String oneLine = null;
         BufferedReader buffRead = new BufferedReader(new FileReader(fileName));
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
      List<Task> listOfTasks = new Gson().fromJson(lines, listType);
      taskStorage.addAll(listOfTasks);
  }
 }
