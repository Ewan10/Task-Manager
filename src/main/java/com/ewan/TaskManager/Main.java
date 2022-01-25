package com.ewan.TaskManager;

//import java.io.FileNotFoundException;
//import java.io.IOException;
import org.apache.commons.cli.*;

public class Main {
  public static void main(String[] args) {
    
    TaskManager T1 = new TaskManager();
    try {
      T1.load("fileStorage.json");
    }
    catch(Exception e){
        e.printStackTrace();
        System.exit(1);
    }
   /*
    try {
      T1 = T1.load("C:\\Users\\secge\\.vscode\\Java Projects\\Task-Manager\\TaskWarehouse.txt");
    }
    
    catch(IOException ioe) {
      System.out.println("Error. The loading process failed.");
      ioe.printStackTrace();
      System.exit(1);
    }
    catch (ClassNotFoundException e) {
      System.out.println("Error. The requested data from the relevant file was not found.");
      System.exit(1);
    }
    */
    Options options = new Options();
    options.addOption("a", "add", true, "add new task")
        .addOption("d","date", true, "view tasks due today")
        .addOption("t", "time", true, "change the date of a task");
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
    if (cmd.hasOption("add")) {
        title = cmd.getOptionValue("add");
    } 
    if (cmd.hasOption("add")) {
       date = cmd.getOptionValue("date");
    } 
    if (cmd.hasOption("time")) {
      time = cmd.getOptionValue("time");
      T1.add(new Task(title, date,time));
    }  
    if (cmd.hasOption("viewAll")) {
      T1.viewAll();
    } 
    if (cmd.hasOption("save")) {
      try {
        T1.save("fileStorage.json");  
      }
      catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }/**/ 
    }
  }
}
// mvn compile exec:java -Dexec.mainClass="com.ewan.TaskManager.Main" -Dexec.args="-a 'Example task' --date '12/12/21' -t '10am' --v"