// Modifies Quadratic Probing Hash Table class by Weiss
// Makes a hash table of Strings instead of AnyType
// 

public class MyHashTable {

	// Constructors
	public MyHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public MyHashTable(int size) {
		allocateArray(size);
		doClear();
	}

	public boolean insert(String x) {
		// Insert x as active
		int currentPos = findPos(x);
		if (isActive(currentPos))
			return false;

		array[currentPos] = new HashEntry<>(x, true);
		theSize++;

		if (++occupied > array.length / 2)
			rehash();

		return true;
	}

	private void rehash() {
		HashEntry<String>[] oldArray = array;

		// Create a new double-sized, empty table
		allocateArray(2 * oldArray.length);
		occupied = 0;
		theSize = 0;

		// Copy table over
		for (HashEntry<String> entry : oldArray)
			if (entry != null && entry.isActive)
				insert(entry.element);
	}

	private int findPos(String x) {
		int offset = 1;
		int currentPos = myhash(x);

		while (array[currentPos] != null
				&& !array[currentPos].element.equals(x)) {
			currentPos += offset;
			offset += 2;
			if (currentPos >= array.length)
				currentPos -= array.length;
		}

		return currentPos;
	}

	public boolean remove(String x) {
		int currentPos = findPos(x);
		if (isActive(currentPos)) {
			array[currentPos].isActive = false;
			theSize--;
			return true;
		} else
			return false;
	}


	public int size() {
		return theSize;
	}

	public int capacity() {
		return array.length;
	}

	public boolean contains(String x) {
		int currentPos = findPos(x);
		return isActive(currentPos);
	}

	private boolean isActive(int currentPos) { // returns true only for those
												// two conditions
		return array[currentPos] != null && array[currentPos].isActive;
	}

	public void makeEmpty() {
		doClear();
	}

	private void doClear() // sets everything to null
	{
		occupied = 0;
		for (int i = 0; i < array.length; i++)
			array[i] = null;
	}

	// Hashes odd characters in String using polynomial function
	/// of 37 by Horner's rule and finding the mod of that value
	// and the table size
	private int myhash(String key) {
		int hashVal = 0;
		for (int i = 0; i < key.length(); i += 2)
			hashVal = 37 * hashVal + key.charAt(i);
		hashVal %= array.length;
		if (hashVal < 0)
			hashVal += array.length;
		return hashVal;
	}

	@SuppressWarnings("hiding")
	private static class HashEntry<String> {
		public String element; // the element
		public boolean isActive; // false if marked deleted

		@SuppressWarnings("unused")
		public HashEntry(String e) {
			this(e, true);
		}

		public HashEntry(String e, boolean i) {
			element = e;
			isActive = i;
		}
	}

	private static final int DEFAULT_TABLE_SIZE = 251;
	private HashEntry<String>[] array; // The array of elements
	private int occupied; // The number of occupied cells
	private int theSize; // Current size

	@SuppressWarnings("unchecked")
	private void allocateArray(int arraySize) {
		array = new HashEntry[nextPrime(arraySize)];
	}

	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;

		for (; !isPrime(n); n += 2)
			;

		return n;
	}

	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;

		if (n == 1 || n % 2 == 0)
			return false;

		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;

		return true;
	}

}
