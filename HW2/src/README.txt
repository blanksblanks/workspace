Name: Nina Baculinao
Uni: nb2406

Homework 2 Readme

NOTE: All java files tested in vagrant environment without compilation issues
Fun story: In vagrant, I hastily entered the command “rm *.java” instead of “rm *.class” after testing, but thankfully I had backups of all the java files in my previous homework 2 submissions

***

PROBLEM 1: A palindrome detector that uses MyStack

HOW TO RUN
  1. Compile the files using the following commands in Terminal
    $ javac MyStack.java
    $ javac UnderflowException.java
    $ javac PalindromeDetector.java
    $ javac PalindromeTest.java
  2. Run the test file
    $ java PalindromeTest

NOTES
 - MyStack class was used in both this problem and the second problem of the homework. It contains only the necessary methods to detect palindromes and build expression trees.
 - These methods include the constructor method MyStack(), the accessor methods peek(), size() and isEmpty(), the mutator methods push() and pop(), and the mutator-accessor method peekAndPop()
 - pop() and peekAndPop() return underflow exception if the stack is empty, while isEmpty() returns true if the list is empty
 - UnderflowException is a short class written by Weiss with that returns a message by me

***

PROBLEM 2: ExpressionTrees which accepts a postfix expression input by the user,
and converts it to prefix and infix expressions, as well as evaluating the expression

HOW TO RUN
  1. Compile the file in Terminal
    $ javac ExpressionTrees.java
  2. Run the file
    $ java ExpressionTrees

NOTES
 - Automatically handles illegal characters like spaces and letters in user input by deleting them from the input string, therefore the postfix expression will not add illegal characters to tree (so “1 2 +” is okay for my program)
 - Tells user if they have entered invalid expressions (all spaces or letters, and nothing can be added to the stack) 
 - Catches errors caused by popping when stack is empty (too many operators) or by elements still existing in tree after final pop (too many operands)
 - Further explanation of design choices in terms of error handling:
We had to account for two kinds of “bad situations” in this assignment. From the homework clarifications:
1. “if you pop from the stack and the stack happens to be empty. When this happens, your program should die gracefully and tell the user what went wrong.” This was caught by the underflow exception in MyStack class and caused by too many operators in the user input
2. “at the end of the algorithm when you pop from the stack one last time. If the stack still has elements on it, then you will also need to die gracefully and tell the user what went wrong.” This was generally caused by too many operands in the user input
Underflow exception worked in MyStack, such as in the case of too many operators and too few operands to pop. However, MyStack by itself doesn’t know when it is being popped for the final time, such as in the case where there are too many operands and the final pop left the tree not empty (as it should be). Therefore I decided to include all my error handling in the ExpressionTrees class itself at the get-go, during the buildTree() method. This way I could PREEMPTIVELY catch errors. If I didn’t do this, then the program would respond to a user expression too many operands and too few operators by printing out incorrect infix and prefix expressions before going all the way to the end of the evaluate() algorithm and handling the error of the tree stack size =/= 0 when you popped “one last time.” I found my early error handling the cleanest and best way to account for these two bad situations.

***

PROBLEM 3: Binary Search with lazy deletion implemented

HOW TO RUN
  1. Compile the file using the following commands in Terminal
    $ javac LazyDeletion.java
    $ javac LazyDeletionTest.java
  2. Run the test file
    $ java LazyDeletionTest

NOTES
- Added size variable and method so isEmpty() method runs on constant time
- Changed almost every method (indicated in the comments) to incorporate lazy deletion

***

EXTRAS

  *. Optional: Can compile the file using the following commands in Terminal
    $ javac Ex3_1.java
    $ javac TwoStack.java

NOTES
- These are the working java implementations of questions 1 and 3 in the written section


