import java.util.Comparator;

/**
 * This class will use the comparable interface in order to compare the todo
 * items that the user inputs. The dates of each todo item will be compared 
 * in this class.
 * 
 * @author Maddie McGreevy
 *
 */
public class DateComparator implements Comparator<TodoItem>{

    @Override
    public int compare(TodoItem o1, TodoItem o2) {
        // set the o1 argument to the first date that will be compared.
        Date firstDate = o1.getDate();
        // set the o2 argument to the second date that will be compared.
        Date secondDate = o2.getDate();
        
        // using the compareTo method, compare the firstDate to the secondDate to
        // see which one comes first.
        int compareDates = firstDate.compareTo(secondDate);
        // return which date comes first.
        return compareDates;
    }

}
