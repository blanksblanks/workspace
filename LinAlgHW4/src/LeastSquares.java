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
 * 
 * TODO: Complete solver with all functions, private/public methods, let
 * computer do work of figuring out row/col # of new matrices, not you,
 * overloaded method for forward eliminate too will be nice
 * 
 * Ways to test: increase the number of data points/ reduce the noise in randomizer
 */



public class LeastSquares {

	public static double[][] transpose(double[][] matrix, int row, int col) {
		double[][] transpose = new double[col][row];
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				transpose[j][i] = matrix[i][j];
		return transpose;
	}

	public static double[][] multiply(double[][] first, double[][] second,
			int m, int col, int row, int n) {
		if (col != row) {
			System.err
					.println("Number of columns of first matrix doesn't equal number of rows of second matrix");
			System.exit(0);
		}
		double[][] product = new double[m][n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				double dot = 0;
				for (int k = 0; k < col; k++)
					dot += first[i][k] * second[k][j];
				product[i][j] = dot;
			}
		return product;
	}

	// Takes matrix A and vector b and returns an augmented matrix
	public static double[][] augment(double[][] matrix, double[][] vector, int n) {
		double[][] augmented = new double[n][n + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				augmented[i][j] = matrix[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			augmented[i][n] = vector[i][0];
		}
		return augmented;
	}

	/*
	 * Forward elimination reduces the system to upper-triangular form.
	 */
	public static double[][] forwardEliminate(double[][] A, int n) {
		for (int k = 0; k < (n - 1); k++) {
			for (int i = k + 1; i < n; i++) {
				double multiplier = A[i][k] / A[k][k]; // # by which A[k][j]
				// is multiplied before being subtracted from A[i][j]
				for (int j = k; j < n; j++) {
					A[i][j] = A[i][j] - (multiplier * A[k][j]); // zeroes out
					// non-zero entries in A below the diagonal - moving from
					// top left to bottom right
				}
				A[i][n] = A[i][n] - (multiplier * A[k][n]); // updates bi
			}
		}
		return A;
	}

	/*
	 * Back substitution solves the simplified system of equations, in upper
	 * triangular form, to find the values of x[i] (all in column [0]) - moving
	 * top to bottom
	 */
	public static double[][] backSubstitute(double[][] U, int n) {
		double[][] x = new double[n][1];
		x[n - 1][0] = U[n - 1][n] / U[n - 1][n - 1]; // finds initial x by
		// dividing bottom b value by bottom coefficient value of matrix A
		for (int i = (n - 1); i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				U[i][n] -= U[i][j] * x[j][0]; // subtracts known value(s) from b
			}
			x[i][0] = U[i][n] / U[i][i]; // finds x by dividing new b by
											// coefficient
		}
		return x;
	}

	/*
	 * Print out the 2D array matrices with blanks between row entries to the
	 * console. Format opt 0: full values, opt 1: truncated, opt 3: long trunc
	 */
	public static String toString(double[][] matrix, int format) {
		String s = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (format == 1)
					s += String.format("%8.2f", matrix[i][j]);
				else if (format == 2)
					s += String.format("%10.2f", matrix[i][j]);
				else
					s += ("    " + matrix[i][j]);
			}
			s += "\n";
		}
		return s;
	}

	// Overloaded print methods
	public static void print(double d) {
		System.out.printf("%8.2f\n", d);
	}

	public static void print(String s) {
		System.out.println(s);
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
				int m, n;
				double C, D, E;
				m = input.nextInt();
				n = 3; // bc 3 unknowns: C, D, E
				C = input.nextDouble();
				D = input.nextDouble();
				E = input.nextDouble();
				input.close();

				// Prints out matrix from the input file
				print("m: " + m + " data points");
				print("C: " + C + "\tD: " + D + "\tE: " + E);
				print("y = " + C + " + " + D + "t + " + E + "t*t\n");

				double[][] A = new double[m][n];
				double[][] b = new double[m][1];
				for (int t = 0; t < m; t++){
					A[t][0] = 1;
					A[t][1] = t;
					A[t][2] = t*t;
					double yi = C + D*t + E*t*t;
					double ri = Math.random(); // perturbation bt 0.0 and 1.0
					// ways to test: /1000 to see if xhat values are closer
					double bi =  yi + ri;
					b[t][0] = bi;
				}
				
				print("A where m row vectors each contain 1, ti and ti*ti:\n" + toString(A, 1));
				print("b where bi = yi + ri:\n" + toString(b, 1));
				
				print("Step by step, we can solve solve ATA x_hat = ATb\n");
				
				double[][] AT = new double[n][m];
				AT = transpose(A, m, n);
				print("AT:\n" + toString(AT, 1));
				
				double[][] ATA = new double[n][n];
				ATA = multiply(AT, A, n, m, m, n);
				print("ATA:\n" + toString(ATA, 2));

				double[][] ATb = new double[n][1];
				ATb = multiply(AT, b, 3, m, m, 1);
				print("ATb:\n" + toString(ATb, 2));
				
				double[][] aug = new double[n][n + 1];
				aug = augment(ATA, ATb, 3);
				print("Augmented form:\n" + toString(aug, 2));

				double[][] elim = new double[n][n + 1];
				elim = forwardEliminate(aug, 3);
				print("Upper triangle form:\n" + toString(elim, 2));

				double[][] x_hat = new double[n][1];
				x_hat = backSubstitute(elim, n);
				print("Vector x_hat contains our calculated coefficients:\n" + toString(x_hat, 0));
				
				print("We can compare these to the original C, D, E values:");
				print(C);
				print(D);
				print(E);
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
