/**
 * 
 * MatrixMultiplier
 * 
 * @author nb2406
 * 
 *         Instructions: Given a matrix A ∈ R^n,n (i.e., A = ((a_ij)_i,j=1)^n is
 *         a square n × n matrix with a_ij ∈ R, for i,j = 1,...,n) and an
 *         integer k ≥ 0, write a program that computes A^(2^k) using k matrix
 *         multiplications.
 * 
 *         The input file should have the matrix size n in the first line, the
 *         value k in the second line, and each of the next n lines should have
 *         the entries of the respective matrix row separated by blanks. The
 *         output file should have n lines one for each row of A^(2^k). The
 *         entries of each row should be separated by blanks.
 * 
 */

import java.io.*;
import java.util.Scanner;

public class MatrixMultiplier {
	
	public static void main(String[] args) throws IOException {

		if (args.length == 2) {
			
			// Introduction
			System.out
			.println("\nThis program calculates A^(2^n), where A is a n x n square matrix\n");

			// Assign command-line args to file names
			String inFileName = args[0];
			File inFile = new File(inFileName);
			String outFileName = args[1];
			File outFile = new File(outFileName);

			// Checks if file exists before creating Scanner and PrintWriter files
			if (inFile.exists()) {
				Scanner input = new Scanner(inFile);
				PrintWriter output = new PrintWriter(outFile);

				System.out.println("Reading in input values from to \""
						+ inFileName + "\"\n");

				// Initializes variables for int n (to determine n x n matrix),
				// int k and double 2D array matrix (to solve the equation A^(2^k))
				int n;
				int k;
				double[][] A;

				// Parses input file for n and k values
				n = input.nextInt();
				System.out.println("n is " + n);
				A = new double[n][n];
				k = input.nextInt();
				System.out.println("k is " + k + "\n");

				// Initializes row and column to start at 0
				int row = 0;
				int column = 0;

				// Adds each double value to the correct A[row][column]
				// Column increases up to n - 2
				// If column equals n - 1, the row increases and column resets
				// ADDED: condition for row < n (prior to this program failed for
				// input files with extra enter spaces at the end of the file)
				while (input.hasNextLine() && row < n) {
					double entry = input.nextDouble();
					A[row][column] = entry;
					if (column < (n - 1)) {
						column++;
					} else if (column == (n - 1)) {
						row++;
						column = 0;
					}
				}

				// Closes the input after saving all its content
				input.close();

				// Prints out matrix from the input file
				System.out.println("Input (A):\n" + toString(A));

				// Initializes twosExponent to start at 0
				int exponent = 2;
				int twosExponent = 1;

				// Computes A^(2^k) by multiplying matrix to itself and
				// reassigning A to its squared value k times, for example if k
				// = 3, the loop runs three times: (1) A = A*A, (2) A = A*A (is
				// A^2A^2), (3) A = A*A (is A^4A^4 = A^8)
				while (twosExponent <= k) {
					A = multiplyMatrices(A, A);
					if (twosExponent < k) {
						System.out
								.println("A^" + exponent + "\n" + toString(A));
					} else if (twosExponent == k) {
						System.out.println("Output (A^" + exponent + "):\n"
								+ toString(A));
						output.println(toString(A));
						System.out
								.println("Successfully wrote final result to \""
										+ outFileName + "\"");
					}
					exponent *= 2;
					twosExponent++;
				}
				output.close();
			} else {
				System.out.println("No such file found, good bye!");
			}
		} else {
			System.out
			.println("\nInput and output files not properly specified. Try again.\n");

			// Runs hard coded matrices to test multiplication method if user doesn't specify input and output files
			double[][] matrix = new double[][] {
	                {0, 1, 0, 0},
	                {0, 0, 1, 0},
	                {0, 0, 0, 1},
	                {0, 0, 0, 0}
	        };
			double[][] test = new double[][] {
	                {0, 1, 0, 0, 0},
	                {0, 0, 1, 0, 0},
	                {0, 0, 0, 1, 0},
	                {0, 0, 0, 0, 0}
	        };
			System.out.println("4x4 matrix ^ 2\n" + toString(multiplyMatrices(matrix, matrix)));
			System.out.println("4x4 matrix * 4x5 test\n" + toString(multiplyMatrices(matrix, test)));
			System.out.println("4x5 test * 4x4 matrix:");
			System.out.println(toString(multiplyMatrices(test, matrix)));
			// Expected output: multiplication is impossible
		}

	}

	// General method that multiplies matrices such that D = BC
	// In this main, both B and C are always square matrix A
	public static double[][] multiplyMatrices(double[][] B, double[][] C) {

		// Check that columns in B are equal to rows in C
		// They must be, for multiplication to be valid
		int Bcols = B[0].length;
		int Crows = C.length;

		if (Bcols == Crows) {
			int Drows = B.length; // D will have B rows and C columns
			int Dcols = C[0].length;

			double[][] D = new double[Drows][Dcols];
			for (int i = 0; i < Drows; i++) { // 1. go through rows in B
				for (int j = 0; j < Dcols; j++) { // 2. go through columns in C
					for (int k = 0; k < Bcols; k++) { // 3. columns in B
						// Calculate dot product for each element. Definition:
						// d_ij = n_∑_(k=1) (b_ik c_kj)
						D[i][j] += B[i][k] * C[k][j];
					}
				}
			}
			return D;
		} else {
			System.out.println("This matrix multiplication is impossible. Good bye.");
			System.exit(0);
			return null;
		}
	}

	// Prints out the 2D array matrices with blanks between row entries to the console
	public static String toString(double[][] matrix) {
		String s = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				s += (matrix[i][j] + "\t");// String.format("%.5f",
												// matrix[i][j]);
			}
			s += "\n";
		}
		return s;
	}

	// public static double getNextNum(Scanner input) {
	// String s = input.next();
	// double d = Double.parseDouble(s);
	// return d;
	// }
	
}