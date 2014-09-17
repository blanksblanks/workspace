/* Name: Nina B
 * UNI: 
 * CS 1004, Summer 2014 
 * Programming Assignment 2a 
 */
  
/* Instructions 
 * Make actions: 
 *   1) one called everyThird 
 *      it should accept one parameter of type String[] 
 *      it should print every third item of the parameter 
 *   2) one called everyFifth 
 *      it should accept one parameter of type String[] 
 *      it should print every fifth item of the parameter 
 *   3) one called everyThirdOrFifth 
 *      it should print every third and fifth item 
 *      so the 3rd, 5th, 6th, 9th, 10th, 12th, 15th, ... 
 * Make a main that uses the three actions in the order presented, 
 * but writes a message declaring when it is going to use them: 
 * 
 * how it should work: 
 *   javac Assignment2a.java 
 *   java Assignment2a 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 
 *   usingEveryThird 
 *   3 
 *   6 
 *   9 
 *   12 
 *   15 
 *   usingEveryFifth 
 *   5 
 *   10 
 *   15 
 *   usingEveryThirdOrFifth 
 *   3 
 *   5 
 *   6 
 *   9 
 *   10 
 *   12 
 *   15 
 */

public class Assignment2a {
	
	public static void main(String[] args) {
		System.out.println("Printing every third item: ");
		EveryThird(args);
		System.out.println("Printing every fifth item: ");
		EveryFifth(args);
		System.out.println("Printing every third or fifth item: ");
		EveryThirdOrFifth(args);
	}
	
	public static void EveryThird(String[] items) {
	    for (int i = 0; i < items.length; ++i) {
	      if ((i+1)%3 == 0) {
	    	  System.out.println(items[i]);
	      }
	    }
	}

	public static void EveryFifth(String[] items) {
		for (int i = 0; i < items.length; ++i) {
		   if ((i+1)%5 == 0) {
		    	  System.out.println(items[i]);
		   }
		}
	}
	
	public static void EveryThirdOrFifth(String[] items) {
		for (int i = 0; i < items.length; ++i) {
			if ((i+1)%3 == 0 || (i+1)%5 == 0) {
				System.out.println(items[i]);
			}
		}
	}

	    
}
	
