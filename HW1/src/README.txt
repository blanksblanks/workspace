Name: Nina Baculinao
Uni: nb2406
Homework 1 Readme

=========================================================
HOW TO RUN THE BIGRAM FREQUENCY TEST
=========================================================

  1. Compile the files using the following commands in Terminal:
	$ javac Test.java
	$ javac bigramlist.java
	$ javac SortedList.java
	$ javac Bigrams.java
	$ javac MostCommon.java
  2. Create the text file you want to read in Terminal, e.g.
	$ pico input.txt
	* You can use or edit the “input.txt” file provided.
	* If you want to create your own text file, you can call it whatever you want and 	work with pico or any text editor of your choice.
  3. Run the main method:
	$ java Test
	* When the program asks you for a file to read, give it the name of the text file 	if it is in the same paragraph or the program, or give the path to the text file 	you want to run through the Bigram Frequency Test.
  4. See the output files in Terminal:
	$ cat output4.txt [final output of 10 most common bigrams]
	$ cat output.txt [all the bigrams in the file, unsorted]
	$ cat output2.txt [all the bigrams in the file, sorted]
	$ cat output3.txt [all the bigrams in the file, with number occurrences]
	* Don’t type what’s in the square brackets
	* If you are using Windows, replace “cat” with “type”

=========================================================
PROLOGUE: CHALLENGES WRITING THE PROGRAM
=========================================================

The most challenging part of coding this program initially was how to keep track of the number of occurrences of the bigrams. There are many ways to do this, including using arrays (either one-­ or two­-dimensional) of ints, using arrays of custom-­built classes, using arrays of Strings, or a combination. I was restricted to using arrays, and restricted from using the Java "Map" or "List" classes (including ArrayList and others).

The second ­most challenging part was how to determine the most frequent bigrams. Did I need to sort the entire list? If so, how? If not, how else could I find the ten most common? I opted for comparing the unsorted list of programs to a master list of all possible bigrams in alphabetical order, by creating an additional “bigramlist.java” class that was not called in the main. 

The third most challenging part was considering what classes/methods I should create, in order to separate and reuse functionality. I knew at minimum my program must contain two classes: one to store the data related to the number of bigram occurrences, as well as the methods for updating the data and reading it (I ended using many more classes for this); and one to implement the logic of the program. I was fascinated by the idea of taking an input file and transforming the information into different forms of output, so I might haven gotten carried away and created too many classes containing only a sole constructor method and too many different output files to show the effects of each method on the file. If I find time, I may go back to do this a little differently, first by keeping the “main” only a few lines long and as small as possible, and second by putting more constructors into the other classes to keep the number of classes lower. I think three classes should be sufficient, rather than the five I have here. Nonetheless, I'll break down how I split the functionality into multiple classes in order to find the top ten occurring bigrams.

=========================================================
HOW I WROTE THE PROGRAM: HOW IT WORKS
=========================================================

Test Class:

The main class asked the user to input a text file. It then accessed the text file and broke each word into character arrays, using the first and second element of the arrays to form bigrams. It put this list of unsorted bigrams into a new file called "output.txt." After that it implemented the constructor methods from other classes to create different output files. It also caught exceptions if the user typed the wrong input.

bigramlist Class:

This class created an output text file called “bigramlist.txt” that contained a master list of all all bigram from “aa” to “zz”. 

SortedList Class:

This class ordered the bigrams output file into alphabetical order by accessing accessing class “bigramlist.java” to create a master list of bigrams, then comparing the bigrams in “output.txt” to the master list. It then put this alphabetized list into "output2.txt”.

Bigrams Class:
This list counted the number of occurrences of each bigram by comparing each line with the next line,  and then created a list of the number of occurrences next to each bigram in "output3.txt”. that looks almost like a 2d array/table.

MostCommon Class:
Using a sorting algorithm and two arrays, this class found the ten most common bigrams and put them in "output4.txt” with a row of number of occurrences and a row of the bigrams associated with each number value. It discards the bigrams that are not in the top ten, and reads from the top of the alphabetized list to the bottom so in the case of ties, bigrams further back in the list are more likely to make it to "the top ten."

