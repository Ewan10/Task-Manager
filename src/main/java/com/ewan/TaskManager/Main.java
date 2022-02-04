package com.ewan.TaskManager;
//import org.joda.time.*;
import org.apache.commons.cli.*;

public class Main {
  public static void main(String[] args) {
    
    TaskManager taskManager = new TaskManager();
  
    Options options = new Options();
    options.addOption("load", "load", false, "Loads the previous session from a specified file.");
    options.addOption("f", "file", true, "The name of the file for loading.");
    options.addOption("a", "add", true, "Add a new task.");
    options.addOption("d","date", true, "View tasks due today.");
    options.addOption("t", "time", true, "Change the date of a task.");
    options.addOption("v", "viewAll", false, "List all the stored tasks.");
    options.addOption("save", "save", false, "Saves the stored tasks in a specified file.");
    options.addOption("f", "file name", true, "The name of the file where the session will be stored.");    
  
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
    if (cmd.hasOption("load") && cmd.hasOption("fileLoad")) {
      String file = cmd.getOptionValue("file");
      taskManager.load(file);
    } 
    if (cmd.hasOption("add") && cmd.hasOption("date") && cmd.hasOption("time")) {
        title = cmd.getOptionValue("add");
        date = cmd.getOptionValue("date");
        time = cmd.getOptionValue("time");
        taskManager.add(title, date, time);
    } 
    if (cmd.hasOption("viewAll")) {
      taskManager.viewAll();
    } /**/
    if (cmd.hasOption("save") && cmd.hasOption("f")) {
      String fileName = cmd.getOptionValue("file name");
      taskManager.save(fileName);
    }
    else
      try {
        taskManager.save("fileStorage.json");  
      }
    catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    } 
  }
}
