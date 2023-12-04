
/**
 * @author Maddie McGreevy
 *
 */
public class DiceDriver {

	public static void main(String[] args) {
		Die die1 = new Die(6);
		Die die2 = new Die(6);
		int numberOfSevens = 0;

		for(int i = 1; i <= 100; i++) {
			die1.roll();
			die2.roll();
			if(die1.getFaceValue()+die2.getFaceValue() == 7) {
				numberOfSevens = numberOfSevens + 1;
			}
		
		}
		
		System.out.println("Out of 100 rolls, "+numberOfSevens+" resulted in 7");

	}
	
}