=========================================================
SHORT EXAMPLE OF INPUT AND OUTPUT FILES
=========================================================

==>haha.txt
hahahaha that is funny

=>output.txt
ha
ah
ha
ah
ha
ah
ha
th
ha
at
is
fu
un
nn
ny

=>output2.txt
ah
ah
ah
at
fu
ha
ha
ha
ha
ha
is
nn
ny
th
un

=>output3.txt
3 ah
1 at
1 fu
5 ha
1 is
1 nn
1 ny
1 th

=>output4.txt
Occurrences: 	5	3	1	1	1	1	1	1	0	0	
Bigrams: 	ha	ah	th	ny	nn	is	fu	at	null  null	


Homework 1

divides: If the remainder when the second int is divided by the first int is 0, then we know the first int can cleanly divide the second.

abs: If the argument, x is a negative number, we multiply it by -1 to turn it positive. Otherwise we return the argument as is.

max: If the first int is bigger than the second int, we turn the first int. Otherwise, we return the second int.

maxMagnitude: Using abs(), we pass the absolute values of two ints (x and y) into the function max(). This will give us the higher absolute value, which we store in the variable “result”. Since we want to return the original argument (and not the absolute value), we check if “result” is equivalent to the absolute value of x. If so, we return x (the original argument). Otherwise, “result” must be the absolute value of y, so we return y.

factor: We initialize an int variable called “ans” which will be returned at the end of the function. If the argument x == 0, we assign ans the value 0. If x == -1 or 1, we assign ans the value 1. For all other integers, we use a for loop that begins with i set to 2 (since we don’t want any factors smaller than 2), and increments by 1 until it reaches the absolute value of x. It’s important to use i <= abs(x) and not i < abs(x) in case x is a prime number and can only be divided by itself. Each time we go through the loop, we check if the absolute value of x can be divided by i without producing a remainder. If it can, that means i is a factor of x, and we assign ans the value of i before breaking the loop so that we only obtain the value of the first factor of x. Finally, we return ans.

***

ASSIGNMENT 3B

We declare a static variable called counter that can be accessed by any function within the class. Its initial value is 0. increment() increases the value of counter by 1, and getCount() returns the current value of counter.

***

ASSIGNMENT 3C

We declare two static variables called counter and lastCountValue, both of which are assigned the value 0.

increment: Increases the value of counter by 1.

getCount: Before returning the value of counter, we store its current value in the variable lastCountValue.

lastCount: The last time we called getCount(), we also took the extra step of storing the count value at that point in a variable called lastCountValue. This means we can now return lastCountValue to see what the last count was, regardless of the current count.

sinceLastCount: We subtract the last count value from the current count in order to calculate how many times we have incremented the count since we last called getCount().

***

ASSIGNMENT 3D

odds: We initialize a variable “sum” with the value 0. Using a for loop that begins at i = 0, ends at the end of the array and skips every other number (increments by 2), we add the value of the number stored in index i to the value of sum. Finally, we return the final value of sum when the loop is complete.

evens: Same as above, except we begin the for loop with i = 1. Since i increments by 2, we now print every second, fourth, sixth…etc number in the array.

product: We initialize a variable “result” with the value 1. If we were to use 0, anything we multiplied with this variable would produce 0 as well, whereas multiplying something by 1 does not affect its value. We use a for loop starting with i = 0, going through the whole array and incrementing by 1, multiplying “result” with the value stored at i every time. Finally, we return result.

parity: We initialize a variable called countOdd, which will act as a counter for odd numbers in the array passed as an argument. We also initialize a boolean variable called isOdd, which we set to false. Using a for loop which starts at i = 0, works all the way through the array and increments by 1, we check if each number in the array can be divided evenly by 2. If it cannot, it is an odd number, and we increment the value of countOdd. Once the loop finishes, countOdd contains the final number of odd numbers in the array. Now we check if there is an even or odd number of odd numbers in the array. An odd number of odd numbers will always add up to another odd number. The number of even numbers can be neglected because the sum of even numbers will always be even. Thus, if the value of countOdd is odd (cannot be divided cleanly by 2) we set the value of isOdd to true. Finally, we return isOdd.