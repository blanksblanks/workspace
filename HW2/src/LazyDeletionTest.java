// Tester method for Lazy Deletion class
public class LazyDeletionTest {
	
	public static void main(String[] args) throws UnderflowException {
		LazyDeletion<Integer> t = new LazyDeletion<Integer>();

		final int NUMS = 10;
		final int GAP = 7;

		System.out.println("Testing Lazy Deletion implementation of Binary Search Tree:");

		System.out.print("Inserted ");
		for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {
			t.insert(i);
			System.out.print(i +" ");
		}

		System.out.println("\nPrinting tree...");
		t.printTree();
		System.out.println("Checking size: tree has " + t.size() + " elements");

		
		System.out.print("Removed ");
		for (int i = 1; i < NUMS; i += 2) {
			t.remove(i);
			System.out.print(i + "  ");
		}

		System.out.println("\nPrinting tree...");
		t.printTree();
		System.out.println("Checking size: tree has " + t.size() + " elements");
			
		System.out.println("Find min: " + t.findMin());
		System.out.println("Find max: " + t.findMax());
		
		if (t.findMin() != 2 || t.findMax() != NUMS - 2)
			System.out.println("FindMin or FindMax error!");

		System.out.print("NO error: does contain ");
		for (int i = 2; i < NUMS; i += 2) {
			if (!t.contains(i))
				System.out.println("Find error1! Should contain but does not contain " + i);
			else
				System.out.print(i + "  ");
		}
		
		System.out.print("\nNO error: doesn't contain ");
		for (int i = 1; i < NUMS; i += 2) {
			if (t.contains(i))
				System.out.println("Find error2! Should not but does contain " + i);
			else
				System.out.print(i + "  ");
		}
		
		System.out.println("\nRe-inserted " + 1);
		t.insert(1);
		System.out.println("Removed " + 2);
		t.remove(2);
		System.out.println("Removed " + 6);
		t.remove(6);
		System.out.println("Printing tree... ");
		t.printTree();
		System.out.println("Checking size: tree has " + t.size() + " elements");

		System.out.println("Does this contain 1? " + t.contains(1));
		System.out.println("Does this contain 2? " + t.contains(2));
		System.out.println("Find min: " + t.findMin());
		System.out.println("Find max: " + t.findMax());

		System.out.println("Is tree empty? " + t.isEmpty());
		
		System.out.println("Removed " + 1);
		t.remove(1);
		System.out.println("Removed " + 8);
		t.remove(8);
		System.out.println("Removed " + 4);
		t.remove(4);
		
		System.out.println("Let's try to print tree...");
		t.printTree();
		System.out.println("Checking size: tree has " + t.size() + " elements");

		System.out.println("Is tree empty? " + t.isEmpty());
		
		System.out.println("That's it, folks. All systems appear to be up and running.");
	}
}