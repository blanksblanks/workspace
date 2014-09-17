######                         #         #
#     #   ######   ##   ####   ##       ## ######
#     #   #       #  #  #   #  # #     # # #
######    #####  #    # #    # #  #   #  # #####
#     #   #      ###### #    # #   # #   # #
#      #  #      #    # #   #  #    #    # #
#      #  ###### #    # ####   #         # ######

=========================================================
INTRODUCTION
=========================================================

"Bigrams" are groups of two written letters (or sometimes two syllables, or two words), and are very commonly used as the basis for simple statistical analysis of text. They are also used in many of the most successful language models for speech recognition. The most common bigrams in written English are "th", "er", "on", "an", and "re".The Bigram Frequency Test program looks through a text file and reports the ten most common bigrams (two-­letter sequences) in the text, along with the number of occurrences. For our purposes, bigrams do no span across words and we can assume the text file is all lower case letters with no punctuation. The name of the text file will be provided by the user when the program runs, and the final output will be a text file with the ten most common bigrams and the number of occurrences, in sorted order.

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
