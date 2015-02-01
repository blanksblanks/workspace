import java.util.Random;


/**
 * 
 * Programming (70 pts). Use inverse iteration to compute the smallest
 * eigenvalue of the matrix A =
 * ( 2 −1 
 * −1 2 −1 . . .
 * . . . . . . .
 * . . . −1 2 −1
 * . . . .  −1 2 ) 
 * assuming that A is a 10×10 matrix. Construct a random vector of
 * unit length to use as the initial approximation of the eigenvector in inverse
 * iteration.
 * Hint: The matrix A is symmetric positive definite. Inverse iteration
 * is the power method applied to A−1 . If µ is the largest eigenvalue
 * of A−1 the smallest eigenvalue of A is λ = µ −1 . We have analytic
 * expressions for all the eigenvalues of this matrix. The eigenvalues of A are
 * given by λj = 2[1 − cos(πj/(n + 1))], j = 1, . . . , n, for an n × n matrix
 * A. Compare your approximation to thie value of λ1 for n = 10 to verify that
 * your program works well.
 * 
 * 
 * @author nb2406
 * 
 */

public class InverseIteration {
	
	public static double inverseIterate(double[][] matrix, int m, int n){
		
		double sigma = 0.0001; // choose suitably small value
		double[][] y = new double[m][1];
		double[][] x = new double[m][1];
		
		Random r = new Random();
		for (int i = 0; i < m; i++)
			x[i][0] = r.nextInt(100);
		
		double x_norm = 0.0;
		for (int i = 0; i < m; i++)
			x_norm += x[i][0] * x[i][0];
		x_norm = Math.sqrt(x_norm);
		
		for (int i = 0; i < m; i++)
			x[i][0] /= x_norm;
		
		double lambda = 0.0;
		double prev_lambda = 0.0;
		int counter = 0;

		/* Pseudocode:
		 *  repeat
		 * y_i+1 = inverse(A - shiftI) * x_i
		 * x_i+1 = y_i+1/ ||y_i+1||2
		 * lambda_i+1 = transposed(x_i+1)A(x_i+1)
		 * i = i + 1 
		 * until convergence
		 */
		for (;;){
			// y = invert(matrix - I * sigma) * x
			y = multiply(invert(add(matrix, (multiply(findIdentity(n), -sigma)))), x);
			double norm = 0.0;
			for (int k = 0; k < y.length; k++)
				norm += y[k][0] * y[k][0];
			norm = Math.sqrt(norm);
			x = multiply(y, 1/norm);
			prev_lambda = lambda;
			lambda = multiply(multiply(transpose(x), matrix), x)[0][0];
			counter++;
			if (Math.abs(lambda - prev_lambda) < 0.0000000000001) break;
			// to get closer to the exact eigen value, make check stricter and requisite difference smaller
		}		
		System.out.println("Number of iterations : " + counter);
		return lambda;
	}
	
	
	public static double[][] transpose(double[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		double[][] transpose = new double[col][row];
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				transpose[j][i] = matrix[i][j];
		return transpose;
	}
	
	public static double[][] invert(double[][] matrix){
		
		int n = matrix.length;
		double[][] id = findIdentity(matrix);
		double[][] aug = augment(matrix, id, n);
		double[][] U = forwardEliminate(aug, n);
		double[][] A_prime = new double[n][n];
		double[][] I_prime = new double[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				A_prime[i][j] = U[i][j];
				I_prime[i][j] = U[i][j+n];
			}
		}
		
		U = forwardEliminate(augment(rotate(A_prime), rotate(I_prime), n), n);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				A_prime[i][j] = U[i][j];
				I_prime[i][j] = U[i][j+n];
			}
		}
		
		double[][] inverse_aug = (augment(rotate(A_prime), rotate(I_prime), n));
		
		for (int i = 0; i < n; i++) {
			Double pivot = inverse_aug[i][i];
			for (int j = 0; j < (n+n); j++)
				inverse_aug[i][j] /= pivot;
		}
		
		double[][] inverse = new double[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n ; j++)
				inverse[i][j] = inverse_aug[i][n+j];
		
		return inverse;
	}
	
	public static double[][] rotate(double[][] matrix){
		int n = matrix.length;
		double[][] rotated = new double[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				rotated[i][j] = matrix[n-i-1][n-j-1];
		return rotated;
	}
	
	public static double[][] findIdentity(double[][] matrix){
		int rows = matrix.length;
		double[][] id = findIdentity(rows);
		return id;
	}
	
	public static double[][] findIdentity(int n){
		double[][] id = new double[n][n]; // for nxn matrix
		for (int i = 0; i < n; i++)
			id[i][i] = 1;
		return id;
	}
	
	// Takes matrix A and another matrix and returns an augmented matrix
	public static double[][] augment(double[][] matrix, double[][] other, int n) {
		int columns = other[0].length;
		double[][] augmented = new double[n][n + columns];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				augmented[i][j] = matrix[i][j];

		if (columns > 1) // it is another matrix
			for (int i = 0; i < n; i++)
				for (int j = 0; j < columns; j++)
					augmented[i][j+n] = other[i][j];
		else // it is vector b
			for (int i = 0; i < n; i++)
				augmented[i][n] = other[i][0];
		return augmented;
	}
		
	/*
	 * Forward elimination reduces the system to upper-triangular form.
	 */
	public static double[][] forwardEliminate(double[][] A, int n) {
		int columns = A[0].length;
		for (int k = 0; k < (n - 1); k++) {
			for (int i = k + 1; i < n; i++) {
				double multiplier = A[i][k] / A[k][k]; // # by which A[k][j]
				// is multiplied before being subtracted from A[i][j]
				for (int j = k; j < n; j++) {
					A[i][j] = A[i][j] - (multiplier * A[k][j]); // zeroes out
					// non-zero entries in A below the diagonal - moving from
					// top left to bottom right
				}
				if (columns == n + 1)
					A[i][n] = A[i][n] - (multiplier * A[k][n]); // updates bi
				else
					for(int j = 0; j < n; j++) // updates I
						A[i][n+j] = A[i][n+j] - (multiplier * A[k][n+j]);
			}
		}
		return A;
	}
	
	public static double[][] add(double[][] first, double[][] second){
		int m = first.length;
		int n = first[0].length;
		if (m != second.length || n != second[0].length) {
			System.err.println("Number of columns of first matrix doesn't equal number of rows of second matrix");
			System.exit(0);
		}
		double[][] sum = new double[m][n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				sum[i][j] = first[i][j] + second[i][j];
		return sum;
		
	}
	
	public static double[][] multiply(double[][] first, double[][] second) {
		int m = first.length;
		int col = first[0].length;
		int row = second.length;
		int n = second[0].length;
		if (col != row) {
			System.err.println("Number of columns of first matrix doesn't equal number of rows of second matrix");
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
	
	public static double[][] multiply(double[][] matrix, double scalar){
		double[][] product = matrix;
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				product[i][j] *= scalar;
		return product;
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
	
	public static void main(String[] args){
        double[][] A = {
                {2,-1,0,0,0,0,0,0,0,0},
                {-1,2,-1,0,0,0,0,0,0,0},
                {0,-1,2,-1,0,0,0,0,0,0},
                {0,0,-1,2,-1,0,0,0,0,0},
                {0,0,0,-1,2,-1,0,0,0,0},
                {0,0,0,0,-1,2,-1,0,0,0},
                {0,0,0,0,0,-1,2,-1,0,0},
                {0,0,0,0,0,0,-1,2,-1,0},
                {0,0,0,0,0,0,0,-1,2,-1},
                {0,0,0,0,0,0,0,0,-1,2},
              };
        int m, n;
        m = n = 10;
        
        double smallestEigenval = inverseIterate(A, m, n);
        System.out.println("SMALLEST EIGENVALUE: " + smallestEigenval);
        
        print("\nFor Matrix A:\n" + toString(A, 1));

        System.out.println("Compared to the eigenvalues of A*:");
        // 2[1 − cos(πj/(n + 1))]
        double eigenVal = 0.0;
        for (int j = 1; j < 11; j++) {
        	eigenVal = 2 * (1- Math.cos(Math.PI*j/(n+1)));
        	System.out.println("    " + eigenVal);
        }
        
        System.out.println("\n*given by λj = 2[1 − cos(πj/(n + 1))], j = 1, . . . , n\n");

       
//        double[][] B = {
//                {4,3},
//                {3,2}
//        };
//        
////        Expected output: {-2, 3}, {3, -4}
////      
//       print("Smallest Eigenvalue: " + inverseIterate(B, 2, 2));
//       print("\nFor Matrix B:\n" + toString(B, 1));
//       print("Inverse:\n" + toString(invert(B), 1));
       
	}

}
