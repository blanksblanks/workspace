Name: Nina Baculinao
Uni: nb2406

CS3151 Homework 4 Readme

***

LEAST SQUARES

***

HOW TO RUN

  1. Compile the files using the following commands in Terminal
  
    $ javac LeastSquares.java
    
  2. Run the file with one command-line arg
  
    $ java LeastSquares input.txt
    
    Note: Input file can be called whatever you like. It should contain on each line:
    m
    C
    D
    E
    
    where m = the nunumber of data points
    and C, D, E = the coefficients of the parabola equation y = C + Dt + Et*t

    Example:
    20
    1.1
    2.2
    3.3

    
***

BRIEF DESCRIPTION

This program fits a parabola to the data (ti , bi), i = 1...m. 
To generate the data, it uses a parabola equation, samples it at m equally spaced points ti,
and uses a random number generator to perturb each yi to get bi = yi + ri for i = 1...m.
Finally, it computes the vector x_hat in the equation ATA x_hat = ATb.

Note: to further test this program, I also played with the value of m (the number of data
points in the input file - from 1 to 20 to 100) and also reduced the noise in the random
number generator (by hardcoding it so that ri were values between 0 and 0.0001.)

***

CLASSES & METHODS
 
    LeastSquares
    
        transpose: swaps the rows and columns to transpose a given matrix
        
        multiply: multiplies matrices and vectors together (takes product dimensions as input)
        
        augment: takes matrix A and vector b and returns in augmented form
        
        forwardEliminate: reduces the system to upper-triangular form
        
        backSubstitute: solves the simplified system of equations, in upper
        triangular form, to find the values of x[i] by moving from top to bottom
                        
        toString: prints out the 2D array matrices in a readable format to the console,
        with rows separated by new lines and row entries separated by blank tabs
        
        print: overloaded method that prints solutions to console and output file
    
        main: reads in user-specified input and writes out output file
        a) scans four lines of input file in order to assign m, C, D, E values (also assigns n to number of unknowns)
        b) creates double[m][n] to represent A, sampling at m equally spaced points ti
        c) creates double[m][1] to represent vector b, where bi = yi + ri (and ri is perturbation using Math.random())
        d) creates double[n][m] to represent AT using transpose method
        e) creates double[n][n] to represent ATA using multiply method
        f) creates double[n][1] to represent ATb using multiply method
        g) creates double[n][n+1] to represent augmented matrix ATA | ATb
        h) reduces augmented matrix to upper triangular form using forwardEliminate method
        i) solves for x_hat using backSubstitute method
        j) compares calculated coefficients from x_hat with original given C, D, E values
        * Note: if you do provide command-line arguments, but the files don't exist, the
          program will self-exit

***

TEST CASE:

$ cat input.txt
    20
    6
    -9
    3

$ java LeastSquares input.txt

Reading in input values from "input2.txt"...

m: 20 data points
C: 6.0  D: -9.0 E: 3.0
y = 6.0 + -9.0t + 3.0t*t

A where m row vectors each contain 1, ti and ti*ti:
    1.00    0.00    0.00
    1.00    1.00    1.00
    1.00    2.00    4.00
    1.00    3.00    9.00
    1.00    4.00   16.00
    1.00    5.00   25.00
    1.00    6.00   36.00
    1.00    7.00   49.00
    1.00    8.00   64.00
    1.00    9.00   81.00
    1.00   10.00  100.00
    1.00   11.00  121.00
    1.00   12.00  144.00
    1.00   13.00  169.00
    1.00   14.00  196.00
    1.00   15.00  225.00
    1.00   16.00  256.00
    1.00   17.00  289.00
    1.00   18.00  324.00
    1.00   19.00  361.00

b where bi = yi + ri:
    6.36
    0.43
    0.03
    6.31
   18.05
   36.68
   60.77
   90.06
  126.11
  168.21
  216.30
  270.19
  330.47
  396.63
  468.07
  546.65
  630.05
  720.53
  816.30
  918.79

Step by step, we can solve solve ATA x_hat = ATb

AT:
    1.00    1.00    1.00    1.00    1.00    1.00    1.00    1.00    1.00    1.00    1.00    1.00    1.00    1.00    1.00    1.00    1.00    1.00    1.00    1.00
    0.00    1.00    2.00    3.00    4.00    5.00    6.00    7.00    8.00    9.00   10.00   11.00   12.00   13.00   14.00   15.00   16.00   17.00   18.00   19.00
    0.00    1.00    4.00    9.00   16.00   25.00   36.00   49.00   64.00   81.00  100.00  121.00  144.00  169.00  196.00  225.00  256.00  289.00  324.00  361.00

ATA:
     20.00    190.00   2470.00
    190.00   2470.00  36100.00
   2470.00  36100.00 562666.00

ATb:
   5826.99
  87282.56
1378926.94

Augmented form:
     20.00    190.00   2470.00   5826.99
    190.00   2470.00  36100.00  87282.56
   2470.00  36100.00 562666.001378926.94

Upper triangle form:
     20.00    190.00   2470.00   5826.99
      0.00    665.00  12635.00  31926.17
      0.00      0.00  17556.00  52696.59

Vector x_hat contains our calculated coefficients:
    6.354065205714596
    -9.021657743635329
    3.001628401123686

We can compare these to the original C, D, E values:
    6.00
   -9.00
    3.00
