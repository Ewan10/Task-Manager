package com.ewan.TaskManager;

import java.io.IOException;
import org.apache.commons.cli.*;

public class Main {
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    TaskManager T1 = new TaskManager();
    Options options = new Options();
    options.addOption("add", false, "add new task");
    options.addOption("today", false, "view tasks due today");
    options.addOption("changeDate", false, "change the date of a task");
    options.addOption("markAsDone", false, "mark task as complete");
    options.addOption("view", false, "view all the tasks");
    options.addOption("save", false, "save the tasks");
    options.addOption("load", false, "load the tasks from the file");
    options.addOption("exit", false, "save the progress and exit the program");

    CommandLineParser parser = new DefaultParser();
    try {
      CommandLine cmd = parser.parse(options, args);
      while (true) {
        if (cmd.hasOption("view")) {
          T1.viewAll();
        } else if (cmd.hasOption("add")) {
          T1.add();
        } else if (cmd.hasOption("today")) {
          T1.dueToday();
        } else if (cmd.hasOption("changeDate")) {
          T1.changeDate();
        } else if (cmd.hasOption("markAsDone")) {
          T1.complete();
        } else if (cmd.hasOption("save")) {
          T1.save(T1);
        } else if (cmd.hasOption("load")) {
          T1 = T1.load();
        } else if (cmd.hasOption("exit")) {
          T1.save(T1);
          System.exit(0);
        } else {
          System.out.println("Invalid input.");
        }
      }
    } catch (ParseException p) {
      System.err.println("Parsing failed. Reason: " + p.getMessage());
    }
  }
}
