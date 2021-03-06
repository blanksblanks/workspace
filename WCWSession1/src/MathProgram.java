
public class MathProgram {
	
	public static void main (String[] args) {
		long a = 1989;
		long b = 1590;
		long answer = euclid(a, b);
		System.out.println("The greatest common divider of " + a +
				" and " + b + "is" + answer);
	}
	
	// Euclid�s algorithm for computing the greatest common divisor
	public static long euclid ( long m, long n ) {
		System.out.print("Sequence: ");
		while (n != 0) {
			long rem = m % n;
			m = n;
			n = rem;
			System.out.print(n + " ");
		}
		System.out.println();
		return m;
	}
}
