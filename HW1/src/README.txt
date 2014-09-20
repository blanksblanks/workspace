Name: Nina Baculinao
Uni: nb2406

Homework 1 Readme

***

PROBLEM 1

HOW TO RUN
  1. Compile the files using the following commands in Terminal
    $ javac Problem1.java
    $ javac Rectangle.java
    $ javac Compare.java
  2. Run the file
    $ java Problem1
    
 CLASSES & METHODS
    class Problem1
        main: creates an array of random rectangles and finds max by area and perimeter
        findMax: generic findMax from Comparator class with function object
        printResults: prints results of CompareByArea and CompareByPerimeter
        printList
    class Rectangle
        Rectangle: constructor
        getLength: accessor that returns length
        getWidth: same as above, but for width
    class Compare
        class CompareByArea: compares rectangle objects and returns one with greatest area
        class CompareByPerimeter: same as above, but for perimeters

***

PROBLEM 2

HOW TO RUN
  1. Compile the files using the following commands in Terminal
    $ javac Problem2.java
    $ javac Sum.java
    $ javac TimeInterval.java
  2. Run the file
    $ java Problem2
    
CLASSES & METHODS
    class Problem2
        main: creates an array of N values and executes runningTimeFor every single value of N in every sum loop
        runningTimeFor: takes parameters sum number and some number N and gives running time for that loop
    class Sum: implements the six loops described in Weiss Exercise 2.7
        sumOne
        sumTwo
        sumThree
        sumFour
        sumFive
        sumSix
    class TimeInterval
        startTiming
        endTiming
        getElapsedTime

***

PROBLEM 3

HOW TO RUN
  1. Compile the file using the following commands in Terminal
    $ javac Problem3.java
  2. Run the file with the input file containing a pre-sorted array
    $ java Problem3 input.txt 
        
NOTES
    The file name can be whatever you decide to name it 
    The file name should be the first and only argument - it needs
    to follow Problem2 with a space
    Only handles integer values
    Make sure your file has no spaces (including enter spaces) or letters because no error handling implemented
    
CLASSES & METHODS
    class Problem3
        main: reads user-specified file into an array of integers and prompts user for search key
        recursiveBinarySearch: recursively searched for a user-defined search key in a sorted array of integers