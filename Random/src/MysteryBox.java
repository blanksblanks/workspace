import java.util.Scanner;

public class MysteryBox {
	
	private static Scanner readin;

	public static void main(String[] args) {
		int total;
		int objOverhead = 16;
		int arrRef = 8;
		int arrPadding = 24;
		readin = new Scanner(System.in);		
		System.out.print("How many booleans + bytes do you have? ");
		int numBools = readin.nextInt();
		System.out.print("How many chars do you have? ");
		int numChars = readin.nextInt();
		System.out.print("How many ints + floats do you have? ");
		int numInts = readin.nextInt();
		System.out.print("How many long + doubles do you have? ");
		int numLongs = readin.nextInt();
		System.out.print("What type of array do you have? bool/byte: 1, char: 2, int/float: 4, long/double: 8 ");
		int arrType = readin.nextInt();
		System.out.print("How big is your array? ");
		int arrSize = readin.nextInt();
		
		// Note double arrays are arrType * sizeM * sizeN
		
		System.out.println("\nCalculating total size...");
		System.out.println();
		total = objOverhead + numBools + (numChars*2) + (numInts*4) + (numLongs*8) + (arrType*arrSize) + arrRef + arrPadding;
		System.out.println("objOverhead + numBools + numChars + numInts + numLongs + ( arrType * arrSize) + arrRef + arrPadding =");
		System.out.println(objOverhead + " + " + numBools + "*2 + " + numChars + "*4 + " + numInts + "*4 + " + numLongs + "*8 + (" + arrType * arrSize + ") + " + arrRef + " = " + total);
		if (total%8 != 0)
			total = (total/8 + 1) * 8;
		System.out.println("Total: " + total);
	}

}
