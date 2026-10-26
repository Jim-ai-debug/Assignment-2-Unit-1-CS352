/* Contestant.java:  
 * This class extends Person while adding attributes and methods
 * related to recording performance in the specified contest. 
 *     
 * Author: Jimmel 1767394
 * Last updated: 10/25/2026
 */

public class Contestant extends Person {
	// A Contestant IS-A Person
	// that also needs needs to keep track of data related to the game
	// Start simple to get your program running, then add attributes and methods as you need them
	
	// Step 1 Add at least one constructor so the driver will compile and run
	private int positionTotal;
	private int positionCount;

	public Contestant(String name, int age) {
		super(name, age);
		positionTotal = 0;
		positionCount = 0;
	}

	// Step 5 Add attributes and methods to assist with recording game statistics
	/**
	 * Records Contestant's position at end of round.
	 * @param position the Contestant's zero-based position in the ArrayList
	 */
	public void recordPosition(int position) {
		positionTotal += position;
		positionCount++;
	}

	/**
	 * Calculates Contestant's average recorded position in list.
	 * @return the average position of Contestent.
	 */
	public double getAveragePosition() {
		if (positionCount == 0) {
			return 0.0;
		}
		return (double) positionTotal / positionCount;
	}

	@Override
	public String toString() {
		return "Contestant [name=" + getName() + ", age=" + getAge()
				+ ", average position=" + getAveragePosition() + "]";
	}
}
