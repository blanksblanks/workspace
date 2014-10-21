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

	// private final String inFileName;
	// private final String outFileName;
	// private int m;
	// private int n;
	// private double[][] A;
	// private double[][] b;

	// Takes matrix A and vector b and returns an augmented matrix
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
	 * Forward elimination reduces the system to upper-triangular form.
	 */
	public static double[][] forwardEliminate(double[][] A, int m, int n) {
		for (int k = 0; k < n; k++) {
			for (int i = k + 1; i < m; i++) {
				double multiplier;
				if (A[k][k] != 0)
					multiplier = A[i][k] / A[k][k]; // # by which A[k][j]
				// is multiplied before being subtracted from A[i][j]
				else
					multiplier = 0;
				for (int j = k; j < n; j++) {
					A[i][j] = A[i][j] - (multiplier * A[k][j]); // zeroes out
					// non-zero entries in A below the diagonal - moving from
					// top left to bottom right
				}
				if (A[i][n] != 0) // updates b entries
					A[i][n] = A[i][n] - (multiplier * A[k][n]);
			}
		}
		return A;
	}

	//
	// public static void swapRows(double[][] matrix, int r1, int r2){
	// double temp;
	// for(int i = 0; i < matrix[0].length; i++){
	// temp = matrix[r1][i];
	// matrix[r1][i] = matrix[r2][i];
	// matrix[r2][i] = temp;
	// }
	// }

	public static double[][] eliminate(double[][] matrix, int m, int n) {
		double pivot = 0;
		int x = 0;

		for (int i = 0; i < Math.min(m, n) && x < n; i++) {
			x = i;
			boolean found = false;
			if (matrix[i][x] != 0) {
				pivot = matrix[i][x];
			} else {
				for (int j = (i + 1); j < m; j++) {
					if (matrix[j][i] != 0) { // swap rows
					// swapRows(matrix, i, j);
						double temp;
						for (int k = 0; k < n; k++) {
							temp = matrix[i][k]; // store r1
							matrix[i][k] = matrix[j][k]; // replace r1 with r2
							matrix[j][k] = temp; // replace r2 with r2
						}
						found = true;
						break;
					}
				}
				if (!found) {
					while (matrix[i][x] == 0 && x < (n - 1)) {
						x++;
					}
					if (x < n) {
						pivot = matrix[i][x];
					}
				} else {
					pivot = matrix[i][x];
				}
			}
			// At this point we've found the pivot
			// Elimination
			if (x < n && pivot != 0) {
				for (int j = (i + 1); j < m; j++) {
					double multiplier = -1 * matrix[j][x] / pivot;
					for (int k = x; k <= n; k++) {
						matrix[j][k] += multiplier * matrix[i][k];
					}
				}
			}
		}
		return matrix;
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

	public static void findNumberOfSols(double[][] inputMatrix, int m, int n) {
		boolean noSol = false;
		double[] b = new double[m];
		int numOfPivots = 0;

		for (int i = 0; i < m; i++) {
			b[i] = inputMatrix[i][n];
		}

		for (int i = 0; i < m; i++) {
			for (int j = i; j < n; j++) {
				if (inputMatrix[i][j] != 0) {
					numOfPivots++;
					break;
				} else {
					while (inputMatrix[i][j] == 0 && j < (n - 1)) {
						j++;
					}
					if (inputMatrix[i][j] != 0) {
						numOfPivots++;
						break;
					}
				}
			}
		}

		if (inputMatrix.length == inputMatrix[0].length - 1 && numOfPivots == n) { //
			System.out.println("There is one solution:\n");
			double[] solVector = new double[m];
			for (int i = (m - 1); i >= 0; i--) {
				double sum = 0;
				for (int j = i + 1; j < m; j++) {
					sum += inputMatrix[i][j] * solVector[j];
				}
				solVector[i] = (b[i] - sum) / inputMatrix[i][i];
			}

			for (int i = 0; i < solVector.length; i++) {
				System.out.println(solVector[i]);
			}
		} else if (numOfPivots == n && numOfPivots < m) {
			int potentialZeroRows = m - numOfPivots;
			for (int i = b.length - 1; i > ((b.length - 1) - potentialZeroRows); i--) {
				if (b[i] != 0) {
					System.out.println("There is no solution\n");
					noSol = true;
					break;
				}
			}
			if (!noSol) {
				System.out.println("There is one solution: \n");
				double[] solVector = new double[m - potentialZeroRows];
				for (int i = (m - 1 - potentialZeroRows); i >= 0; i--) {
					double sum = 0;
					for (int j = i + 1; j < m - potentialZeroRows; j++) {
						sum += inputMatrix[i][j] * solVector[j];
					}
					solVector[i] = (b[i] - sum) / inputMatrix[i][i];
				}

				for (int i = 0; i < solVector.length; i++) {
					System.out.println(solVector[i]);
				}
			}
		}

		else if (numOfPivots == m && numOfPivots < n) {
			double divideBy = 0;
			System.out.println("There are infinite solutions.\n");
			System.out.println("The RREF is: \n");
			boolean contFlag = false;
			for (int i = 0; i < m; i++) {
				contFlag = false;
				for (int j = i; j < n; j++) {
					if (inputMatrix[i][j] != 0) {
						divideBy = inputMatrix[i][j];
						contFlag = true;
					}
					if (contFlag) {
						for (int k = i; k < (n + 1); k++) {
							if (divideBy != 0) {
								inputMatrix[i][k] = (inputMatrix[i][k] / divideBy);
							}
						}
						break;
					}
				}
			}

			boolean firstTime = true;
			for (int i = m - 1; i >= 0; i--) {
				firstTime = true;
				for (int j = 0; j < n; j++) {
					if (inputMatrix[i][j] != 0 && firstTime) {
						for (int k = (i - 1); k >= 0; k--) {
							double pivot = -inputMatrix[k][j];
							firstTime = false;
							for (int l = j; l < (n + 1); l++) {
								if (pivot != 0) {
									inputMatrix[k][l] += pivot
											* inputMatrix[i][l];
								}
							}
						}
					}
				}
			}

			System.out.println(toString(inputMatrix, 1));

			System.out.println("\nA particular solution is: \n");
			int i = 0;
			int j = 0;
			boolean[] fpArray = new boolean[n];
			while (j < n && i < m) {
				if (inputMatrix[i][j] != 0) {
					fpArray[j] = true; // true means pivot
					i++;
					j++;
				} else {
					while (inputMatrix[i][j] == 0 && j < n) {
						fpArray[j] = false; // false means free
						j++;
					}
				}
			}
			int counter = 0;

			for (int r = 0; r < fpArray.length; r++) {
				if (fpArray[r]) {
					System.out.printf("%.2f\n", inputMatrix[counter][n]);
					counter++;
				} else {
					System.out.println("0.00");
				}
			}
			System.out.println("\nThe special solution(s) is/are: ");
			for (int t = 0; t < fpArray.length; t++) {
				int counterNullspace = 0;
				if (fpArray[t] == false) {
					System.out.println();
					for (int y = 0; y < n; y++) {
						if (fpArray[y] == false && y == t) {
							System.out.println(1);
						} else if (fpArray[y] == true) {
							System.out.printf("%.2f\n",
									(-1 * inputMatrix[counterNullspace][t]));
							counterNullspace++;
						} else {
							System.out.println(0);
						}
					}
				}
			}

		} else {
			int potentialZeroRows = m - numOfPivots;
			for (int i = b.length - 1; i > ((b.length - 1) - potentialZeroRows); i--) {
				if (b[i] != 0) {
					System.out.println("There is no solution\n");
					noSol = true;
					break;
				}
			}
			if (!noSol) {
				double divideBy = 0;
				System.out.println("There are infinite solutions\n");
				System.out.println("The RREF is:\n");
				boolean contFlag = false;
				for (int i = 0; i < m; i++) {
					contFlag = false;
					for (int j = i; j < n; j++) {
						if (inputMatrix[i][j] != 0) {
							divideBy = inputMatrix[i][j];
							contFlag = true;
						}
						if (contFlag) {
							for (int k = i; k < (n + 1); k++) {
								if (divideBy != 0) {
									inputMatrix[i][k] = (inputMatrix[i][k] / divideBy);
								}
							}
							break;
						}
					}
				}
				boolean firstTime = true;
				for (int i = m - 1; i >= 0; i--) {
					firstTime = true;
					for (int j = 0; j < n; j++) {
						if (inputMatrix[i][j] != 0 && firstTime) {
							for (int k = (i - 1); k >= 0; k--) {
								double pivot = -inputMatrix[k][j];
								firstTime = false;
								for (int l = j; l < (n + 1); l++) {
									if (pivot != 0) {
										inputMatrix[k][l] += pivot
												* inputMatrix[i][l];
									}
								}
							}
						}
					}
				}

				System.out.println(toString(inputMatrix, 1));

				System.out.println("A particular solution is:\n");
				int i = 0;
				int j = 0;
				boolean[] fpArray = new boolean[n];
				while (j < n && i < m) {
					if (inputMatrix[i][j] != 0) {
						fpArray[j] = true; // true means pivot
						i++;
						j++;
					} else {
						while (inputMatrix[i][j] == 0 && j < n) {
							fpArray[j] = false; // false means free
							j++;
						}
					}
				}
				int counter = 0;

				for (int r = 0; r < fpArray.length; r++) {
					if (fpArray[r]) {
						System.out.printf("%.2f\n", inputMatrix[counter][n]);
						counter++;
					} else {
						System.out.println("0.00");
					}
				}

				System.out.println("\nThe special solutions are: ");
				for (int t = 0; t < fpArray.length; t++) {
					int counterNullspace = 0;
					if (fpArray[t] == false) {
						System.out.println();
						for (int y = 0; y < n; y++) {
							if (fpArray[y] == false && y == t) {
								System.out.println("1.00");
							} else if (fpArray[y] == true) {
								System.out.printf("%.2f\n", -1
										* inputMatrix[counterNullspace][t]);
								counterNullspace++;
							} else {
								System.out.println("0.00");
							}
						}
					}
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {

		if (args.length == 2) {

			// Introduction
			System.out
					.println("\nThis program gives a complete solution for a system of of linear equations (A x = b) as described b any m x n matrix.");

			// Assign command-line args to file names
			File inFile = new File(args[0]);
			// File outFile = new File(args[1]);

			if (inFile.exists()) {
				Scanner input = new Scanner(inFile);
				// PrintWriter output = new PrintWriter(outFile);
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
				System.out.println("Augmented matrix:\n" + toString(aug, 1));

				double[][] elim = new double[n][n + 1];
				elim = forwardEliminate(aug, m, n);
				System.out
						.println("Forward Elimination:\n" + toString(elim, 1));

				double[][] eliminated = new double[m][n + 1];
				eliminated = eliminate(aug, m, n);
				System.out.println("Elimination with pivots:\n"
						+ toString(eliminated, 1));

				findNumberOfSols(elim, m, n);
				//
				//
				// double[][] x = new double[n][1];
				// x = backSubstitute(elim, n);
				// System.out.println("Vector x:\n" + toString(x, 1));
				//
				// output.println(toString(x, 0));
				// System.out.println("Successfully wrote solution x to \""
				// + args[1] +
				// "\" (*Note values in the output file are not truncated)\n");
				// output.close();

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
