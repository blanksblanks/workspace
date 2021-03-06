Name: Nina Baculinao
Uni: nb2406

CS3151 Homework 2 Readme

***

MATRIX SOLVER

***

HOW TO RUN

  1. Compile the files using the following commands in Terminal
  
    $ javac MatrixSolver.java
    
  2. Run the file with two command-line args (note: call the .txt files whatever you like)
  
    $ java MatrixSolver input.txt output.txt
    
***

BRIEF DESCRIPTION

This program solves systems of linear equations Ax = b by using Gaussian elimination
and back substitution, where the coefficient matrix is m-diagonal and m is odd. 

***

CLASSES & METHODS
 
    MatrixSolver
    
        augment: takes matrix A and vector b and returns an augmented matrix
        
        forwardEliminate: reduces the system to upper-triangular form.
        - Takes advantage of the m-diagonal form by eliminating only the
        nonzero elements in the matrix (that is, the ones (m-1)/2 rows below
        and columns to the right of the diagonal)
        
        backwardSubstitute: solves the simplified system of equations, in upper
        triangular form, to find the values of x[i] by moving from top to bottom
        
        toString: prints out the 2D array matrices in a readable format to the console,
        with rows separated by new lines and row entries separated by blank tabs
    
        main: reads in user-specified input and writes out output file
        a) scans first two lines of input file in order to assign n and m int values
        b) creates double[n][n] 2D arrays to represent n x n matrix A and n x 1 vector b
        c) scans next n lines for double values and adds each to correct indices for A and b
        d) sets up an augmented matrix with A and b
        e) uses forward elimination to reduce matrix A to upper triangular form
        f) uses back substitution to produce solution set in vector x
        g) writes final solutions of x to output file
        * Note: if you do provide command-line arguments, but the files don't exist, the
          program will self-exit

***

TEST CASE 1
    
** Input File:
    
10
3
2   4   0   0   0   0   0   0   0   0
8   2   4   0   0   0   0   0   0   0
0   16  8   5   0   0   0   0   0   0
0   0   4   2   1   0   0   0   0   0
0   0   0   2   8   2   0   0   0   0
0   0   0   0   16  8   2   0   0   0
0   0   0   0   0   4   2   16  0   0
0   0   0   0   0   0   8   4   2   0
0   0   0   0   0   0   0   2   4   4
0   0   0   0   0   0   0   0   8   2
2
4
16
8
4
2
2
16
2
4

** Output File:

-10.192000000000016 
5.596000000000008   
18.586000000000027  
-44.44480000000007  
22.545600000000032  
-43.737600000000064 
-4.41440000000001   
11.611200000000018  
2.435200000000003   
-7.740800000000013  

** Terminal Output:
    
This program solves systems of linear equations (A x = b) using elimination and back substitution, where the coefficient matrix is m-diagonal and m is odd.

Reading in input values from "input.txt"...

n: 10
m: 3

Matrix A:
    2.00    4.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00
    8.00    2.00    4.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00
    0.00   16.00    8.00    5.00    0.00    0.00    0.00    0.00    0.00    0.00
    0.00    0.00    4.00    2.00    1.00    0.00    0.00    0.00    0.00    0.00
    0.00    0.00    0.00    2.00    8.00    2.00    0.00    0.00    0.00    0.00
    0.00    0.00    0.00    0.00   16.00    8.00    2.00    0.00    0.00    0.00
    0.00    0.00    0.00    0.00    0.00    4.00    2.00   16.00    0.00    0.00
    0.00    0.00    0.00    0.00    0.00    0.00    8.00    4.00    2.00    0.00
    0.00    0.00    0.00    0.00    0.00    0.00    0.00    2.00    4.00    4.00
    0.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    8.00    2.00

Vector b:
    2.00
    4.00
   16.00
    8.00
    4.00
    2.00
    2.00
   16.00
    2.00
    4.00

Augmented matrix form in which first 10 columns are A, last column is b:
    2.00    4.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    2.00
    8.00    2.00    4.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    4.00
    0.00   16.00    8.00    5.00    0.00    0.00    0.00    0.00    0.00    0.00   16.00
    0.00    0.00    4.00    2.00    1.00    0.00    0.00    0.00    0.00    0.00    8.00
    0.00    0.00    0.00    2.00    8.00    2.00    0.00    0.00    0.00    0.00    4.00
    0.00    0.00    0.00    0.00   16.00    8.00    2.00    0.00    0.00    0.00    2.00
    0.00    0.00    0.00    0.00    0.00    4.00    2.00   16.00    0.00    0.00    2.00
    0.00    0.00    0.00    0.00    0.00    0.00    8.00    4.00    2.00    0.00   16.00
    0.00    0.00    0.00    0.00    0.00    0.00    0.00    2.00    4.00    4.00    2.00
    0.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    8.00    2.00    4.00

