
public class OrderOfGrowth {

	public static void main(String[] args) {
//		double[] arr = {0.001, 0.005, 0.056, 0.464, 4.595, 41.823, 374.390, 3365.759};
//		double[] arr = {0.003, 0.016, 0.096, 0.555, 3.354, 20.688, 122.295, 738.061, 4445.938};
		double[] arr = {0.002, 0.013, 0.080, 0.529, 3.449, 21.867, 143.949, 928.452, 5988.783};
		double[] total = new double[arr.length - 1];
		System.out.println("ratio\tlog2(ratio)");
		for (int i = 0; i < arr.length - 1; i++) {
			double ratio = arr[i+1]/arr[i];
			double log = Math.log(ratio)/Math.log(2);
			total[i] = log;
			System.out.println(i + "\t" + ratio + "\t" + log);
		}
		double average = findAverage(total);
		System.out.println("Average: " + average);
	}
	
	public static double findAverage(double[] numbers){
		double avg = 0;
		for (int i = 0; i < numbers.length; i++) {
			avg += numbers[i];
		}
		avg /= numbers.length;
		return avg;
	}
}
	
