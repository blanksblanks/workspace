/**
 * 
 * Exercise 2_7
 * 
 * @author nb2406
 * 
 *         Weiss, Exercise 2.7: Implement the code [six loops that return sums]
 *         in Java, and give the running time for several values of N. 
 *         
 */

public class Problem2 {

	public static void main(String[] args) {

		System.out.println("Loop\tN\tRuntime (ms)\tSum");

		double[] tens = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
		// Commented out because running times took too long for doubles vs.
		// ints
		// double[] hundreds = {100, 200, 300, 400, 500, 600, 700, 800, 900};
		// double[] thousands = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000,
		// 9000};
		// double[] millions = {1000000, 2000000, 3000000, 4000000, 5000000,
		// 6000000, 7000000, 8000000, 9000000};

		for (int loop = 1; loop <= 6; loop++) {
			for (int i = 0; i < tens.length; i++) {
				runningTimeFor(loop, tens[i]);
			}
		}
	}

	// Gives running times for several values of N based on which loop is
	// calculating N
	public static void runningTimeFor(int sumNum, double someNum) {

		TimeInterval time = new TimeInterval();
		time.startTiming();
		double runTime;
		double sum = 0;

		Sum test = new Sum();

		switch (sumNum) {
			case 1:
				sum = test.sumOne(someNum);
				break;
			case 2:
				sum = test.sumTwo(someNum);
				break;
			case 3:
				sum = test.sumThree(someNum);
				break;
			case 4:
				sum = test.sumFour(someNum);
				break;
			case 5:
				sum = test.sumFive(someNum);
				break;
			case 6:
				sum = test.sumSix(someNum);
				break;
		}

		time.endTiming();
		runTime = time.getElapsedTime();
		// System.out.println("Loop: " + sumNum + " N: " + someNum +
		// " Running time: " + runTime + " ms\n");
		System.out.println("  " + sumNum + "\t" + someNum + "\t" + runTime
				+ "\t\t" + sum);

	}

}
