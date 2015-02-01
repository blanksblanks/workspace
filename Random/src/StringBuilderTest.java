import java.util.ArrayList;

public class StringBuilderTest {

	public static void main(String[] args) {
		StringBuilder str = new StringBuilder("Tutorial");
		System.out.println("string = " + str);

		// insert character value at offset 8
		str.insert(8, 's');

		// prints StringBuilder after insertion
		System.out.print("After insertion = ");
		System.out.println(str.toString());

		String word = "hello";
		char[] c = word.toCharArray();
		ArrayList<String> words = new ArrayList<String>();
		
		// Add one character
		for (int i = 0; i <= c.length; i++) {
			String addApos = word.substring(0, i) + "'"
					+ word.substring(i, c.length);
			words.add(addApos);
			System.out.println(addApos);
			for (char j = 'a'; j <= 'z'; j++) {
				String addChar = word.substring(0, i) + j
						+ word.substring(i, c.length);
				words.add(addChar);
				System.out.println(addChar);
			}
		}

		// Remove one character
		for (int i = 0; i <= c.length - 1; i++) {
			String suggestion = word.substring(0, i) + word.substring(i + 1);
			System.out.println(suggestion);
		}

		// Swap adjacent characters
		// Expected outcome ehllo, hlelo, hello, helol
		for (int i = 0; i < word.length() - 1; i++) {
			char[] ch = word.toCharArray();
			char temp = ch[i];
			ch[i] = ch[i + 1];
			ch[i + 1] = temp;
			String swappedString = new String(ch);
			System.out.println(swappedString);
		}

		// String word = "hello";
		// StringBuilder s = new StringBuilder(word);
		// for (int i = 0; i < word.length(); i++){
		// for
		// s.insert(i, ch);
		// }
		// }

	}
}
