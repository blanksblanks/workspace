import java.util.Arrays;
import java.util.ArrayList;
import java.util.ListIterator;

public class Ex3_1 {

	public static <T> void printLots(ArrayList<T> L,
			ArrayList<Integer> P) {

		ListIterator<T> elements = L.listIterator();
		ListIterator<Integer> positions = P.listIterator();

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

		ArrayList<String> L = new ArrayList<String>(Arrays.asList("America",
				"Bolivia", "China", "Denmark", "Egypt", "France", "Germany",
				"Holland", "Iceland"));
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
