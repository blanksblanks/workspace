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
		for (int i = 0; i < 3; i++) {
			increment();
		}
		System.out.println(getCount());
		increment();
		increment();
		System.out.println(lastCount());
		System.out.println(sinceLastCount());
	}
	
	public static int counter = 0;
	public static int lastValue = 0;
	
	public static void increment() {
		counter += 1;
	}
	
	public static int getCount() {
		lastValue = counter;
		return counter;
	}
  
	public static int lastCount() {
		return lastValue;
	}
	
	public static int sinceLastCount() {
		return counter - lastValue;
	}

}
