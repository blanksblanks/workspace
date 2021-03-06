import java.util.Comparator;

public class Compare {

	// Compares rectangles by area and returns largest one
	class CompareByArea implements Comparator<Rectangle> {
		public int compare(Rectangle rect1, Rectangle rect2) {
			int length1 = rect1.getLength();
			int width1 = rect1.getWidth();
			int area1 = length1 * width1;

			int length2 = rect2.getLength();
			int width2 = rect2.getWidth();
			int area2 = length2 * width2;

			return area1 - area2;
		}
	}
	
	//Compares rectangles by perimeter and returns largest one
	class CompareByPerimeter implements Comparator<Rectangle> {
		public int compare(Rectangle rect1, Rectangle rect2) {
			int length1 = rect1.getLength();
			int width1 = rect1.getWidth();
			int perimeter1 = 2 * length1 + 2 * width1;

			int length2 = rect2.getLength();
			int width2 = rect2.getWidth();
			int perimeter2 = 2 * length2 + 2 * width2;

			return perimeter1 - perimeter2;
		}
	}	
}