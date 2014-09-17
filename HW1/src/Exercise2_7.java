
// TODO: Implement the code in Java, and give the running time for several values of N.
// TODO: Compare your analysis with the actual running times.

public class Exercise2_7 {
	
	public static void main(String[] args) {
		
		int[] numbers = {1, 500, 1000, 2500, 4000};
		TimeInterval time = new TimeInterval();
		Sum sum = new Sum();
		
		for (int i = 0; i < numbers.length; i++) {
			for (int sumNum = 0; sumNum < 6; sumNum++) {
				switch (sumNum) {
				case 0: time.startTiming();
						int sum1 = sum.sumOne(numbers[i]);
						time.endTiming();
						double runtime1 = time.getElapsedTime();
						System.out.println("The first sum of " + numbers[i] + " is " + sum1 + " and the running time is " + runtime1 + " in milliseconds.");
						break;
	            case 1: time.startTiming();
						int sum2 = sum.sumTwo(numbers[i]);
						time.endTiming();
						double runtime2 = time.getElapsedTime();
	            		System.out.println("The second sum of " + numbers[i] + " is " + sum2 + " and the run time is " + runtime2 + " in milliseconds.");
						break;
	            case 2: time.startTiming();
						int sum3 = sum.sumThree(numbers[i]);
						time.endTiming();
						double runtime3 = time.getElapsedTime();
						System.out.println("The third sum of " + numbers[i] + " is " + sum3 + " and the run time is " + runtime3 + " in milliseconds.");
						break;
	            case 3: time.startTiming();
	            		int sum4 = sum.sumFour(numbers[i]);
						time.endTiming();
						double runtime4 = time.getElapsedTime();
	        			System.out.println("The fourth sum of " + numbers[i] + " is " + sum4 + " and the run time is " + runtime4 + " in milliseconds.");
	            		break;
	            case 4: time.startTiming();
	            		int sum5 = sum.sumFive(numbers[i]);
						time.endTiming();
						double runtime5 = time.getElapsedTime();
	        			System.out.println("The fifth sum of " + numbers[i] + " is " + sum5 + " and the run time is " + runtime5 + " in milliseconds.");
	            		break;
	            case 5: time.startTiming();
	            		int sum6 = sum.sumSix(numbers[i]);
						time.endTiming();
						double runtime6 = time.getElapsedTime();
	        			System.out.println("The sixth sum of " + numbers[i] + " is " + sum6 + " and the run time is " + runtime6 + " in milliseconds.");
	            		break;
				}
			}
		}
	}
}
