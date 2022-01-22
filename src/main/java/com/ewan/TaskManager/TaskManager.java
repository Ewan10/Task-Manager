package com.ewan.TaskManager;

import java.util.*;
//import java.util.regex.*;
import java.io.*;
//import java.time.*;

class TaskManager implements Serializable {
  // private static final long serialversionUID = 1L;
  private List<Task> taskStorage = new ArrayList<Task>();

  /***
   *  This method compares the date of a stored object with the date of today from the String object. It is used by the dueToday method.
   * @param date
   * @return
   */
 /* private boolean dateCompare(String date) {
    return date.equals(String.now());
  }
 */
  /***
   * The dateAccess() method simply takes an int and returns a date from a task object stored in the list of TaskManager. It is called in the insertionSort method.
   * @param w
   * @return
   */
  /*
  private String dateAccess(int w) {
     String date = taskStorage.get(w).getDate();

    return date;
  }
*/
  /***
   * insertionSort method sorts the objects according to the //date from nearest to furthest in future. It is called in the add method.
   */
  /*
  private void insertionSort() {
    for (int i = 1; i < taskStorage.size(); i++) {
      int y = i - 1;
      Task toInsert = taskStorage.get(i);

      while ((y >= 0) && (dateAccess(y).isAfter(toInsert.getDate()))) {
        taskStorage.set(y + 1, taskStorage.get(y));
        y--;
      }
      taskStorage.set(y + 1, toInsert);
    }
  }
*/
  /***
   * The validateDate() method validates the format and the ranges of the user input date. It is used by the add method.
   */
  /*
  private String validateDate() {
    String date;
    int[] userDate = { 0, 0, 0 };
    String[] dateAsString = {};

    do {
      do {
        date = userInput("Enter the date as: YYYY-MM-dd");
      } while (!Pattern.compile("\\d{4}?\\-\\d{2}?\\-\\d{2}?").matcher(date).matches());

      dateAsString = date.split("-");
      for (int i = 0; i < userDate.length; i++)
        userDate[i] = Integer.parseInt(dateAsString[i]);
    } while (!(userDate[0] >= 2021) || (!(userDate[1] >= 1 && userDate[1] <= 12)) || (!(userDate[2] >= 1
        && userDate[2] <= 31)));

    // Date validation according to numeric values of 31, 12 and a positive number for the day, the month and the year respectively.
    return String.parse(date);
  }
/*
  /***
   * The userInput() method prints an expression for
   * @param expression
   * @return
   */
  private String userInput(String expression) {
    // the user to enter input and it returns that input string.
    String s;
    Scanner sx = new Scanner(System.in);
    System.out.println(expression);
    s = sx.nextLine();
    sx.close();
    return s;
  }
/*
  private String validateTime() {
    String timeOfTask;
    int hrs, min;
    do {
      do {
        // Converts the string returned from userInput to integer type
        hrs = Integer.parseInt(userInput("Enter the hours: "));
      } while (!(hrs >= 0 && hrs <= 23));
      // Likewise for minutes.
      min = Integer.parseInt(userInput("Enter the minutes: ")); 
    } while (!(min >= 0 && min <= 59));
    // Then the integer values are arguments of the String.of() method.
    timeOfTask = String.of(hrs, min); 
    return timeOfTask; 
  }
*/
  /***
   * searchStoredTask searches the memory for a stored task according to the input title. It is called in the changeDate method.
   * @param title
   */
  /*
  private void searchStoredTask(String title) {
    String date = "";
    String time;
    boolean taskExists = false;
    for (Task task : taskStorage) {
      if (title.equals(task.getTitle())) {
        taskExists = true;
    //    date = validateDate();
        time = validateTime();
        task.setDate(date);
        task.setTime(time);
      }
    }
    if (taskExists == false) {
      System.out.println("This task does not exist.");
    }
  }
*/
  /***
   * The add method adds a new task to the list.
   */
  public void add(Task t) {
  //  String title = userInput("Enter the title of the task: ");
  //  String date = validateDate();
   // String time = validateTime();
    taskStorage.add(t);
  //  insertionSort();
  }

  /***
   * This method changes the date of an already stored task.
   */
  /*
  public void changeDate() { 
    String title = userInput("Enter the title of the task to change its date: ");
    searchStoredTask(title);
  }
*/
  /***
   * The viewAll method for viewing all the stored tasks.
   */
  public void viewAll() {
    for (Task task : taskStorage) {
      task.print();
    }
  }

  /***
   * The dueToday method for viewing the tasks which are due to the present day.
   */
  /*
  public void dueToday() {
    for (Task task : taskStorage) {
      if (dateCompare(task.getDate()))
        task.print();
    }
  }
*/
  /***
   * The complete method marks a task as complete.
   */
  public void complete() {
    String title = userInput("Enter a task title: ");
    for (Task task : taskStorage) {
      if (title.equals(task.getTitle())) {
        task.setStatus(" -Done.");
      }
    }
  }

  /***
   * The save method saves a session of stored tasks in a file specified by the user.
   * @param tm
   * @throws IOException
   */
  public void save(TaskManager tm, String filename) throws IOException {

    try {
      FileOutputStream fileOutputStream = new FileOutputStream(filename);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

      // Serialize object and write it to the prescribed file.
      objectOutputStream.writeObject(tm);
      objectOutputStream.flush();
      objectOutputStream.close();
      fileOutputStream.close();
    } catch (IOException e) {
      System.out.println("Saving process failed. ");
    }
  }

  /***
   * The load() method loads the saved task from a prespecified file
   * to the running ArrayList after the program opens.
   */
  public TaskManager load(String filename) throws IOException, ClassNotFoundException {
    TaskManager tm = null;

    try {
      FileInputStream fileInputStream = new FileInputStream(filename);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

      tm = (TaskManager) objectInputStream.readObject();
      objectInputStream.close();
      fileInputStream.close();
    } catch (IOException e) {
      System.out.println("Error. The loading process failed.");
    } catch (ClassNotFoundException e) {
      System.out.println("Error. The requested data from the relevant file was not found.");
    }
    return tm;
  }
}
