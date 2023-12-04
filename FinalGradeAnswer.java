import java.util.Scanner;

/**
 * This class is a solution to a task given in CISC 230 lecture.
 * The program asks the user for their exam, lab, and task 
 * averages and computes their final grade.
 * 
 * @author Prof. Yilek
 *
 */
public class FinalGradeAnswer {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        double labAverage;
        double examAverage;
        double taskAverage;
        double finalPercentage;
        
        System.out.print("Enter your lab average (0-100): ");
        labAverage = keyboard.nextDouble();
        
        System.out.print("Enter your exam average (0-100): ");
        examAverage = keyboard.nextDouble();
       
        System.out.print("Enter your daily task average (0-100): ");
        taskAverage = keyboard.nextDouble();

        finalPercentage = labAverage*.60+examAverage*.35+taskAverage*.05;
        
        System.out.println("Your final semester percentage is "+finalPercentage);
        
        // compute letter grade
        // note that + and - are missing
        // also missing is 55% rule for passing
        if (finalPercentage>=90.0) {
            System.out.println("You got an A");
        } else if (finalPercentage>=80.0) {
            System.out.println("You got a B");
        } else if (finalPercentage>=70.0) {
            System.out.println("You got a C");
        } else if (finalPercentage>=60.0) {
            System.out.println("You got a D");
        } else {
            System.out.println("You got an F");
        }
        
        
        

    }

}
