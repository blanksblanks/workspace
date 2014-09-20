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
		Random r = new Random();

		Rectangle[] list = new Rectangle[8];
		for (int i = 0; i < list.length; i++) {
			int width = r.nextInt(9) + 1;
			int height = r.nextInt(9) + 1;
			list[i] = new Rectangle(width, height);
		}

		System.out.println("The largest rectangle by area is "
				+ findMax(list, new CompareArea().new CompareByArea()));
		System.out
				.println("The largest rectangle by width is "
						+ findMax(list,
								new ComparePerimeter().new CompareByPerimeter()));

		System.out
				.println("\n out of this list of randomly generated rectangles:\n");
		print(list);

	}

	public static void print(Rectangle[] list) {
		for (int i = 0; i < list.length; i++) {
			int width = list[i].getWidth();
			int length = list[i].getLength();
			System.out.println(list[i] + "[" + i + "] with width " + width
					+ ", length " + length + ", area " + (width * length)
					+ " and perimeter " + (2 * (width + length)));
		}
	}

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
