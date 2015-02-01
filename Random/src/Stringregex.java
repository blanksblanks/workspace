
public class Stringregex {

	public static void main(String[] args){
		String word = "!d?ksjks-j'! !!";
		if (word.matches(".*[a-zA-Z]+.*")) {
			// Remove punctuation before and after each word (but not
			// inside, or single quotes)
			// ^ used in two ways, ^[ means "starts with", [^ means "non"
			word = word.replaceFirst("^[^a-zA-Z']+", "")
					.replaceAll("[^a-zA-Z']+$", "").toLowerCase();
			System.out.println(word);
		}
	}
	
}
