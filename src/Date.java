
/**
 * This class will handle and create the dates that are associated with the TodoItems
 * that the user inputs. 
 * 
 * @author Maddie McGreevy
 *
 */
public class Date {

    // instance variables
    /**
     * The month number of the current date (1-12).
     */
    private int month;

    /**
     * The current date's day number within its month.
     */
    private int day;

    /**
     * The current date's year.
     */
    private int year;

    // constructors
    /** Constructs a date from the given month, day, and year.
     * 
     * @param month The month number (1-12) for the date to be constructed.
     * @param day The day number for the date to be constructed.
     * @param year The year for the date to be constructed.
     */
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;   
    }


    // methods
    /**
     * This method will create a date from the YYYYMMDD type of string.
     * 
     * @param string The string in the format of YYYYMMDD.
     * @return The corresponding date.
     */
    public static Date fromYYYYMMDDString(String string) {
        String theYear;
        String theMonth;
        String theDay;
        int year;
        int month;
        int day;
        
        // the year will contain the first 4 numbers
        theYear = string.substring(0, 4);
        // the month will contain the next two numbers
        theMonth = string.substring(4,6);
        // the day will contain the last two digits
        theDay = string.substring(6, 8);
        // convert the year string to an int
        year = Integer.parseInt(theYear);
        // convert the month string to an int
        month = Integer.parseInt(theMonth);
        // convert the day string to an int
        day = Integer.parseInt(theDay);
        // create a new date object that will contain the month, day, and year integers
        Date date = new Date(month, day, year);
        // return the new date
        return date;
    }

    /**
     * This method will create a date from the YYYY-MM-DD type of string.
     * 
     * @param string The string in the format of YYYY-MM-DD.
     * @return The corresponding date.
     * @throws IllegalArgumentException When the format is not correct.
     */
    public static Date fromYYYYMMDDDashString(String string) {
        int year;
        int month;
        int day;
        // split the strings whenever there is an "-"
        String[] parts = string.split("-");

        // if the parts amount is not equal to 3, then throw an illegalargumentexception
        if(parts.length != 3) {
            throw new IllegalArgumentException();
        }
        // if the first part is not equal to 4, or the second part or third part is not equal to 2, then 
        // throw an illegalargumentexception
        if (parts[0].length() != 4 || parts[1].length() != 2 || parts[2].length() != 2) {
            throw new IllegalArgumentException();
        }
        try {
            // convert the year string to an integer
            year = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        try { 
            // convert the month string to an integer
            month = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        try {
            // convert the day string ot an integer
            day = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        // return the month, day, and year date
        return new Date(month, day, year); 
    }

    /**
     * This method will 
     * 
     * @return the date in the format of YYYYMMDD.
     */
    public int getAsYYYYMMDD() {
        int date = 0;
        // add the year to the date int
        date = date + this.year;
        // multiply this by 100 
        date = date * 100;
        // add the month to the date int on top of the year
        date = date + this.month;
        // multiply this by 100
        date = date * 100;
        // add the day to the date int on top of the year and month
        date = date + this.day;
        // return the YYYYMMDD date
        return date;
    }

    /**
     * This method will tell the program if the date objects are equal to each
     * other or not.
     * 
     * @param object The date object that is going to be compared to another 
     * date object.
     * @return Return true if the two objects are equal to each other and false
     * otherwise.
     */
    public boolean equals(Object other) {
        // if other is empty then return false
        if(other == null) {
            return false;
        // if other.getClass() is not equal to this.getClass() then return false
        } else if(other.getClass() != this.getClass()) {
            return false;
        }
        Date otherAsDate = (Date)other;

        // if this.date is equal to other.date then return true
        if(this.month == otherAsDate.month && this.day == otherAsDate.day && this.year == otherAsDate.year) {
            return true;
        }
        // otherwise return false
        return false;
    }

    /**
     * This method will compare two dates to each other to see which one comes 
     * before the other.
     * 
     * @param other The date object that is going to be compared to another date 
     * obejct.
     * @return Return -1 if thisDate < otherDate, 1 if thisDate > otherDate, and 
     * 0 otherwise.
     */
    public int compareTo(Date other) {
        int thisDate = this.getAsYYYYMMDD();
        int otherDate = other.getAsYYYYMMDD();

        // if thisDate is less than otherDate then return -1
        if(thisDate < otherDate) {
            return -1;
        // if thisDate is greater than otherDate then return 1
        } else if (thisDate > otherDate) {
            return 1;
        } 
        // otherwise return 0
        return 0;
    }
    
    /**
     * This method will print out the date that the user inputs to the program.
     *
     *@return finalString The date in the form of a string.
     */
    public String toString() { //might have to adjust the 0's in this program
        String finalString = "";
        String newDay = "";
        String newMonth = "";
 
        // add the month, day, and year, to a string and seperate them with "/".
        finalString = finalString + month + "/" + day + "/" + year;
        // return the finalstring that will show the user the date of their task.
        return finalString; 
    }
}
