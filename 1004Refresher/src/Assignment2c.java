/* Name: Nina B
 * UNI: nb2406
 * CS 1004, Summer 2014 
 * Programming Assignment 2c 
 */
  
/* Instructions 
 * make an action such that: 
 *   it is named "action2c" 
 *   it accepts an int parameter 
 *   1) if the parameter is 0, output "No Zeroes" 
 *   2) otherwise, if the parameter is negative, multiply it by -1 to 
 *      make it positive; if it is positive, leave it alone. 
 *      Now, do the following: 
 *      a) count how many items would be in the collatz sequence for 
 *         the given number (i.e. how many lines would Assignment2b 
 *         print if we used that number) 
 *      b) make an array of Strings the size of the count from part a 
 *      c) now fill the array with the string versions of the collatz 
 *         sequence for the given parameter; note to make an int into a 
 *         string you can do ("" + someInt) -- you might not need the ( 
 *         and ), but better safe than sorry. 
 *      d) Output the items in the array from the first to the last 
 * 
 * Make a main that, 
 *   1) If there are no args (how do you check that?) it will write 
 *      "Goodbye!" and do nothing else 
 *   2) Otherwise if there are args, turn the first one into an int 
 *      and use it as the parameter to action2c 
 * 
 * example: 
 *   javac Assignment2c.java 
 *   java Assignment2c 10 
 *   10 
 *   5 
 *   16 
 *   8 
 *   4 
 *   2 
 *   1 
 */
  
public class Assignment2c {
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Goodbye!");
		} // If there are no args, output bye and exit		
		else {
		    try {
		    	int firstArg;
		    	firstArg = Integer.parseInt(args[0]);
		    	action2c(firstArg);
		    } // Only takes first arg as parameter for action2c
		    catch (NumberFormatException e) {
		        System.err.println("Argument" + args[0] + " must be an integer.");
		        System.exit(1);
		    }
		} 
	}
	
	public static void action2c(int x) {
		// collatzSequence(x); for debugging purposes
		if (x == 0) {
			System.out.println("No Zeroes");
		} // If the parameter is 0, output "No Zeroes"
		else if (x < 0) {
			x *= -1;
		} // If the parameter is negative, make it positive
		
		int count = 1; // Initiates count variable
		int subx = x; // Initiates subx x variable to replace x - will need x value later
		
		while (subx > 1) {
			if (subx%2 == 0) {
				subx /= 2;
				count++;
			}
			else if (subx%2 == 1) {
				subx = (subx*3) + 1;
				count++;
			}
		} // Counts how many items would be in the collatzsequence before x = 1
		
		String[] items = new String[count]; // Initializes new String array
		items[0] = ("" + x); // Makes original x first element in array
		
		for (int i = 1; i < items.length; i++) {
				if (x%2 == 0) {
					x /= 2;
					items[i] = ("" + x);
				}
				else if (x%2 == 1) {
					x = (x*3) + 1;
					items[i] = ("" + x);
				}
		} // Fills array with int x values as Strings
		
		for (String e : items) // Syntax: (Type var : sequence)
		{
			System.out.println(e);
		} // Outputs items array element by element
	}
}