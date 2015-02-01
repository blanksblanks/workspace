import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class DeleteBehindTheColon {
	
	public static void main(String[] args) throws FileNotFoundException{
		File inFile = new File(args[0]);
		Scanner input = new Scanner(inFile);
		while (input.hasNextLine()){
			String entry = input.nextLine();
			entry = entry.replaceAll("\\s", "");
			StringTokenizer words = new StringTokenizer(entry, ":");
			if (words.nextToken() != null)
				System.out.println(words.nextToken());
		}
		input.close();
	}

}
