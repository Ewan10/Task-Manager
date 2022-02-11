package com.ewan.TaskManager;

import org.apache.commons.cli.*;

public class Main {
  public static void main(String[] args) {

    TaskManager taskManager = new TaskManager();

    Options options = new Options();
    options.addOption("lf", "load", true, "The name of the file for loading.");
    options.addOption("a", "add", true, "Add a new task.");
    options.addOption("d", "date", true, "View tasks due today.");
    options.addOption("t", "time", true, "Change the date of a task.");
    options.addOption("v", "viewAll", false, "List all the stored tasks.");
    options.addOption("st", "status", true, "Option for changing the status of a task.");
    options.addOption("id", "id", true, "This is the id number of the task.");
    options.addOption("sf", "save", true, "The name of the file where the session will be saved.");

    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = null;
    String title, date, time;
    title = "";
    date = "";

    try {
      cmd = parser.parse(options, args);
    } catch (ParseException e) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("CLITester", options);
      System.exit(1);
    }
    if (cmd.hasOption("lf")) {
      String file = cmd.getOptionValue("load");
      taskManager.load(file);
    } else {
      taskManager.load("fileStorage.json");
    }
    if (cmd.hasOption("add") && cmd.hasOption("date") && cmd.hasOption("time")) {
      title = cmd.getOptionValue("add");
      date = cmd.getOptionValue("date");
      time = cmd.getOptionValue("time");
      taskManager.add(title, date, time);
    }
    if (cmd.hasOption("viewAll")) {
      taskManager.viewAll();
    }
    if (cmd.hasOption("status") && cmd.hasOption("id")) {
      int id = Integer.valueOf(cmd.getOptionValue("id"));
      String statusValue = cmd.getOptionValue("status");
      taskManager.updateTaskStatus(id, statusValue);
      ;
    }
    if (cmd.hasOption("sf")) {
      String fileName = cmd.getOptionValue("save");
      taskManager.save(fileName);
    } else
      try {
        taskManager.save("fileStorage.json");
      } catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
  }
}
