// Modifies AvlTree class by Weiss
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x, line ) --> Insert x + line number
// ^ modified so if tree already contains x, line number still added as new
// entry to the linked list I added to the private AVL word node definition
// void remove( x ) --> Remove x (unimplemented)
// boolean contains( x ) --> Return true if x is present
// boolean remove( x ) --> Return true if x was present
// Comparable findMin( ) --> Return smallest item
// Comparable findMax( ) --> Return largest item
// boolean isEmpty( ) --> Return true if empty; else false
// void makeEmpty( ) --> Remove all items
// void printTree( ) --> Print tree in sorted order a-z
// ^ modified so tree prints line numbers the word node appears too
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.LinkedList;
import java.util.Iterator;

public class AvlWordTree<AnyType extends Comparable<? super AnyType>> {

	public AvlWordTree() {
		root = null;
	}

	public void insert(AnyType x, int line) {
		root = insert(x, root, line);
	}

	public void remove(AnyType x) {
		root = remove(x, root);
	}

	private AvlWordNode<AnyType> remove(AnyType x, AvlWordNode<AnyType> t) {
		if (t == null)
			return t; // Item not found; do nothing

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			t.left = remove(x, t.left);
		else if (compareResult > 0)
			t.right = remove(x, t.right);
		else if (t.left != null && t.right != null) // Two children
		{
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else
			t = (t.left != null) ? t.left : t.right;
		return balance(t);
	}

	public AnyType findMin() {
		if (isEmpty())
			throw new UnderflowException();
		return findMin(root).element;
	}

	public AnyType findMax() {
		if (isEmpty())
			throw new UnderflowException();
		return findMax(root).element;
	}

	public boolean contains(AnyType x) {
		return contains(x, root);
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	private static final int ALLOWED_IMBALANCE = 1;

	private AvlWordNode<AnyType> balance(AvlWordNode<AnyType> t) {
		if (t == null)
			return t;

		if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
			if (height(t.left.left) >= height(t.left.right))
				t = rotateWithLeftChild(t);
			else
				t = doubleWithLeftChild(t);
		else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE)
			if (height(t.right.right) >= height(t.right.left))
				t = rotateWithRightChild(t);
			else
				t = doubleWithRightChild(t);

		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}

	public void checkBalance() {
		checkBalance(root);
	}

	private int checkBalance(AvlWordNode<AnyType> t) {
		if (t == null)
			return -1;

		if (t != null) {
			int hl = checkBalance(t.left);
			int hr = checkBalance(t.right);
			if (Math.abs(height(t.left) - height(t.right)) > 1
					|| height(t.left) != hl || height(t.right) != hr)
				System.out.println("OOPS!!");
		}

		return height(t);
	}

	// Added line number parameter
	private AvlWordNode<AnyType> insert(AnyType x, AvlWordNode<AnyType> t,
			int line) {
		if (t == null)
			return new AvlWordNode<>(x, null, null, line);

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			t.left = insert(x, t.left, line); // nb
		else if (compareResult > 0)
			t.right = insert(x, t.right, line); // nb
		else
			t.lines.add(line); // nb add line numbers to it if it exists already
		return balance(t);
	}

	private AvlWordNode<AnyType> findMin(AvlWordNode<AnyType> t) {
		if (t == null)
			return t;

		while (t.left != null)
			t = t.left;
		return t;
	}

	private AvlWordNode<AnyType> findMax(AvlWordNode<AnyType> t) {
		if (t == null)
			return t;

		while (t.right != null)
			t = t.right;
		return t;
	}

	private boolean contains(AnyType x, AvlWordNode<AnyType> t) {
		while (t != null) {
			int compareResult = x.compareTo(t.element);

			if (compareResult < 0)
				t = t.left;
			else if (compareResult > 0)
				t = t.right;
			else
				return true; // Match
		}

		return false; // No match
	}

	// Prints out line numbers using an iterator
	private void printTree( AvlWordNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.print (t.element + ": ");
    		Iterator<Integer> lines = t.lines.iterator();
    		while (lines.hasNext()){
    			System.out.print(lines.next());
    			if (lines.hasNext()) // if element has more line numbers to list
    				System.out.print(", ");
    			else // if moving on to another element
    				System.out.println();
    		}
            printTree( t.right );
        }
    }


	private int height(AvlWordNode<AnyType> t) {
		return t == null ? -1 : t.height;
	}

	private AvlWordNode<AnyType> rotateWithLeftChild(AvlWordNode<AnyType> k2) {
		AvlWordNode<AnyType> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	private AvlWordNode<AnyType> rotateWithRightChild(AvlWordNode<AnyType> k1) {
		AvlWordNode<AnyType> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height) + 1;
		return k2;
	}

	private AvlWordNode<AnyType> doubleWithLeftChild(AvlWordNode<AnyType> k3) {
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	private AvlWordNode<AnyType> doubleWithRightChild(AvlWordNode<AnyType> k1) {
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}

	private static class AvlWordNode<AnyType> {
		@SuppressWarnings("unused")
		// Base constructor
		AvlWordNode() {
			this(null, 0);
		}

		// Overloaded constructor with more arguments
		AvlWordNode(AnyType theElement, int line) {
			this(theElement, null, null, line);
		}

		// Designated constructor - the one that's doing the work
		AvlWordNode(AnyType theElement, AvlWordNode<AnyType> lt,
				AvlWordNode<AnyType> rt, int line) {
			// Initializing instance vars
			lines = new LinkedList<Integer>();
			lines.add(line);
			element = theElement;
			left = lt;
			right = rt;
			height = 0;
		}

		// Declaring instance vars
		AnyType element; // The data in the node
		AvlWordNode<AnyType> left; // Left child
		AvlWordNode<AnyType> right; // Right child
		int height; // Height
		LinkedList<Integer> lines; // Line number
	}

	private AvlWordNode<AnyType> root; // Tree root

}
