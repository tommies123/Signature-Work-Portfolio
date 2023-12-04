import java.util.Scanner;

/**
 * This class should ask the user for their lab average, their exam
 * average, and their daily task average and compute and display their
 * final semester percentage. It then should display their final letter
 * grade.
 * 
 * @author Maddie McGreevy
 *
 */
public class FinalGrade {

	public static void main(String[] args) {
		double labAverage = 0;
		double examAverage = 0;
		double dailyTasksAverage = 0;
		double semesterGrade = 0;
		Scanner keyboardInput = new Scanner(System.in);

		// ask the user for their average grades between 0 and 100
		System.out.print("Enter your average lab grade (between 0 and 100): ");
		labAverage = keyboardInput.nextDouble();

		System.out.print("Enter your average exam grade (between 0 and 100): ");
		examAverage = keyboardInput.nextDouble();

		System.out.print("Enter your average daily tasks grade (between 0 and 100): ");
		dailyTasksAverage = keyboardInput.nextDouble();
		
		System.out.println();
		
		// print out the semester grade percentage 
		semesterGrade = (labAverage*0.6) + (examAverage*0.35) + (dailyTasksAverage * 0.05);
		System.out.println("Your semester grade percentage is: "+semesterGrade);
		
		
		// letter grades for grade percentages
		if (semesterGrade >= 90.0 && semesterGrade <= 100.0) {
			System.out.println("Your final letter grade is: A");
		} else if (semesterGrade >= 80.0 && semesterGrade <= 89.9) {
			System.out.println("Your final letter grade is: B");
		} else if (semesterGrade >= 70.0 && semesterGrade <= 79.9) {
			System.out.println("Your final letter grade is: C");
		} else if (semesterGrade >= 60.0 && semesterGrade <= 69.9) {
			System.out.println("Your final letter grade is: D");
		} else if(semesterGrade <= 59.9) {
			System.out.println("Your final letter grade is: F");
		} else if(semesterGrade > 100.0) {
			System.out.println("You have made a mistake. Please re-run the program with grades between 0 and 100.");
		}

	}


}	



