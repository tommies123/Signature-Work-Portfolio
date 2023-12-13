import java.util.Comparator;

/**
 * This class will use the comparable interface in order to compare the todo
 * items that the user inputs. The importance level of each todo item will be
 * compared in this class.
 * 
 * @author Maddie McGreevy
 *
 */
public class ImportanceComparator implements Comparator<TodoItem> {

    @Override
    public int compare(TodoItem o1, TodoItem o2) {

        // set the o1 argument to the firstImportanceLevel that will be compared.
        Importance firstImportanceLevel = o1.getImportanceLevel();
        // set the o2 argument to the secondImportanceLevel that will be compared.
        Importance secondImportanceLevel = o2.getImportanceLevel();
        int importanceValue = 0;
        
        // if the firstImportanceLevel and secondImportanceLevel are equal to HIGH, then return 0.
        if (firstImportanceLevel == Importance.HIGH && secondImportanceLevel == Importance.HIGH) {
            importanceValue =  0;
        // if the firstImportanceLevel is equal to HIGH and secondImportanceLevel is not equal to HIGH,
        // then return -1.
        } else if (firstImportanceLevel == Importance.HIGH && secondImportanceLevel != Importance.HIGH) {
            importanceValue = -1;
        // if the firstImportanceLevel is equal to MEDIUM and secondImportanceLevel is equal to HIGH, then
        // return 1.
        } else if (firstImportanceLevel == Importance.MEDIUM && secondImportanceLevel == Importance.HIGH) {
            importanceValue = 1;
        // if the firstImportanceLevel and secondImportanceLevel are equal to MEDIUM, then return 0.
        } else if (firstImportanceLevel == Importance.MEDIUM && secondImportanceLevel == Importance.MEDIUM) {
            importanceValue = 0;
        // if the firstImportanceLevel is equal to MEDIUM and the secondImportanceLevel is equal to LOW,
        // then return -1.
        } else if (firstImportanceLevel == Importance.MEDIUM && secondImportanceLevel == Importance.LOW) {
            importanceValue = -1;
        // if the firstImportanceLevel and secondImportanceLevel are equal to LOW, then return 0.
        } else if (firstImportanceLevel == Importance.LOW && secondImportanceLevel == Importance.LOW) {
            importanceValue =  0;
        // if the firstImportanceLevel is equal to LOW and the secondImportanceLevel is equal to LOW, then
        // return 1.
        } else if (firstImportanceLevel == Importance.LOW && secondImportanceLevel != Importance.LOW) {
            importanceValue = 1;
        }
        // return the number that represents the compared importance levels.
        return importanceValue;  
    }


}
