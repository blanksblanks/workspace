import java.io.*;
import java.util.Scanner;

public class MatrixMultiplier {
	public static void main(String[] args) throws IOException {
		
		 // #1
        double[][] multiplicand = new double[][] {
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        };
        double[][] multiplier = new double[][] {
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
                };
        
        System.out.println("#1\n" + toString(multiplyMatrices(multiplicand, multiplier)));
       
		if (args.length > 0) {

			// Assign command-line args
			String inFileName = args[0];
			File inFile = new File(inFileName);
			String outFileName = args[1];
			File outFile = new File(outFileName);
			PrintWriter output = new PrintWriter(outFile);

			// Checks if file exists before creating Scanner
			if (inFile.exists()) {
				int n;
				double k;
				double[][] matrix;

				// Scanner counts elements in file to determine array size
				Scanner input = new Scanner(inFile);
				n = (int) getNextNum(input);
				System.out.println ("n is " + n);
				matrix = new double[n][n];
				k = getNextNum(input);
				System.out.println ("k is " + k);
				
				int row = 0;
				int column = 0;
				
				while (input.hasNextLine()) {
					float next = input.nextFloat();
					matrix[row][column] = next;
					if (column == (n - 1)) {
						row++;
						column = 0;
					} else {
						column++;
					}
				}
				
				int twosExponent = 1;
				System.out.println("Input:\n" + toString(matrix));

				while (twosExponent <= k) {
					matrix = multiplyMatrices(matrix, matrix);
					if (twosExponent < k) {
					System.out.println("#" + twosExponent + "\n" + toString(matrix));
					} else if (twosExponent == k) {
					System.out.println("Output:\n" + toString(matrix));
					output.println(toString(matrix));
					System.out.println("Successfully wrote final result to " + outFileName);
					}
					twosExponent++;
				}
				
/*				//figure out the dimensions of the matrix from LINE 1 (n)
				n = Integer.parseInt(input.nextLine());
				System.out.println("n = " + n); 
			
				//figure out the value of k from LINE 2
				k = Integer.parseInt(input.nextLine());
				System.out.println("k = " + k);
			
				//make the dimensions of the 2D array based on n
				matrix = new float[n][n];
				
				//rows and columns of the matrix
				int row = 0;
				int column = 0;
				
				//read the rest of the input file, placing the contents into the 2D matrix
				while (reader.hasNextLine()) { 
						float next = reader.nextFloat();
						matrix[row][column] = next;
						if (column == (n-1)) {
							row++;
							column = 0;
						}
						else {
							column++;
						}	
				}
				*/
				
//				for (int i = 0; i < n; i++) {
//					for (int j = 0; j < n; i++) {
//						arr[i][j] = getNextNum(input);
//						System.out.println("arr" + i + j + " is " + arr[i][j]);
//					}
//				}
//				
//		        System.out.println("#2\n" + toString(multiplyMatrices(multiplicand, multiplier)));

				input.close();
				output.close();
			}
		}

	}

	public static double getNextNum(Scanner input) {
			String s = input.next();
			double d = Double.parseDouble(s);
			return d;
	}
	
	// Matrix D = BC
    public static double[][] multiplyMatrices(double[][] B, double[][] C) {
        int bCols = B[0].length; // B columns length
        int cRows = C.length;    // C rows length
        if (bCols!= cRows) return null; // matrix multiplication is not possible
        int dRows = B.length;    // m result rows length
        int dCols = C[0].length; // m result columns length
        double[][] D = new double[dRows][dCols];
        for(int i = 0; i < dRows; i++) {         // rows from m1
            for(int j = 0; j < dCols; j++) {     // columns from c and d
                for(int k = 0; k < bCols; k++) { // columns from m1
                    D[i][j] += B[i][k] * C[k][j];
                }
            }
        }
        return D;
    }

    public static String toString(double[][] m) {
        String result = "";
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[i].length; j++) {
            	result += (m[i][j] + "  ");
            }
            result += "\n";
        }
        return result;
    }
    
}
