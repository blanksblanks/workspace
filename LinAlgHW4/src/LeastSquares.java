import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
 * 
 * Programming (40pts). Write a program the fit a parabola to the data (ti ,
 * bi), i = 1, . . . , m. Your program should compute the matrix ATA from the
 * matrix A (see, e.g., equation (10) on page 223) and the vector AT b. Test
 * your program for m = 20. To generate the data (ti , bi), i = 1, . . . , m,
 * choose a parabola and sample it at m equally spaced points ti . This will
 * give you yi , i = 1, . . . , m. Using a random number generator perturb each
 * yi to get the bi = yi + ri , i = 1, . . . , m. You may assume that ri ∈ (0,
 * 1).
 * 
 * More Instructions:
 * 
 * Your program must take as input m (the number of data points), and C, D, E
 * (the coefficients of the equation representing a parabola y = C + Dt + Et*t
 * ). Your program will then generate perturbed data by choosing m equally
 * spaced time values t_i, and then plugging these t_i into the real parabola
 * equation above to find the real y_i. Then use a random number generator to
 * add or subtract a small value (r_i, between 0 and 1) from each of these y_i
 * to get the perturbed data b_i = y_i + r_i. Your program should then use the
 * t_i and b_i values as your data.
 * 
 * Once you have data, your program must try to reconstruct the original
 * parabola coefficients C, D, E using the fitting technique discussed (on pages
 * 211-212) in the text. Output your calculated coefficients and compare them to
 * the original C, D, E values you used to generate the data. Test your program
 * with m >= 20, but make sure your program works for arbitrary m values.
 * 
 * To assist with grading, your input parameters must be read form a file and
 * the following file format must be used. The first line will contain the value
 * of m, the second C, the third D and the fourth E.
 * 
 * As output, you can either print to the terminal or to a file or both.
 */

public class LeastSquares {
	
	/*
	 * Print out the 2D array matrices with blanks between row entries to the
	 * console. Format option 0: full values, option 1: truncated
	 */
	public static String toString(double[][] matrix, int format) {
		String s = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (format == 1) {
					s += String.format("%8.2f", matrix[i][j]);
				} else {
					s += (matrix[i][j] + "\t");
				}
			}
			s += "\n";
		}
		return s;
	}

	// Overloaded print methods
	public static void print(String s) {
		System.out.println(s);
	}

	public static void print(double d) {
		System.out.printf("%.2f\n", d);
	}

	public static void main(String[] args) throws IOException {

		if (args.length == 1) {
			// Assign command-line args to file names
			File inFile = new File(args[0]);

			if (inFile.exists()) {
				Scanner input = new Scanner(inFile);
				print("Reading in input values from \"" + args[0]
						+ "\"...\n");

				// Parses input file for  m  (# data points), and C, D, E
				// (coefficients of parabola equation y = C + Dt + Et*t)
				int m;
				double C, D, E;
				m = input.nextInt();
				C = input.nextDouble();
				D = input.nextDouble();
				E = input.nextDouble();
				input.close();

				// Prints out matrix from the input file
				print("m: " + m + " data points");
				print("C: " + C + "\tD: " + D + "\tE: " + E);
				print("y = " + C + " + " + D + "t + " + E + "t*t");

				double[][] A = new double[m][3];
				double[][] b = new double[m][1];
				for (int t = 0; t < m; t++){
					A[t][0] = 1;
					A[t][1] = t;
					A[t][2] = t*t;
					double r = Math.random(); // perturbation r_i, > 0.0 and < 1.0
					double perturbed = C + D*t + E*t*t + r;
					b[t][0] = perturbed;
				}
				print("A:");
				print(toString(A, 1));
				print("b:");
				print(toString(b, 1));

			} else {
				print("No such input file found, good bye!");
			}
		} else {
			System.out
					.println("\nInput and/or output files not properly specified. "
							+ "Try again.\n");
		}

	}
	
}
