import java.io.IOException;
import java.util.Scanner;

/**
 * The TodoDriver will ask the user for their username, ask them if they
 * would like to create a new Todo List, and then ask them to pick from a
 * list of options on what they exactly would like to do. The options can 
 * vary from showing tasks by their sorted date or importance, to adding 
 * new tasks or saving and exiting the program. 
 * 
 * @author Maddie McGreevy
 */
public class TodoDriver {

    public static void main(String[] args) {
        boolean keepGoing = true;
        Scanner keyboard = new Scanner(System.in);
        String username = "";
        TodoList todoList = null;
        String inputUsername = "";
        String newTodoList = "";
        String userOption = "";
        String date = "";
        String importance = "";
        String description = "";
        String newUsername = "";
        String newDate = "";

        // welcome the user to the program
        System.out.println("Welcome to TodoList application.");
        // ask for the user's username
        System.out.print("Enter your username: ");
        // read in the input
        username = keyboard.nextLine();
        // trim the input
        username = username.trim();
        inputUsername = username;

        try {
            // use the buildFromUsername function to build the todoList
            todoList = TodoList.buildFromUsername(inputUsername);
        } catch (IOException e) {
            // if there is no username that matches a file, then output that there is an error.
            System.out.println("An error occurred trying to read the file for your username.");
            // ask the user if they would like to create a new TodoList
            System.out.print("Create new Todo List? (Y/N) ");
            // read in the user's option
            userOption = keyboard.nextLine();
            
            // if the user chooses N then exit the program
            if(userOption.equals("N")) {
                keepGoing = false;
            // if the user chooses Y then create a new todoList
            } else if(userOption.equals("Y")) {
                todoList = new TodoList(username);
            // if anything else is inputted then exit the program.
            } else {
                System.exit(0);
            }
        }
        
        while(keepGoing) {
            try {
                // print out the options that the user can pick from
                System.out.println("Options:");
                System.out.println("1) Show tasks sorted by date");
                System.out.println("2) Show tasks sorted by importance");
                System.out.println("3) Add new task");
                System.out.println("4) Save and exit");
                System.out.print("Your choice: ");
                // read in the user's choice
                userOption = keyboard.nextLine();
                // trim the user's option
                userOption = userOption.trim();
                // if the user chooses 1, then print out the todolist that went through the 
                // getAsDateSortedString method.
                if (userOption.equals("1")) {
                    System.out.println(todoList.getAsDateSortedString());
                // if the user chooses 2, then print out the todoList that went through the 
                // getAsDateSortedString method.
                } else if (userOption.equals("2")) {
                    System.out.println(todoList.getAsImportanceSortedString());
                // if the user chooses 3, then start the process to create a new task.
                } else if (userOption.equals("3")) {
            
                    Date theDate;
                    try {
                        // ask the user to enter a date
                        System.out.print("Enter a date (YYYY-MM-DD): ");
                        // read in the user's input
                        date = keyboard.nextLine();
                        date = date.trim();
                        // call the fromYYYYMMDDDashString 
                        theDate = Date.fromYYYYMMDDDashString(date);
                    } catch (IllegalArgumentException a) {
                        // if the user inputs the incorrect format, then throw an illegalargumentexception
                        // and tell them that there is an error
                        System.out.println("Dates must be entered in YYYY-MM-DD format");
                        throw new IllegalArgumentException();
                    }
                
                    Importance important = Importance.HIGH;
                    // ask the user for an importance level
                    System.out.print("Enter an importance (HIGH, MEDIUM, LOW): ");
                    // read in the users input
                    importance = keyboard.nextLine();
                    // trim the users input
                    importance = importance.trim();
                    
                    // if the importance level equals high, then set it equal to high
                    if (importance.equals("HIGH")) {
                        important = Importance.HIGH;
                    // if the importance level equals medium, then set it equal to medium
                    } else if(importance.equals("MEDIUM")) {
                        important = Importance.MEDIUM;
                    // if the importance level equals low, then set it equal to low
                    } else if(importance.equals("LOW")) {
                        important = Importance.LOW;
                    } else {
                        // if the user enters a bad importance choice, then throw an illegalargumentexception
                        // and tell the user that they entered a bad value.
                        System.out.println("Bad importance choice");
                        throw new IllegalArgumentException();
                    }
                    
                    try {
                        // ask the user for a description
                        System.out.print("Enter a short description (no commas): ");
                        // read in the description
                        description = keyboard.nextLine();  
                        // trim the desription
                        description = description.trim();
                        // add the date, description, adn importance level to a todoTask object
                        TodoItem newTodoTask = new TodoItem(theDate, description, important);
                        // add the task to the list
                        todoList.addTask(newTodoTask);
                    } catch (IllegalArgumentException e) {
                        // if the user enters a "," then throw an illegalargumentexception and tell them 
                        // that they have entered a bad value
                        if(description.contains(",")){
                            System.out.println("No commas allowed");
                            throw new IllegalArgumentException();
                        }
                    }
                    
                // if the user enters a 4, then finalize the list and end the program
                } else if (userOption.equals("4")) {
                    todoList.finalize();
                    break;
                }
        
            } catch (IllegalArgumentException e) {
            
            } 
        }
        
    }
    
}



