
/**
 * This class will handle the functions that has to do with the 
 * todo items that the user inputs. This will include handling the 
 * getters to get information from other classes. 
 * 
 * @author Maddie McGreevy
 *
 */
public class TodoItem {

    /*
    // instance variables
    /**
     * Private instance variable that stores the date.
     */
    private Date date;
    
    /**
     * Private instance variable that stores the description of the TodoItem that the user inputs.
     */
    private String description;
    
    /**
     * Private instance varibale that stores the importance level of the TodoItem. 
     */
    private Importance importanceLevel;
    
    // constructors
    public TodoItem(Date date, String description, Importance importanceLevel) {
        this.date = date;
        this.description = description;
        this.importanceLevel = importanceLevel;
        
        // if the description contains a "," then throw an illegalargumentexception
        if(description.contains(",")){
            throw new IllegalArgumentException();
        }
    }
    
    // methods
    /**
     * This method will build the todotasks from a previous CSV by splitting it into
     * parts and evaulating them.
     * 
     * @param string The string that will be split up by ",".
     * @return item The parts of the CSV split up.
     * @throws IllegalArgumentException If the importance level is not equal to one of
     * the three options that the user is given.
     */
    public static TodoItem buildFromCSV(String string) {
        Date date;
        TodoItem item;
        Importance importance;
        // create parts by splitting at the ",".
        String[] parts = string.split(",");
        
        // if the second part is equal to HIGH, then the importance level is HIGH.
        if(parts[2].equals("HIGH")) {
            importance = Importance.HIGH;
        // if the second part is equal to MEDIUM, then the importance level is MEDIUM.
        } else if(parts[2].equals("MEDIUM")) {
            importance = Importance.MEDIUM;
        // if the second part is equal to LOW, then the importance level is LOW.
        } else if(parts[2].equals("LOW")){
            importance = Importance.LOW;   
        // if the second part is not equal to any of these, then throw an illegalargumentexception.
        } else {
            throw new IllegalArgumentException();
        }
        
        // get the date from the fromYYYYMMDDString method.
        date = Date.fromYYYYMMDDString(parts[0]);
        // part all of the parts together to create a TodoItem object.
        item = new TodoItem(date, parts[1], importance);
        // return the task that came from a CSV.
        return item;
    }
    
    /**
     * This method will convert the tasks to a CSV string.
     * 
     * @return CSVString The user's input as a CSV string that can be added to a .txt file.
     */
    public String getAsCSV() {
        String CSVString = "";
        Date CSVDate = date;
        Importance CSVImportance = importanceLevel;
        String CSVDescription = description;
        
        // add the date, description, and importance together and seperate them with a ",".
        CSVString = CSVString + CSVDate.getAsYYYYMMDD() + "," + CSVDescription + "," + CSVImportance;
        // return the new CSVString in the form of a string.
        return CSVString.toString();

    }
    
    /**
     * This method is the getter that will get the date.
     * 
     * @return The date object.
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * This method is the getter that will get the importance level.
     * 
     * @return The importance enum.
     */
    public Importance getImportanceLevel() {
        return importanceLevel;
    }
    
    /**
     * This method is the getter that will get the description.
     * 
     * @return The description object. 
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * This method will output the tasks to the user when they select to display their
     * tasks or save their tasks to a .txt file.
     * 
     * @return theTask The todo task that the user created while using this program.
     */
    @Override
    public String toString() {
        String theTask = "*" + "\n";
        Date theDate = getDate();
        theTask = theTask + "Date: " + theDate.toString() + "\n" + "Importance: " + importanceLevel.toString() + "\n" + description + "\n";
        return theTask;
    }
}
