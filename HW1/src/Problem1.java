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
		
		// Creates an array of 8 rectangles with random legnths and widths
		Random r = new Random();
		Rectangle[] list = new Rectangle[8];
		for (int i = 0; i < list.length; i++) {
			int l = r.nextInt(9) + 1;
			int w  = r.nextInt(9) + 1;
			list[i] = new Rectangle(l, w);
		}

		Rectangle greatestArea = findMax(list, new CompareArea().new CompareByArea());
		int lengthA = greatestArea.getLength();
		int widthA = greatestArea.getWidth();
		Rectangle greatestPerim = findMax(list,
				new ComparePerimeter().new CompareByPerimeter());
		int lengthP = greatestPerim.getLength();
		int widthP = greatestPerim.getWidth();
		System.out.println("The largest rectangle by area is "
				+ greatestArea + " with area " + (lengthA * widthA));
		System.out
				.println("The largest rectangle by width is " +
						greatestPerim + " with perimeter " + (2 * (lengthP + widthP)));

		System.out
				.println("\n The full list of randomly generated rectangles:\n");
		print(list);

	}

	// Prints out rectangle dimensions in the array
	public static void print(Rectangle[] list) {
		for (int i = 0; i < list.length; i++) {
			int length = list[i].getLength();
			int width = list[i].getWidth();
			System.out.println(list[i] + "[" + i + "] with length " + length
					+ ", width " + width + ", area " + (length * width)
					+ " and perimeter " + (2 * (length + width)));
		}
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
	
}
