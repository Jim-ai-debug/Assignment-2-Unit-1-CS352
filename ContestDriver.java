import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class ContestDriver {

	/**
	 * Creates list of various Contestants with names.
	 * @param count certain number of contestants to be created
	 * @return ArrayList containing requested contestants
	 */
	private static ArrayList<Contestant> createContestants(int count) {
		ArrayList<Contestant> contestants = new ArrayList<>(count);

		for (int i = 1; i <= count; i++) {
			contestants.add(new Contestant("Contestant-" + i, 18 + (i % 10)));
		}

		return contestants;
	}

	/**
	 * Flips a coin using a boolean
	 * @return true about half the time and false about half the time
	 */
	private static boolean flipCoin() {
		return ThreadLocalRandom.current().nextBoolean();
	}

	/**
	 * Plays one round of the contest.
	 * Correct answers move contestants to front and incorrect moves contestants to back.
	 * @param contestants the current line of Contestants
	 */
	private static void playRound(ArrayList<Contestant> contestants) {
		ArrayList<Contestant> correct = new ArrayList<>(contestants.size());
		ArrayList<Contestant> incorrect = new ArrayList<>(contestants.size());

		for (Contestant contestant : contestants) {
			if (flipCoin()) {
				correct.add(contestant);
			} else {
				incorrect.add(contestant);
			}
		}

		/*
		 * Correct Contestants are added in reverse order so that the first
		 * correct Contestant processed ends up at the front, matching the
		 * movement rules in the assignment example.
		 */
		Collections.reverse(correct);

		contestants.clear();
		contestants.addAll(correct);
		contestants.addAll(incorrect);
	}

	/**
	 * Records the position of every contestant currently.
	 * @param contestants the current line of Contestants
	 */
	private static void recordPositions(ArrayList<Contestant> contestants) {
		for (int i = 0; i < contestants.size(); i++) {
			contestants.get(i).recordPosition(i);
		}
	}

	/**
	 * Runs simulation for the listed number of rounds.
	 * @param contestants the current line of Contestants
	 * @param rounds number of rounds to run
	 */
	private static void runSimulation(ArrayList<Contestant> contestants, int rounds) {
		for (int round = 0; round < rounds; round++) {
			playRound(contestants);
			recordPositions(contestants);
		}
	}

	/**
	 * Runs timed section of the simulation.
	 * @param contestants the current line of Contestants
	 * @param rounds the number of rounds to run
	 * @return time elasped in milliseconds
	 */
	private static double timedSimulation(ArrayList<Contestant> contestants, int rounds) {
		long start = System.nanoTime();

		for (int round = 0; round < rounds; round++) {
			playRound(contestants);
		}

		long end = System.nanoTime();
		return (end - start) / 1_000_000.0;
	}

	public static void main(String[] args) {
		System.out.println("Starting Simulation");

		// Step -1 Compile the program as is to be sure it runs

		// Step 0 - Uncomment the two lines below, then add code to the Contestant class to make this compile
		// This creates a single Contestant and prints its attributes, just to show it can be done
		Contestant ac = new Contestant("Billy",22);
		System.out.println(ac);
		
		// Step 2
		// Create multiple Contestants and add them to an ArrayList
		// Do something simple to get started, but remember, you will need
		// to generate up to 1,000,000 Contestants	
		int numberOfContestants = 8;
		ArrayList<Contestant> contestants = createContestants(numberOfContestants);
		System.out.println("\nInitial line:");
		System.out.println(contestants);

		// Steps 3 and 4
		// Play a round
		// For each Contestant, flip a coin, then move the Contestant to
		// one end of the ArrayList or the other.
		playRound(contestants);
		System.out.println("\nAfter one round:");
		System.out.println(contestants);
		
		// Steps 5 - 7
		// Repeat Steps 3 and 4 many times to gather data.
		// You will need to modify the Contestant class so each Contestant
		// will keep track of its individual statistics. Be smart about how you record its
		// position at the end of each round.
		int fairnessRounds = 12;
		runSimulation(contestants, fairnessRounds);

		System.out.println("\nFairness results after " + fairnessRounds + " rounds:");
		for (Contestant contestant : contestants) {
			System.out.printf("%s average position: %.2f%n",
					contestant.getName(), contestant.getAveragePosition());
		}

		System.out.println("\nTiming results:");

		int[] roundCounts = {10, 100, 1000, 10000};

		int smallContestantCount = 43;
		System.out.println("\n43 Contestants");
		for (int rounds : roundCounts) {
			ArrayList<Contestant> testContestants =
					createContestants(smallContestantCount);
			double time = timedSimulation(testContestants, rounds);
			System.out.printf("%d rounds: %.3f ms%n", rounds, time);
		}

		int largeContestantCount = 45000;
		System.out.println("\n45,000 Contestants");
		for (int rounds : roundCounts) {
			ArrayList<Contestant> testContestants =
					createContestants(largeContestantCount);
			double time = timedSimulation(testContestants, rounds);
			System.out.printf("%d rounds: %.3f ms%n", rounds, time);
		}
		
		System.out.println("Finished");
	}
}
