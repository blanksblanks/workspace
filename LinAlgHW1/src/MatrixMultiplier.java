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

		System.out
				.println("\nThis program calculates A^(2^n), where A is a n x n square matrix\n");

		if (args.length > 0) {

			// Assign command-line args to file names
			String inFileName = args[0];
			File inFile = new File(inFileName);
			String outFileName = args[1];
			File outFile = new File(outFileName);

			// Checks if file exists before creating Scanner and Output files
			if (inFile.exists()) {
				Scanner input = new Scanner(inFile);
				PrintWriter output = new PrintWriter(outFile);

				System.out.println("Reading in input values from to \""
						+ inFileName + "\"\n");

				// Initializes variables for int n (to determine n x n matrix),
				// int k
				// and double 2D array matrix (to solve the equation A^(2^k))
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

				// Adds each double value to the correct matrix[row][column]
				// Column increases up to n - 2
				// If column equals n - 1, the row increases and column resets
				while (input.hasNextLine()) {
					double element = input.nextDouble();
					A[row][column] = element;
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
					.println("No input and output files specified, good bye!");
		}

	}

//	public static double getNextNum(Scanner input) {
//		String s = input.next();
//		double d = Double.parseDouble(s);
//		return d;
//	}

	// Matrix D = BC such that d_ij= ∑ b_ik c_kj,_(k=1)^i,j=1,…,n.
	public static double[][] multiplyMatrices(double[][] B, double[][] C) {
		int bCols = B[0].length; // B columns length
		int cRows = C.length; // C rows length
		if (bCols != cRows)
			return null; // matrix multiplication is not possible
		int dRows = B.length; // m result rows length
		int dCols = C[0].length; // m result columns length
		double[][] D = new double[dRows][dCols];
		for (int i = 0; i < dRows; i++) { // rows from m1
			for (int j = 0; j < dCols; j++) { // columns from c and d
				for (int k = 0; k < bCols; k++) { // columns from m1
					D[i][j] += B[i][k] * C[k][j];
				}
			}
		}
		return D;
	}

	public static String toString(double[][] matrix) {
		String result = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				result += (matrix[i][j] + "\t");// String.format("%.5f",
												// matrix[i][j]);
			}
			result += "\n";
		}
		return result;
	}

}

/*
 * // #1 double[][] multiplicand = new double[][] { {0, 1, 0, 0}, {0, 0, 1, 0},
 * {0, 0, 0, 1}, {0, 0, 0, 0} }; double[][] multiplier = new double[][] { {0, 1,
 * 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 0} };
 * 
 * System.out.println("#1\n" + toString(multiplyMatrices(multiplicand,
 * multiplier)));
 */

/*
 * //figure out the dimensions of the matrix from LINE 1 (n) n =
 * Integer.parseInt(input.nextLine()); System.out.println("n = " + n);
 * 
 * //figure out the value of k from LINE 2 k =
 * Integer.parseInt(input.nextLine()); System.out.println("k = " + k);
 * 
 * //make the dimensions of the 2D array based on n matrix = new float[n][n];
 * 
 * //rows and columns of the matrix int row = 0; int column = 0;
 * 
 * //read the rest of the input file, placing the contents into the 2D matrix
 * while (reader.hasNextLine()) { float next = reader.nextFloat();
 * matrix[row][column] = next; if (column == (n-1)) { row++; column = 0; } else
 * { column++; } }
 */

// for (int i = 0; i < n; i++) {
// for (int j = 0; j < n; i++) {
// arr[i][j] = getNextNum(input);
// System.out.println("arr" + i + j + " is " + arr[i][j]);
// }
// }
//
// System.out.println("#2\n" + toString(multiplyMatrices(multiplicand,
// multiplier)));
