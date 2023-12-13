import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * This class will be in charge of building the username that the user inputs,
 * adding user input tasks to the array list, sort both dates and importance 
 * levels of the different tasks, and finalize the TodoList. 
 * 
 * @author Maddie McGreevy
 *
 */
public class TodoList {

    // instance variables
    /**
     * Private instance variable that creates the arraylist that all of the users
     * tasks will go into.
     */
    private ArrayList<TodoItem> theTasks;

    /**
     * Private instance variable that creates the string that will hold the users
     * username.
     */
    private String username = "";

    // constructors
    /**
     * This constructor will build the theTasks ArrayList and the username of the user.
     * 
     * @param user The username of the user using the program
     */
    public TodoList(String user) {
        theTasks = new ArrayList<TodoItem>();
        this.username = user;
    }

    // methods
    /**
     * This method will build a todo list from a username by attempting to open
     * the corresponding file and reading in the data, creating TodoItems as 
     * needed.
     * 
     * @param string The user's username
     * @return The list of the user's tasks
     * @throws IOException 
     */
    public static TodoList buildFromUsername(String string) throws IOException{ 
        List<String> empty;
        String list;

        try {
            // read all the lines of the file is there is a match with a .txt file.
            // If there is no match, then throw an IOException.
            empty = Files.readAllLines(Paths.get(string + ".txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IOException();
        }

        TodoList userInputList = new TodoList(string);
        // for the size of the tasks that the user inputs, build them from a CSV.
        for(int i = 0; i < empty.size(); i++) {
            TodoItem todoList;
            String currentLine = empty.get(i);
            todoList = TodoItem.buildFromCSV(currentLine);
            // add the task to the todoList for the user.
            userInputList.addTask(todoList);
        }

        // return the list with the users tasks on it.
        return userInputList;
    }

    /**
     * This method will add a item that the user inputs to the TodoList. There will be
     * nothing returned since it is a void method.
     */
    public void addTask(TodoItem item) {
        theTasks.add(item);   
    }

    /**
     * This method will sort the dates of the items in the TodoList.
     * 
     * @return The TodoList sorted by their dates.
     */
    public String getAsDateSortedString() {
        String dateSortedString = "";

        // if the task size list is empty, then tell the user this.
        if(theTasks.size() == 0) {
            return "No tasks in list";
        }

        // create a comparator that will compare the dates.
        Comparator<TodoItem> comparison = new DateComparator();
        // sort the tasks based off of their dates
        Collections.sort(theTasks, comparison);

        // for the size of the tasks list, arrange the tasks in the correct date order.
        for(int i = 0; i < theTasks.size(); i++) {
            dateSortedString = dateSortedString + theTasks.get(i).toString() + "\n"; 
        }
        
        // return the date sorted string.
        return dateSortedString;
    }

    /**
     * This method will sort the importance levels of the items in the TodoList.
     * 
     * @return The TodoList sorted by their importance level.
     */
    public String getAsImportanceSortedString() {
        String importanceSortedString = "";

        // if the task size list is empty, then tell the user this.
        if(theTasks.size() == 0) {
            return "No tasks in list";
        }

        // create a comparator that will compare the importance levels.
        Comparator<TodoItem> comparison = new ImportanceComparator();
        // sort the tasks based off of their importance levels
        Collections.sort(theTasks, comparison);

        // for the size of the tasks list, arrange the tasks in the correct importance level order.
        for(int i = 0; i < theTasks.size(); i++) {
            importanceSortedString = importanceSortedString + theTasks.get(i).toString() + "\n";
        }
        // return the importance sorted string.
        return importanceSortedString;       
    }

    /**
     * This method will save the current TodoList to the user's file. This 
     * method will not return anything since it is a void method.
     */
    public void finalize() {
        String finalizeString = "";
        // for all of the items in the list, starting with the current item, add them to a string
        // and then go to a new line.
        for(TodoItem currentItem : theTasks) {
            finalizeString = finalizeString + currentItem.getAsCSV() + "\n";
        }

        try {
            // write the files to a txt file.
            Files.write(Paths.get(username+".txt"), finalizeString.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {           
        }
    }
    
    @Override
    /**
     * This is the toString method. This helps to put create information that
     * is communicable in String form
     * 
     * @return - This method will return type string
     */
    public String toString() {
        String finalString = "";
        for (int i = 0; i < theTasks.size(); i++) {
            finalString = finalString + theTasks.get(i).toString() + "\n";
            
        }

        return finalString;
    }

}
