
/* Name: 
 * UNI: 
 * CS 1004, Summer 2014 
 * Programming Assignment 2b 
 */
  
/* Instructions 
 * Make an action: 
 *   name it collatzSequence 
 *   it should accept one parameter, an int 
 *   if the parameter is negative, make it positive 
 *   if the parameter is 0, do nothing 
 *   output the values for the collatz sequence starting from that number: 
 *      if the current number is odd, triple it and add one 
 *      if the current number is even, halve it 
 *      print the number out before you change it 
 *      stop when you get to one 
 * You should make a main such that: 
 *   1) if there are no arguments given, you should warn the user that 
 *      an argument is required 
 *      example: 
 *      java Assignment2b 
 *      Argument required, please give an argument. 
 *   2) if there is an argument given, assume it is an int and change it 
 *      into an int (see code examples where this happened) and use the 
 *      action from above with that value 
 * 
 * Example: 
 *    javac Assignment2b.java 
 *    java Assignment2b 20 
 *    20 
 *    10 
 *    5 
 *    16 
 *    8 
 *    4 
 *    2 
 *    1 
 */
  
public class Assignment2b { 

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Argument required, please give an argument.");
		}		
		else if (args.length > 1 ){
			System.out.println("Too many arguments, only one integer required.");
		}
		else if (args.length == 1) {
		    try {
		    	int firstArg;
		    	firstArg = Integer.parseInt(args[0]);
		    	collatzSequence(firstArg);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[0] + " must be an integer.");
		        System.exit(1);
		    }
		}

	}
	
	public static void collatzSequence(int x) {
		
		if (x == 0 ) {
			}
		else {
			if (x<0) {
				x *= -1;
			}
		}
		
		while (x > 1) {
			if (x%2 == 0) {
				System.out.println(x);
				x /= 2;
			}
			else if (x%2 == 1) {
				System.out.println(x);
				x = (x*3) + 1;
			}
			
		}
		System.out.println(x);
}
	
	
}