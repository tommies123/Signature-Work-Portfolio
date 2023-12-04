import java.util.Random;

/**
 * @author Maddie McGreevy
 *
 */
public class Die {

	// instance variables (internal state)
	/**
	 * How many sides the Die has.
	 */
	private int numberOfSides;
	
	/**
	 * The current face value of the Die.
	 */
	private int faceValue;
	
	
	// constructors
	
	/** 
	 * Describe constructors...
	 * 
	 * @param sides The number of sides the Die should have.
	 */
	public Die(int sides) {
		// job: put meaningful values into all instance variables
		numberOfSides = sides;
		faceValue = 1;
		
	}
	
	// methods (roll and getFaceValue)
	
	/**
	 *  Description of method.
	 *  
	 * @return The current face value of the Die object.
	 */
	public int getFaceValue() {
		return faceValue;
	}
	
	/**
	 * Rolls the current Die.
	 */
	public void roll() {
		Random generator = new Random();
		faceValue = generator.nextInt(numberOfSides)+1;
		
	}
}