Matrix A after elimination and ready for back substitution:
    2.00    4.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    2.00
    0.00  -14.00    4.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00   -4.00
    0.00    0.00   12.57    5.00    0.00    0.00    0.00    0.00    0.00    0.00   11.43
    0.00    0.00    0.00    0.41    1.00    0.00    0.00    0.00    0.00    0.00    4.36
    0.00    0.00    0.00    0.00    3.11    2.00    0.00    0.00    0.00    0.00  -17.33
    0.00    0.00    0.00    0.00    0.00   -2.29    2.00    0.00    0.00    0.00   91.14
    0.00    0.00    0.00    0.00    0.00    0.00    5.50   16.00    0.00    0.00  161.50
    0.00    0.00    0.00    0.00    0.00    0.00    0.00  -19.27    2.00    0.00 -218.91
    0.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    4.21    4.00  -20.72
    0.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00   -5.61   43.39

Vector x:
  -10.19
    5.60
   18.59
  -44.44
   22.55
  -43.74
   -4.41
   11.61
    2.44
   -7.74

Successfully wrote solution x to "output.txt" (*Note values in the output file are not truncated)

***

TEST CASE 2: a 5-diagonal matrix
    
** Input File:

10
5
2   4   5   0   0   0   0   0   0   0
8   2   4   6   0   0   0   0   0   0
4   16  8   5   7   0   0   0   0   0
0   2   4   2   1   3   0   0   0   0
0   0   3   2   8   2   2   0   0   0
0   0   0   1   16  8   2   3   0   0
0   0   0   0   3   4   2   16  4   0
0   0   0   0   0   4   8   4   2   5
0   0   0   0   0   0   5   2   4   4
0   0   0   0   0   0   0   6   8   2
2
4
16
8
4
2
2
16
2
4

** Output File:

3.644451885086742   
7.186088975200365   
-6.8066519341949885 
-2.050197549052451  
-6.978718274961519  
10.644514386481882  
31.530534163709127  
-10.835831156945511 
22.667581865666737  
-56.162833991830404 

** Terminal Output:

This program solves systems of linear equations (A x = b) using elimination and back substitution, where the coefficient matrix is m-diagonal and m is odd.

Reading in input values from "input2.txt"...

n: 10
m: 5

Matrix A:
    2.00    4.00    5.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00
    8.00    2.00    4.00    6.00    0.00    0.00    0.00    0.00    0.00    0.00
    4.00   16.00    8.00    5.00    7.00    0.00    0.00    0.00    0.00    0.00
    0.00    2.00    4.00    2.00    1.00    3.00    0.00    0.00    0.00    0.00
    0.00    0.00    3.00    2.00    8.00    2.00    2.00    0.00    0.00    0.00
    0.00    0.00    0.00    1.00   16.00    8.00    2.00    3.00    0.00    0.00
    0.00    0.00    0.00    0.00    3.00    4.00    2.00   16.00    4.00    0.00
    0.00    0.00    0.00    0.00    0.00    4.00    8.00    4.00    2.00    5.00
    0.00    0.00    0.00    0.00    0.00    0.00    5.00    2.00    4.00    4.00
    0.00    0.00    0.00    0.00    0.00    0.00    0.00    6.00    8.00    2.00

Vector b:
    2.00
    4.00
   16.00
    8.00
    4.00
    2.00
    2.00
   16.00
    2.00
    4.00

Augmented matrix form in which first 10 columns are A, last column is b:
    2.00    4.00    5.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    2.00
    8.00    2.00    4.00    6.00    0.00    0.00    0.00    0.00    0.00    0.00    4.00
    4.00   16.00    8.00    5.00    7.00    0.00    0.00    0.00    0.00    0.00   16.00
    0.00    2.00    4.00    2.00    1.00    3.00    0.00    0.00    0.00    0.00    8.00
    0.00    0.00    3.00    2.00    8.00    2.00    2.00    0.00    0.00    0.00    4.00
    0.00    0.00    0.00    1.00   16.00    8.00    2.00    3.00    0.00    0.00    2.00
    0.00    0.00    0.00    0.00    3.00    4.00    2.00   16.00    4.00    0.00    2.00
    0.00    0.00    0.00    0.00    0.00    4.00    8.00    4.00    2.00    5.00   16.00
    0.00    0.00    0.00    0.00    0.00    0.00    5.00    2.00    4.00    4.00    2.00
    0.00    0.00    0.00    0.00    0.00    0.00    0.00    6.00    8.00    2.00    4.00

Matrix A after elimination and ready for back substitution:
    2.00    4.00    5.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    2.00
    0.00  -14.00  -16.00    6.00    0.00    0.00    0.00    0.00    0.00    0.00   -4.00
    0.00    0.00  -11.14    8.43    7.00    0.00    0.00    0.00    0.00    0.00    9.71
    0.00    0.00    0.00    4.15    2.08    3.00    0.00    0.00    0.00    0.00    8.92
    0.00    0.00    0.00    0.00    7.75   -1.08    2.00    0.00    0.00    0.00   -2.56
    0.00    0.00    0.00    0.00    0.00    9.44   -2.00    3.00    0.00    0.00    4.96
    0.00    0.00    0.00    0.00    0.00    0.00    2.16   14.60    4.00    0.00    0.67
    0.00    0.00    0.00    0.00    0.00    0.00    0.00  -57.01  -14.37    5.00   11.17
    0.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    2.75    1.21   -5.76
    0.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00    0.00   -0.33   18.75

Vector x:
    3.64
    7.19
   -6.81
   -2.05
   -6.98
   10.64
   31.53
  -10.84
   22.67
  -56.16

Successfully wrote solution x to "output2.txt" (*Note values in the output file are not truncated)