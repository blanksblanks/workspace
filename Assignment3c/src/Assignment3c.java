/* Name: 
 * UNI: 
 * CS 1004, Summer 2014 
 * Programming Assignment 3c 
 */
  
/* Instructions 
 * In this assignment you'll be making a simple counter using 
 * static variables. The counter should initially be 0 and, 
 * there should be the following functionality: 
 *   1) function: increment 
 *      increase the value of the counter by exactly one. 
 *      it should not return any value 
 *   2) function: getCount 
 *      returns the value of the counter 
 *   3) function: lastCount 
 *      returns the value of the counter the last time that 
 *      getCount was called or 0 if it has yet to be called 
 *   4) function: sinceLastCount 
 *      returns the number of times the counter has been 
 *      incremented since getCount was last called or since 
 *      the program was started, whichever is smaller 
 */

public class Assignment3c {
	
	public static void main (String[] args) {
		increment(counter);
		System.out.println(getCount(counter));
		System.out.println(lastCount(counter));
		System.out.println(sinceLastCount(counter));
	}
	
	public static int counter = 0;
	public static int lastCountValue = 0;
	
	public static void increment(int counter) {
		counter += 1;
	}
	
	public static int getCount(int counter) {
		lastCountValue = counter;
		return counter;
	}
  
	public static int lastCount(int counter) {
		return lastCountValue;
	}
	
	public static int sinceLastCount(int counter) {
		return counter - lastCountValue;
	}

}
