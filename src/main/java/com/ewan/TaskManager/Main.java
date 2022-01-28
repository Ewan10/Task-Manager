package com.ewan.TaskManager;

import org.apache.commons.cli.*;

public class Main {
  public static void main(String[] args) {
    
    TaskManager taskManager = new TaskManager();
    try {
      taskManager.load("fileStorage.json");
    }
    catch(Exception e){
        e.printStackTrace();
        System.exit(1);
    }
   
    Options options = new Options();
    options.addOption("a", "add", true, "add new task");
    options.addOption("d","date", true, "view tasks due today");
    options.addOption("t", "time", true, "change the date of a task");
    options.addOption("v", "viewAll", false, "list all the stored tasks");
    options.addOption("save", "save", false, "saves the stored tasks in a specified file");
  
    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = null;
    String title, date, time;
    title = "";
    date = "";
    try {
        cmd = parser.parse(options, args);
    }
    catch (ParseException e) {
       HelpFormatter formatter = new HelpFormatter();
       formatter.printHelp("CLITester", options);
       System.exit(1);
    }
    if (cmd.hasOption("add") && cmd.hasOption("date") && cmd.hasOption("time")) {
        title = cmd.getOptionValue("add");
        date = cmd.getOptionValue("date");
        time = cmd.getOptionValue("time");
        taskManager.add(new Task(title, date,time));
    } 
    if (cmd.hasOption("viewAll")) {
      taskManager.viewAll();
    } 
    try {
      taskManager.save("fileStorage.json");  
    }
    catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    } 
  }
}
