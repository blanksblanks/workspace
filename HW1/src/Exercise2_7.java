
// TODO: Implement the code in Java, and give the running time for several values of N.
// TODO: Compare your analysis with the actual running times.

public class Exercise2_7 {
	
	public static void main(String[] args) {
		
		System.out.println("Loop\tN\tRuntime (ms)\tSum");
		
		int[] millions = {1000000, 2000000, 3000000, 4000000, 5000000, 6000000, 7000000, 8000000, 9000000};
		int[] hundreds = {100, 200, 300, 400, 500, 600, 700, 800, 900};
		
		for (int loop = 1; loop <= 4; loop++) {
			for (int i = 0; i < millions.length; i++) {
				runTimeFor(loop, millions[i]);
			}
		}
			
		for (int loop = 5; loop <= 6; loop++) {
			for (int i = 0; i < hundreds.length; i++) {
				runTimeFor(loop, hundreds[i]);
			}
		}
	}
	
	public static void runTimeFor(int sumNum, int someNum) {

		TimeInterval time = new TimeInterval();
		time.startTiming();
		double runTime;
		int sum = 0;

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
//		System.out.println("Loop: " + sumNum + " N: " + someNum +
//		" Running time: " + runTime + " ms\n");
		System.out.println("  " + sumNum + "\t" + someNum + "\t" + runTime + "\t\t" + sum);


	}
	
}
