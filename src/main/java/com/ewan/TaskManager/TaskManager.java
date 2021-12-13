package com.ewan.TaskManager;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.time.*;

class TaskManager implements Serializable {
  // private static final long serialversionUID = 1L;
  private List<Task> taskStorage = new ArrayList<Task>();

  private boolean dateCompare(LocalDate date) { // This method compares the date of a stored object with
    return date.equals(LocalDate.now()); // the date of today from the LocalDate object. It is used by
  } // the dueToday method.

  private LocalDate dateAccess(int w) { // The dateAccess() method simply takes an int and returns
    LocalDate date = taskStorage.get(w).getDate(); // a date from a task object stored in the list of //TaskManager. It
                                                   // is called in the insertionSort method.
    return date;
  }

  private void insertionSort() { // insertionSort method sorts the objects according to the //date from nearest
                                 // to furthest in future. It is called in the
    for (int i = 1; i < taskStorage.size(); i++) { // add method.
      int y = i - 1;
      Task toInsert = taskStorage.get(i);

      while ((y >= 0) && (dateAccess(y).isAfter(toInsert.getDate()))) {
        taskStorage.set(y + 1, taskStorage.get(y));
        y--;
      }
      taskStorage.set(y + 1, toInsert);
    }
  }

  private LocalDate validateDate() { // The validateDate() method validates the format and the
    String date; // ranges of the user input date. It is used by the add
    int[] userDate = { 0, 0, 0 }; // method.
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
    // Date validation according to numeric values of 31,
    return LocalDate.parse(date); // 12 and a positive number for
  } // the day, the month and the year respectively.

  private String userInput(String expression) { // The userInput() method prints an expression for
    String s; // the user to enter input and it returns that input
    Scanner sx = new Scanner(System.in); // string.
    System.out.println(expression);
    s = sx.nextLine();
    sx.close();
    return s;
  }

  private LocalTime validateTime() {
    LocalTime timeOfTask;
    int hrs, min;
    do {
      do {
        hrs = Integer.parseInt(userInput("Enter the hours: ")); // Converts the string returned from
      } while (!(hrs >= 0 && hrs <= 23)); // userInput to integer type.
      min = Integer.parseInt(userInput("Enter the minutes: ")); // Likewise for minutes.
    } while (!(min >= 0 && min <= 59));
    timeOfTask = LocalTime.of(hrs, min); // Then the integer values are arguments
    return timeOfTask; // of the LocalTime.of() method.
  }

  private void searchStoredTask(String title) { // searchStoredTask searches the memory
    LocalDate date; // for a stored task according to the input title.
    LocalTime time; // It is called in the changeDate method.
    boolean taskExists = false;
    for (Task task : taskStorage) {
      if (title.equals(task.getTitle())) {
        taskExists = true;
        date = validateDate();
        time = validateTime();
        task.setDate(date);
        task.setTime(time);
      }
    }
    if (taskExists == false) {
      System.out.println("This task does not exist.");
    }
  }

  public void add() { // The add method adds a new task to the list.
    String title = userInput("Enter the title of the task: ");
    LocalDate date = validateDate();
    LocalTime time = validateTime();
    taskStorage.add(new Task(title, date, time));
    insertionSort();
  }

  public void changeDate() { // This method changes the date of
    String title = userInput("Enter the title of the task to change its date: "); // an already stored task.
    searchStoredTask(title);
  }

  public void viewAll() { // The viewAll method for viewing
    for (Task task : taskStorage) { // all the stored tasks.
      task.print();
    }
  }

  public void dueToday() { // The dueToday method for viewing the
    for (Task task : taskStorage) { // tasks which are due to the present day.
      if (dateCompare(task.getDate()))
        task.print();
    }
  }

  public void complete() { // The complete method marks a task as
    String title = userInput("Enter a task title: "); // complete.
    for (Task task : taskStorage) {
      if (title.equals(task.getTitle())) {
        task.setStatus(" -Done.");
      }
    }
  }

  public void save(TaskManager tm) throws IOException { // The save method saves a session of
    String filename = userInput("Enter the filename with the" +
        "extension where you want your task to be saved: "); // stored tasks in a file specified by the user.

    try {
      FileOutputStream fileOutputStream = new FileOutputStream(filename);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

      objectOutputStream.writeObject(tm); // Serialize object and write it to the
                                          // prescribed file.
      objectOutputStream.flush();
      objectOutputStream.close();
      fileOutputStream.close();
    } catch (IOException e) {
      System.out.println("Saving process failed. ");
    }
  }

  // The load() method loads the saved task from a prespecified file
  // to the running ArrayList after the program opens.

  public TaskManager load() throws IOException, ClassNotFoundException {
    TaskManager tm = new TaskManager();
    String filename = userInput("Enter the filename with the extension where" +
        " you want your tasks to be loaded from: ");

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
