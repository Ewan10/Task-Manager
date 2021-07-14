public class Program { 
    public static void main(String[] args) {
        TaskManager T1 = new TaskManager();
        Options options = new Options();
        options.addOption("add", false, "add new task");
        options.addOption("today", false, "view tasks due today");
        options.addOption("change date", false, "change the date of a task");
        options.addOption("mark done", false, "mark task as complete");
        options.addOption("view", false, "view all the tasks");
        options.addOption("save", false, "save the tasks");
        options.addOption("load", false, "load the tasks from the file");
        options.addOption("exit", false, "exit the program");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        while(true){
          if(cmd.hasOption("view")) {
             T1.viewAll();
          }
          else if(cmd.hasOption("add")) {
             T1.add();
          }
          else if(cmd.hasOption("today")) {
            T1.dueToday();
          }
          else if(cmd.hasOption("change date")) {
            T1.changeDate();
          }
          else if(cmd.hasOption("mark done")) {
            T1.complete();
          }
          else if(cmd.hasOption("save")) {
            T1.save(T1);
          }
          else if(cmd.hasOption("load")) {
            T1 = T1.load();
          }
          else if(cmd.hasOption("exit")) {
            T1.save(T1);
            break;
          }          
          else {
            System.out.println("Invalid input.");
          }
        }
    }
}
