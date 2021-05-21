import java.util.*;
import java.util.regex.*;

class Task {
    private String title;
    private String date;

    public Task(String title, String date) {
        this.title = title;
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String s) {
        title = title + s;
    }   
    public String getDate() {
        return date;
    } 
    public void setDate(String s) {
        date = s;
    }
    public void print() { System.out.println("title: " + title + " date: " + date); }
}
//_________________TaskManager class_____________________________________

class TaskManager {
    private String title;
    private String date;
    private List<Task> LT= new ArrayList<Task>();

//____________________Helper function DateComparison() This method compares the date of a stored 
//object with the date of today from the Date() object. It is used by the DueToday() method.

public boolean DateComparison(String date) {
           
            String[] dateAsString = date.split("/");		//It will split the day, month and year.
            int[] intDate = { 0, 0, 0};
            for(int i= 0; i < dateAsString.length; i++)
                intDate[i] = Integer.parseInt(dateAsString[i]);		//Converts the string input date to integer.
            
            Date d = new Date(); 
            String s = d.toString();                                         //Converts the Date() object to string.
            
            String[] dayMonthYear = {" ", " "," ", " "};
            int to = 0;
            int[] integerDate = {0, 0, 0, 0};
    
         Pattern p =  Pattern.compile("\\s\\w{3}\\s|\\d{2}\\s|\\d{4}");	//Use of regular expression to match
	     Matcher m = p.matcher(s);					//the three letter month name, the day
	    while(m.find()) {					//date and the four digit year.

           	 dayMonthYear[to] = m.group().toString().trim();	  //This array holds the day, month and year.
           		  to++;						//extracted from the Date object as strings.
  	       }

  	       integerDate[1] = Integer.parseInt(dayMonthYear[1]);	//The day date.
  	       integerDate[2] = Integer.parseInt(dayMonthYear[3]);	//The year.
  	       
            //Month conversion to integer originating from the new Date() object in dayMonthYear[0]

        switch(dayMonthYear[0]) {
            case "Jan": integerDate [0] = 1;
            break;
            case "Feb": integerDate [0] = 2;
            break; 
            case "Mar": integerDate [0] = 3;
            break;
            case "Apr": integerDate [0] = 4;
            break;
            case "May": integerDate [0] = 5;
            break;
            case "Jun": integerDate [0] = 6;
            break;
            case "Jul": integerDate [0] = 7;
            break;
            case "Aug": integerDate [0] = 8;
            break;    
            case "Sep": integerDate [0] = 9;
            break;
            case "Oct": integerDate [0] = 10;
            break;
            case "Nov": integerDate [0] = 11;
            break;
            case "Dec": integerDate [0] = 12;
            break;
            default: integerDate [0]= 0;
            break;
        }
            if((intDate[0] == integerDate[1]) && (intDate[1] == integerDate[0]) && (intDate[2] == integerDate[2]))
                return true;
            else 
                return false;
        }

//________________________The Add() method for adding a new task.

    public void Add() {
        int[] userDate = {0, 0, 0};
        String[] dateAsString = {};
        
        Scanner sx = new Scanner(System.in);
         
        System.out.println("Enter the title of the task: ");
        title = sx.nextLine();
        
       do {
        do {
            System.out.println("Enter the date as dd/mm/yyyy: ");
       	 
            date = sx.nextLine();
        
            //Validate the format for the entered date as dd/mm/yyyy.
        
            Pattern p = Pattern.compile("\\d{2}?\\/\\d{2}?\\/\\d{4}?");
	        Matcher m = p.matcher(date);
        }
	  	while(!Pattern.compile("\\d{2}?\\/\\d{2}?\\/\\d{4}?").matcher(date).matches());
	  	
	  	 dateAsString = date.split("/"); 
	  	for(int i = 0; i < userDate.length; i++) 	  	    
	  	     userDate[i] = Integer.parseInt(dateAsString[i]);
       }
	  	  // Date validation according to numeric values of 31, 12 and a positive number for
	  	  //the day, the month and the year respectively.
	  	  
        while(!(userDate[0] <=31 && userDate[0] >= 1) || (!(userDate[1] <=12 && userDate[1] >= 1)) 
        || (!(userDate[2] >= 2021)));
           // System.out.println("Invalid date! Enter a new date as dd/mm/yyyy");
            
        
        LT.add(new Task(title, date));
    } 

//______________The ChangeDate() method used to change the date of an already stored task.

    public void  ChangeDate() {
      Scanner sx = new Scanner(System.in);
        System.out.println("Enter the title of the task to change its date: ");
        title = sx.nextLine();
        boolean b = false;
        for(Task t: LT) {
            
            if (title.equals(t.getTitle())){
                b = true;
                System.out.println("Enter the new date as dd/mm/yyyy: ");
                date = sx.nextLine();
                t.setDate(date);
                t.print();
            }
        }
        if(b== false) {
            System.out.println("This task does not exist."); }
    }

//______________________________The All() method for viewing all the stored tasks.

    public void All() {

        for(Task t: LT) {
            t.print();
        }
    }

		//______________The DueToday() method for viewing the task which are due today.
    
public void DueToday() {
    for(Task t: LT) {
		if(DateComparison(t.getDate()))
			t.print();
    }
}

//___________________The Complete() method for marking a task as complete.

    public void Complete() {
        Scanner sx = new Scanner(System.in);
        System.out.println("Enter a task title: ");
         title = sx.nextLine();    
        //in.close();
        for(Task ta: LT) {
         if(title.equals(ta.getTitle())) {
            ta.setTitle(" -Done.");
         }
        }
    } 
} 

//_____________User text interface inside main
public class Main {
 
    public static void main(String[] args) {
       TaskManager T1 = new TaskManager();
          System.out.println("For creating a new task type add \n");
          System.out.println("For viewing the tasks due today type today \n");
          System.out.println("For viewing all tasks type all \n");
          System.out.println("For changing the date of a task type change date \n");
          System.out.println("For marking a task as complete type \"complete\"\n"); 
          Scanner sc = new Scanner(System.in);
          
          while(true) {
           String s = sc.nextLine(); 
           switch(s) {
               case "add": T1.Add();
               break;
               case "today": T1.DueToday();
               break;
               case "all": T1.All();
               break;
               case "change date": T1.ChangeDate();
               break;
               case "complete": T1.Complete();
               break;
               default: 
               System.out.println("Invalid input. Try again.");
               break;
           }
      }

    }
}
