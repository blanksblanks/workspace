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

	/*
	 * Transform augmented matrix into RREF: 
	 * a. Search down and right for 1st non-zero entry
	 * b. Swap rows if necessary so pivot is in first row 
	 * c. Subtract multiples of 1st row from other rows to get all 0's below pivot in the column
	 * d. Repeat steps on all elements below and to the right of current pivot
	 */
	public static double[][] toRREF(double[][] matrix, int m, int n) {
		int piv = 0;
		for (int r = 0; r < m; r++) { // go down rows
			if (piv >= n)
				break; // done - full rank
			{
				int k = r;
				while (matrix[k][piv] == 0) {
					k++; // search down column for nonzero element
					if (k == m) { // if we reach end of row
						k = r; // move on to next column
						piv++;
						if (piv == n)
							return matrix; // done
					}
				}
				double[] temp = matrix[r]; // swap rows i and r
				matrix[r] = matrix[k];
				matrix[k] = temp;
			}
			// turn pivots into 1's
			{
				double divisor = matrix[r][piv];
				for (int j = 0; j < n + 1; j++)
					// divide by pivot value including vector b
					matrix[r][j] /= divisor;
			}
			// eliminate other elements in the same column
			for (int i = 0; i < m; i++) {
				if (i != r) {
					double multiplier = matrix[i][piv];
					for (int j = 0; j < n + 1; j++) {
						matrix[i][j] -= multiplier * matrix[r][j];
						if (matrix[i][j] == -0.00)
							matrix[i][j] = 0.00;
					}
				}
			}
			piv++;
		}
		return matrix;
	}

	/*
	* Find rank by traversing down the diagonal, skipping columns if
	* necessary to count the number of pivot columns
	*/
	public static int findRank(double[][] matrix, int m, int n){
		int r = 0;
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
		return r;
	}
	
	/* Check from bottom up to right below the row containing the pivot, i.e.
	* the zeroed out rows; if any non-zero elements are found, there is no solution
	*/
	public static boolean findNonzeroes(double[][] matrix, int m, int n, int r){
		for (int i = m - 1; i > (r - 1); i--){
			if (matrix[i][n] != 0)
				return true;
		} return false;
	}

	/*
	 * Find possible solutions, which depend on rank r:
	 * r == m && r == n has 1 solution (t2 check)
	 * r == m && r < n has infinite solutions (t7 check)
	 * r < m && r == n has 0 or 1 solution (t3 check0, t6 check1)
	 * r < m && r < n has 0 or infinite solutions (t1 check, t4 check
	 * with 0 vector b, t5 check with 0 matrix and vector, t8 check0)
	 */
	public static boolean findSol(double[][] matrix, int m, int n) {
		int r = findRank(matrix, m, n);
		print("Rank: " + r + "\n");
		int solSet = 1; // initializing by default, but all cases below should be accounted for below
		boolean foundNonzero = findNonzeroes(matrix, m, n, r);

		if (foundNonzero)
			solSet = 0;
		else if (r == m && r == n) // 1 solution
			solSet = 1;
		else if (r == m && r < n) // infinite solutions
			solSet = 2;
		else if (r < m && r == n)
			solSet = 1; // 1 solution if no nonzeroes in the wrong place
		else // ( r < m && r < n)
			solSet = 2; // infinite solutions if no nonzeroes in the wrong place

		switch (solSet) {
			case 0:
				print("There is no solution.");
				break;
			case 1:
				print("There is one unique solution.\n");
				double[][] x = new double[n][1]; // r == n
				for (int i = (r - 1); i >= 0; i--) { // backsub from bottom
					for (int j = i + 1; j < r; j++) {
						matrix[i][n] -= matrix[i][j] * x[j][0]; // subtract known val from b
					}
					x[i][0] = matrix[i][n] / matrix[i][i]; // finds x by dividing new b by
				}
				print(toString(x, 1));
				break;
			case 2:
				print("There are infinitely many solutions.\n");
				print("Particular solution xp:");
				int i = 0;
				int j = 0;
				int[] pivotArray = new int[n];
				double[][] xp = new double[n][1];
				while (j < n && i < m) {
					if (matrix[i][j] != 0) {
						pivotArray[j] = 1; // found pivot, move to next row and col
						i++; j++;
					} else {
						while (matrix[i][j] == 0 && j < n) {
							pivotArray[j] = 0;
							j++; // found free, move to next col
						}
					}
				}
				// print b at pivot rows, 0 elsewhere in the vector to get xp
				int piv = 0;
				for (int row = 0; row < n; row++) {
					if (pivotArray[row] != 0) {
						xp[row][0] = matrix[piv][n];
						piv++;
					} else {
						xp[row][0] = 0;
					}
				}
				print(toString(xp, 1));

				// go to each free col and find special solutions
				int solnum = 0;
				for (int col = 0; col < n; col++) {
					int free = 0; // reinitialize vector that spans N(A)
					if (pivotArray[col] == 0) {
						solnum++;
						print("Special solution s" + solnum + ":");
						double[][] xs = new double[n][1];
						for (int rows = 0; rows < n; rows++) {
							if (pivotArray[rows] == 0 && rows == col) {
								xs[rows][0] = 1;
							} else if (pivotArray[rows] == 1) {
								xs[rows][0] = (0 - matrix[free][col]);
								free++;
							} else {
								xs[rows][0] = 0;
							}
						}
						print(toString(xs, 1));
					}
				}
				break;
		}
		return true;

	}

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
				// Introduction
				System.out
						.println("This program gives a complete solution for a system of of linear equations (A x = b) as described by any m x n matrix."
								+ "\nIt has three types of output: no solution, unique solution, and infinitely many solutions.");

				Scanner input = new Scanner(inFile);
				print("Reading in input values from \"" + args[0]
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
				print("m: " + m);
				print("n: " + n + "\n");
				print("Matrix A:\n" + toString(A, 1));
				print("Vector b:\n" + toString(b, 1));

				double[][] aug = new double[m][n + 1];
				aug = augment(A, b, m, n);
				print("Augmented form:\n" + toString(aug, 1));

				double[][] rref = new double[m][n + 1];
				rref = toRREF(aug, m, n);
				print("Reduced row echelon form:\n"
						+ toString(rref, 1));

				findSol(rref, m, n);

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
