/**
 * 
 * Complete MatrixSolver
 * 
 * @author nb2406
 * 
 *         Write a program that solves a system of linear equations Ax = b where
 *         the coefficient matrix A ∈ R^m,n. Your program should determine if
 *         the system has a unique solution, infinity many solutions or no
 *         solution at all. You program should take as input the matrix
 *         dimensions m, n, the matrix and the right hand side vector b. It
 *         should output a statement concerning the solution (no solution,
 *         unique solution, infinity many solutions) and the solution if any. In
 *         the case of infinity many solutions, you should output a particular
 *         solution xp and the special solutions, i.e. the vectors that span
 *         N(A). Test your program using matrices of size at least 4 × 7 and
 *         make sure you cover the three cases above concerning the solution.
 * 
 */

import java.io.*;
import java.util.Scanner;

public class CompleteMatrixSolver {

	// Take matrix mxn A and mx1 vector b and returns an augmented matrix
	public static double[][] augment(double[][] matrix, double[][] vector,
			int m, int n) {
		double[][] augmented = new double[m][n + 1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				augmented[i][j] = matrix[i][j];
			}
		}
		for (int i = 0; i < m; i++) {
			augmented[i][n] = vector[i][0];
		}
		return augmented;
	}

	// Transform augmented matrix into RREF
	public static double[][] toRREF(double[][] M, int m, int n) {
		int piv = 0;
		for (int k = 0; k < m; k++) {
			if (piv >= n) // done
				break;
			{
				int i = k;
				while (M[i][piv] == 0) {
					i++; // search column for nonzero element
					if (i == m) {
						i = k; // move on to next column 
						piv++;
						if (piv == n)
							return M; // already rref
					}
				}
				double[] temp = M[k]; // row swap
				M[k] = M[i];
				M[i] = temp;
			}
			// turn pivots into 1's
			{
				double factor = M[k][piv];
				for (int j = 0; j < n+1; j++)
					M[k][j] /= factor;
			}
			// eliminate other elements in the same column
			for (int i = 0; i < m; i++) {
				if (i != k) {
					double factor = M[i][piv];
					for (int j = 0; j < n+1; j++) {
						M[i][j] -= factor * M[k][j];
						if (M[i][j] == -0.00)
							M[i][j] = 0.00;
					}
				}
			}
			piv++;
		}
		return M;
	}

	/* Find possible solutions, which depend on rank r:
	 * r == m && r == n has one solution
	 * r == m && r < n infinite solutions
	 * r < m && r == n has 0 or 1 solution
	 * r < m && r < n has 0 or infinity solutions
	 */
	public static boolean findSol(double[][] matrix, int m, int n) {
		double[] b = new double[m];
		int r = 0;

		// create new vector b with updated values
		for (int i = 0; i < m; i++) {
			b[i] = matrix[i][n];
		}

		// Find rank by traversing down the diagonal, skipping columns if necessary to count the number of pivot columns
		for (int i = 0; i < m; i++) {
			for (int j = i; j < n; j++) {
				if (matrix[i][j] != 0) {
					r++;
					break; // go to the next row if pivot is found
				} else {
					while (matrix[i][j] == 0 && j < (n - 1)) {
						j++; // skip to next column if finding 0's in the row
					}
					if (matrix[i][j] != 0) {
						r++;
						break;
					}
				}
			}
		}

		System.out.println("Rank: " + r + "\n");

		int solSet = 1; // default, but all cases below should be accounted for below
		int diff = m - r; // rows - rank
		
		if (r == m && r == n) // 1 solution
			solSet = 1;
		else if (r == m && r < n) // infinite solutions
			solSet = 2;
		else if (r < m && r == n) {
			solSet = 1; // 1 solution
			for (int i = m - 1; i > (m - diff - 1); i--) { // check from bottom up
				if (b[i] != 0) // change to 0 solution if any zeroe'd out rows have non-zero b elements
					solSet = 0;
			}
		} else {
			solSet = 2; // infinite solutions
			for (int i = m - 1; i > (m - diff - 1); i--){
				if (b[i] != 0) // change to 0 solution
					solSet = 0;
			}
		}

		switch (solSet) {
			case 0:
				print("There is no solution.");
				break;
			case 1:
				print("There is one unique solution.\n");
				double[] x = new double[m - diff];
				for (int i = (m - 1 - diff); i >= 0; i--) {
					double sum = 0;
					for (int j = i + 1; j < m - diff; j++) {
						sum += matrix[i][j] * x[j];
					}
					x[i] = (b[i] - sum) / matrix[i][i];
				}
				for (int i = 0; i < x.length; i++) {
					print(x[i]);
				}
				break;
			case 2:
				print("There are infinitely many solutions.\n");
				print("Particular solution xp:\n");
				int i = 0;
				int j = 0;
				int[] findPiv = new int[n];
				while (j < n && i < m) {
					if (matrix[i][j] != 0) {
						findPiv[j] = 1; // found pivot
						i++;
						j++;
					} else {
						while (matrix[i][j] == 0 && j < n) {
							findPiv[j] = 0; // found free
							j++;
						}
					}
				}
				int k = 0;
				for (int s = 0; s < findPiv.length; s++) {
					if (findPiv[s] != 0) {
						print(matrix[k][n]);
						k++;
					} else {
						print("0.00");
					}
				}
				print("\nSpecial solutions: ");
				for (int t = 0; t < findPiv.length; t++) {
					int NA = 0;
					if (findPiv[t] == 0) {
						print("");
						for (int y = 0; y < n; y++) {
							if (findPiv[y] == 0 && y == t) {
								print("1.00");
							} else if (findPiv[y] == 1) {
								print(0 - matrix[NA][t]);
								NA++;
							} else {
								print("0.00");
							}
						}
					}
				}
				break;
		}
		return true;

	}

	/* Print out the 2D array matrices with blanks between row entries to the
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

	// Overloaded print methods to print to console and output file
	public static void print(String s) {
		System.out.println(s);
		output.println(s);
	}

	public static void print(double d) {
		System.out.printf("%.2f\n", d);
		output.printf("%.2f\n", d);
	}

	public static File outFile;
	public static PrintWriter output;

	public static void main(String[] args) throws IOException {

		if (args.length == 2) {
			// Assign command-line args to file names
			File inFile = new File(args[0]);
			outFile = new File(args[1]);

			if (inFile.exists()) {
				// Introduction
				System.out
						.println("This program gives a complete solution for a system of of linear equations (A x = b) as described by any m x n matrix. It has three types of output: no solution, unique solution, and infinitely many solutions.");

				Scanner input = new Scanner(inFile);
				output = new PrintWriter(outFile);
				System.out.println("Reading in input values from \"" + args[0]
						+ "\"...\n");

				// Parses input file for m, n coefficient matrix, rhs
				// vector b values (to solve the equation A x = b)
				int m, n;
				double[][] A, b;
				m = input.nextInt();
				n = input.nextInt();
				A = new double[m][n];
				b = new double[m][1];
				for (int i = 0; i < m; i++) { // rows
					for (int j = 0; j < n; j++) { // cols
						A[i][j] = input.nextDouble();
					}
				}
				for (int i = 0; i < m; i++) {
					b[i][0] = input.nextDouble();
				}
				input.close();

				// Prints out matrix from the input file
				System.out.println("m: " + m);
				System.out.println("n: " + n + "\n");
				System.out.println("Matrix A:\n" + toString(A, 1));
				System.out.println("Vector b:\n" + toString(b, 1));

				double[][] aug = new double[m][n + 1];
				aug = augment(A, b, m, n);
				System.out.println("Augmented form:\n" + toString(aug, 1));

				double[][] rref = new double[m][n + 1];
				rref = toRREF(aug, m, n);
				System.out.println("Reduced row echelon form:\n"
						+ toString(rref, 1));

				boolean found = findSol(rref, m, n);

				if (found)
					System.out
							.println("\nSuccessfully wrote solution set to \""
									+ args[1] + "\"");

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
