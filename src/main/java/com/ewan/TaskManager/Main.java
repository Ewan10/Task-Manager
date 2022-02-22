package com.ewan.TaskManager;

import org.apache.commons.cli.*;

public class Main {
  public static void main(String[] args) {

    TaskManager taskManager = new TaskManager();

    Options options = new Options();
    options.addOption("a", "add", true, "Add a new task.");
    options.addOption("d", "date", true, "Add the date for the task.");
    options.addOption("t", "time", true, "Add the time for the task.");
    options.addOption("v", "viewAll", false, "List all the stored tasks.");
    options.addOption("s", "status", true, "Option for changing the status of a task.");
    options.addOption("id", "id", true, "This is the id number of the task.");
    options.addOption("f", "file", true, "The name of the file where the session will be saved or loaded.");

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
    if (cmd.hasOption("f")) {
      taskManager.load(cmd.getOptionValue("file"));
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
    }
    if (cmd.hasOption("f")) {
      taskManager.save(cmd.getOptionValue("file"));
    } else {
      taskManager.save("fileStorage.json");
    }
  }
}
