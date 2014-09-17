/* Name: Nina B
 * UNI: nb2406
 * CS 1004, Summer 2014 
 * Programming Assignment 1c 
 */
  
/* Instructions 
 * Fill out the main below to do the following: 
 * Assign to variables the following information, using the 
 * best type appropriate 
 *   1) your name 
 *   2) my name (Matthew Maycock) 
 *   3) what you want people to think your age is 
 *   4) your home institution (Columbia, Duke, Cambridge, etc) 
 *   5) the name of this institution (Columbia University) 
 *   6) the name of this course 
 *   7) the room this course is taught in 
 * Output everything in meaningful english 
 *   "Matthew Maycock is ___ years old .... at .... to ... ... ..." 
 * You can only use one System.out.println, so use concatenation 
 * You don't need to use a scanner at all for this problem. 
 */

public class Assignment1c {
	
	public static void main(String[] args) {
	
		String name = "Nina";
		String prof = "Matthew Maycock";
		int age = 24;
		String homeInst = "Columbia College";
		String thisInst = "Columbia University";
		String course = "CS 1004 Intro to Java";
		String room = "717 Hamilton";
		
		System.out.println(
				"Hello, my name is " + name + " and at a hearty age of " +
				age + " I find myself sitting in on " + course +
				" taught by " + prof + " in " + room + " at " + thisInst +
				", which is also my alma mater as I graduated from " +
				homeInst + " some years past.");
	
	}

}
