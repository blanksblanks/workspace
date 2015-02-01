/**
 * 
 * Problem 1
 * 
 * @author nb2406
 * 
 *         Weiss, Exercise 1.15: Define a Rectangle class that provides
 *         getLength and getWidth methods. Using the findMax routines in Figure
 *         1.18, write a main that creates an array of Rectangle and finds the
 *         largest Rectangle first on the basis of area, and then on the basis
 *         of perimeter.
 * 
 */

import java.util.Comparator;
import java.util.Random;

public class Problem1 {

	public static void main(String[] args) {

		// Create an array of 8 rectangles with random dimensions from 1-10
		Random r = new Random();
		Rectangle[] list = new Rectangle[8];
		for (int i = 0; i < list.length; i++) {
			int l = r.nextInt(9) + 1;
			int w = r.nextInt(9) + 1;
			list[i] = new Rectangle(l, w);
		}
		
		Rectangle square = new Rectangle(2, 2);
		System.out.println("This is a square with length " + square.length + " and width" + square.width + "\n");
		square.length = 3;
		System.out.println("This is a square with length " + square.length + " and width" + square.width + "\n");

		
		// Find rectangle(s) with greatest area and perimeter
		Rectangle greatestArea = findMax(list,
				new Compare().new CompareByArea());
		Rectangle greatestPerim = findMax(list,
				new Compare().new CompareByPerimeter());
		
		// Print results to console
		printResults(greatestArea, greatestPerim);
		System.out
				.println("\n Here is the full list of randomly generated rectangles:\n");
		printList(list);

	}
	
	// Generic findMax, with a function object.
	// Precondition: arr.length > 0.
	public static <AnyType> AnyType findMax(AnyType[] arr,
			Comparator<? super AnyType> cmp) {
		int maxIndex = 0;

		for (int i = 0; i < arr.length; i++) {
			if (cmp.compare(arr[i], arr[maxIndex]) > 0) {
				maxIndex = i;
			}
		}
		return arr[maxIndex];
	}

	// Print out the results of greatest area and perimeter in readable format
	public static void printResults(Rectangle greatestArea, Rectangle greatestPerim) {
		int lengthA = greatestArea.getLength();
		int widthA = greatestArea.getWidth();
		int lengthP = greatestPerim.getLength();
		int widthP = greatestPerim.getWidth();
		// Printing Rectangle objects produce random symbols and characters
		System.out
				.println("Largest by area is the rectangle with length "
						+ lengthA
						+ ", width "
						+ widthA
						+ " and area "
						+ (lengthA * widthA));
		System.out.print("Largest by perimeter is the ");
		if (greatestArea.equals(greatestPerim)) {
			System.out.print("same ");
		}
		System.out.println("rectangle with length "
				+ lengthP
				+ ", width "
				+ widthP
				+ " and perimeter "
				+ (2 * (lengthP + widthP)));
	}
	
	// Print out list of rectangle dimensions in the array
	public static void printList(Rectangle[] list) {
		for (int i = 0; i < list.length; i++) {
			int length = list[i].getLength();
			int width = list[i].getWidth();
			System.out.println("Rectangle" + "[" + i + "] with length "
					+ length + ", width " + width + ", area "
					+ (length * width) + " and perimeter "
					+ (2 * (length + width)));
		}
	}
}
