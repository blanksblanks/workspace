/**
 * 
 * MatrixSolver
 * 
 * @author nb2406
 * 
 *         Write a program that solves a system of linear equations using
 *         elimination and backsubstitution, where the coefficient matrix is
 *         m-diagonal and m ∈ N is odd; see Ch. 9, p. 468.
 * 
 *         You may use the code on p. 100 or the pseudocode in the class notes,
 *         however, you should modify it to take advantage of the structure of
 *         the system. Do not perform pivoting or row swaps; they are not
 *         necessary for certain matrices, such as the one in the example on p.
 *         468.
 * 
 *         Your program should take as input the matrix dimension n, the number
 *         m, the coefficient matrix, and the right hand side vector b. It
 *         should output the solution of the system. Test your program using n =
 *         10 and m = 3, 5.
 * 
 */

import java.io.*;
import java.util.Scanner;

public class MatrixSolver {

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
	 * Forward elimination reduces the system to upper-triangular form. Takes
	 * advantage of the m-diagonal form by eliminating only the nonzero elements
	 * in the matrix (that is, the ones (m-1)/2 rows below and columns to the
	 * right of the diagonal).
	 */
	public static double[][] forwardEliminate(double[][] A, int m, int n) {
		int nonzero = (m - 1) / 2;
		for (int k = 0; k < (n - 1); k++) {
			for (int i = k + 1; (i <= k + nonzero) && (i < n); i++) {
				double multiplier = A[i][k] / A[k][k]; // # by which A[k][j]
				// is multiplied before being subtracted from A[i][j]
				for (int j = k; (j <= k + nonzero) && (j < n); j++) {
					A[i][j] = A[i][j] - (multiplier * A[k][j]); // zeroes out
					// non-zero entries in A below the diagonal - moving from
					// top left to bottom right
				}
				A[i][n] = A[i][n] - (multiplier * A[k][n]); // updates b's
															// entries

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
	 * Prints out the 2D array matrices with blanks between row entries to the
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

	public static void main(String[] args) throws IOException {

		if (args.length == 2) {

			// Introduction
			System.out
					.println("\nThis program solves systems of linear equations "
							+ "(A x = b) using elimination and back substitution, "
							+ "where the coefficient matrix is m-diagonal and "
							+ "m is odd.\n");

			// Assign command-line args to file names
			String inFileName = args[0];
			File inFile = new File(inFileName);
			String outFileName = args[1];
			File outFile = new File(outFileName);

			// Checks if file exists before creating input/output files
			if (inFile.exists()) {
				Scanner input = new Scanner(inFile);
				PrintWriter output = new PrintWriter(outFile);

				System.out.println("Reading in input values from \""
						+ inFileName + "\"...\n");

				// Initializes variables for n, m, coefficient matrix, rhs
				// vector b, (to solve the equation A x = b)
				int n;
				int m;
				double[][] A;
				double[][] b;

				// Parses input file for values
				n = input.nextInt();
				System.out.println("n: " + n);
				A = new double[n][n];
				b = new double[n][1];
				m = input.nextInt();
				System.out.println("m: " + m + "\n");

				// Initializes row and column to start at 0
				int row = 0;
				int column = 0;
				int mcount = 0;
				int vcount = 0;

				// Adds each double value to the correct A[row][column]
				// If column equals n - 1, the row increases and column resets
				// to 0
				while (input.hasNext() && (mcount < (n * n))) {
					double entry = input.nextDouble();
					A[row][column] = entry;
					if (column < (n - 1)) {
						column++;
					} else if (column == (n - 1)) {
						row++;
						column = 0;
					}
					mcount++;
				}

				while (input.hasNext() && (vcount < n)) {
					double entry = input.nextDouble();
					b[vcount][0] = entry;
					vcount++;
				}

				// Closes the input after saving all its content
				input.close();

				// Prints out matrix from the input file
				System.out.println("Matrix A:\n" + toString(A, 1));
				System.out.println("Vector b:\n" + toString(b, 1));

				double[][] aug = new double[n][n + 1];
				aug = augment(A, b, n);
				System.out.println("Augmented matrix form in which first " + n
						+ " columns are A, last column is b:\n"
						+ toString(aug, 1));

				double[][] elim = new double[n][n + 1];
				elim = forwardEliminate(aug, m, n);
				System.out
						.println("Matrix A after elimination and ready for back substitution:\n"
								+ toString(elim, 1));

				double[][] x = new double[n][1];
				x = backSubstitute(elim, n);
				System.out.println("Vector x:\n" + toString(x, 1));

				output.println(toString(x, 0));
				System.out.println("Successfully wrote solution x to \""
						+ outFileName + "\" (*Note values in the output file are not truncated)\n");
				output.close();

			} else {
				System.out.println("No such input file found, good bye!");
			}
		} else {
			System.out
					.println("\nInput and/or output files not properly specified. "
							+ "Try again.\n");
		}

	}
}
