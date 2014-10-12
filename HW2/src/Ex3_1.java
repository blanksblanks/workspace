/**
 * 
 * Exercise 3.1 clarification:
 * 
 * Please go to the Collection page on Java API (the book erroneously says
 * "Collections". What you need to do is answer this question in Java using only
 * the methods outlined in the Collection API.
 * 
 * http://docs.oracle.com/javase/7/docs/api/java/util/Collection.html
 * 
 * Do not use anything from sub-classes of Collection (e.g. no using get() in
 * ArrayList). Only use the methods laid out in Collection.
 * 
 * As was stated in class, we want you to use proper Java here, but it doesn't
 * have to compile (so you can omit some of the overhead if you want). At the
 * same time, it would probably be beneficial to code this and just test it
 * yourself.
 * 
 * Do NOT use the toArray() method. Blaer wants you to use the iterator()
 * factory method in the Collection interface. You can use the iterator's
 * methods because it is not a sub-class of Collection.
 * 
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;

public class Ex3_1 {

	public static <T> void printLots(ArrayList<T> L,
			ArrayList<Integer> P) {

		Iterator<T> elements = L.iterator();
		Iterator<Integer> positions = P.iterator();

		int index = 0;
		int position = positions.next();
		// boolean indexIsValid = (index <= position && index <= positionList.size());
		// this doesn't work because the size has nothing to with the magnitude of position integers

		while (index <= position && elements.hasNext()) {
			T element = elements.next(); // System.out.println("index: " + index);
			if (index == position) {
				// print element if its index matches a position index
				System.out.println(element); // System.out.println(" matches position: " + position);
				if (positions.hasNext()) {
					position = positions.next(); // System.out.println("new position: " + position);
				} else {
					// break loop to prevent index unnecessary incrementing
					break; // System.out.println("Break");
				}
			}
			index++;
		}

	}

	public static void main(String[] args) {

		ArrayList<String> L = new ArrayList<String>(Arrays.asList("Apples",
				"Bananas", "Cantaloupes", "Dates", "Elderberries", "Figs", "Grapes",
				"Honeydew melons", "Mangos"));
		ArrayList<Integer> P = new ArrayList<Integer>(Arrays.asList(0, 2, 4, 6,
				20));

		System.out.println("L: " + L);
		System.out.println("P: " + P);
		printLots(L, P); // should print A, C, E, G, and nothing from index 20

		System.out.println("");
		P.remove(new Integer(4));
		P.remove(new Integer(6));
		P.remove(new Integer(20));
		System.out.println("New P: " + P);
		printLots(L, P); // should print A, C
	}

}
