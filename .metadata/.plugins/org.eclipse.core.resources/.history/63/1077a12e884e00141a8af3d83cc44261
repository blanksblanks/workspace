Name: Nina Baculinao
Uni: nb2406

CS3151 Homework 1 Readme

***

BRIEF DESCRIPTION

This program calculates A^(2^n), where A is a n x n square matrix.

MATRIX MULTIPLIER

HOW TO RUN

  1. Compile the files using the following commands in Terminal
  
    $ javac MatrixMultiplier.java
    
  2. Run the file with two command-line args (note: call the .txt files whatever you like)
  
    $ java MatrixMultiplier input.txt output.txt
    
 CLASSES & METHODS
 
    MatrixMultiplier
    
        main: reads in user-specified input and writes out output file
        a) scans first two lines of input file in order to assign n and k int values
        b) creates double[n][n] 2D array to represent n x n matrix A
        c) scans next n lines for double values and adds each to correct A[row][column]
        d) the most important part: computes A^(2^k)
              by multiplying matrix to itself and reassigning A to its squared value k times,
              for example if k = 3, the loop runs three times:
                 (1) A = A*A, (2) A = A*A (is A^2A^2), (3) A = A*A (is A^4A^4 = A^8)
        e) writes final result of A^(2^k) to output file
         * Note: if you don't provide command-line arguments, the program multiplies two
           hard-coded matrices to test the multiplyMatrices method, first a 4 x 4 matrix
           by itself, then a 4 x 4 matrix by a 4 x 5 matrix, and finally a 4 x 5 matrix
           by a 4 x 4 matrix (the last will result in a statement that this is impossible).
         * Note: if you do provide command-line arguments, but the files don't exist, the
           program will self-exit

        multiplyMatrices: General method that multiplies matrices such that D = BC
        as described in the homework description
         * Note: not specific to square matrix A multiplying itself so method can be
           used in other classes - since this homework is short, included all in 1 class
        a) first checks that columns in B are equal to rows in C, if so:
        b) create new double[][] D matrix with B rows and C number of columns
        c) go through rows in B, columns in C, then columns in B in a nested loop
        d) calculate dot product for each element. Definition:
                 d_ij = n_∑_(k=1) (b_ik c_kj)
                 therefore D[i][j] += B[i][k] * C[k][j]; // adds to itself before moving to next entry
        e) returns D matrix
        f) if B columns don't match C rows, multiplication is impossible and program will say so
           before exiting
                 
        toString: prints out the 2D array matrices in a readable format to the console,
        with rows separated by new lines and row entries separated by blank tabs
        
CONCERNS

    * When k is very large, the entries can quickly become large too and the columns become mis-aligned
    because of the huge double number values of the elements in the array - should shorten these but
    keep the scientific notation
    * Used to have more steps in multiplyMatrices but this seems the most efficient, same with the loop
    that runs A^2 ^k times.
    * Readin for the individual entries in the row and column was tricky, but got that to work;
    kept the hard-coded 2D arrays for reference