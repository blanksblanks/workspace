/**
 * 
 * Exercise 3_24
 * 
 * @author nb2406
 * 
 *         Instructions: Write routines to implement two stacks using only one
 *         array. Your stack routines should not declare an overflow unless
 *         every slot in the array is used.
 * 
 *         Clarifications: We are essentially looking for a TwoStack class with
 *         standard stack methods.
 * 
 *         Again, aim for Java, but not necessarily Java that will fully
 *         compile.
 * 
 *         You need to at least implement push, pop, peek, and isEmpty methods.
 * 
 * 
 */

// Implement two stacks with one array, a "top" stack and "bottom" stack
// Public Operations: void isEmpty(), void isFull(), Object peek(), void pop(), void push(x)
// Note: each stack has its own version of these methods
public class TwoStack {

	private Object[] stackArray;
	private int topOfStack;
	private int bottomOfStack;
	static final int DEFAULT_CAPACITY = 10;

	// Constructor
	public TwoStack() {
		this.stackArray = new Object[DEFAULT_CAPACITY];
	}

	// Constructor with capacity parameter
	public TwoStack(int capacity) {
		this.stackArray = new Object[capacity];
		this.topOfStack = -1;
		this.bottomOfStack = stackArray.length;
	}

	// "TOP" STACK METHODS
	
	// Check if stack is empty
	public boolean isEmptyFromTop() {
		return (topOfStack == -1);
	}
	
	// Check if stack is full
	public boolean isFullFromTop() {
		return (topOfStack == stackArray.length - 1);
	}
	
	// Get most recently inserted object (doesn't change stack)
	// If stack is empty, return null
	public Object peekTop() {
		if (isEmptyFromTop()) {
			return null;
		} else {
			return stackArray[topOfStack];
		}
	}

	// Remove most recently inserted object from stack
	// If stack is empty, throw underflow exception
	public void popTop() {
		if (isEmptyFromTop()) {
			throw new UnderflowException();
		} else {
			stackArray[topOfStack--] = null;
		}
	}
	
	// Insert a new object into stack
	// If stack is full, throw index out of bounds exception
	// Tested this - if we don't check isFull, it'll throw an
	// uncaught exception under the same conditions anyway
	public void pushTop(Object x) {
		if (isFullFromTop())
			throw new IndexOutOfBoundsException("Burp. The stack is full.");
		else {
			stackArray[topOfStack + 1] = x;
			topOfStack++;
		}
	}
	
	// "BOTTOM" STACK METHODS (equivalent to top stack, but from the bottom)
	
	public boolean isEmptyFromBottom() {
		return (bottomOfStack == stackArray.length);
	}
	
	public boolean isFullFromBottom() {
		return (bottomOfStack == 0);
	}
	
	public Object peekBottom() {
		if (isEmptyFromBottom()) {
			return null;
		} else {
			return stackArray[bottomOfStack];
		}
	}

	public void popBottom() {
		if (isEmptyFromBottom()) {
			throw new UnderflowException();
		} else {
			stackArray[bottomOfStack++] = null;
		}
	}
	
	public void pushBottom(Object x) {
		if (isFullFromBottom()) {
			throw new IndexOutOfBoundsException("Burp. The stack is full.");
		} else {
			stackArray[bottomOfStack - 1] = x;
			bottomOfStack--;
		}
	}

	// This isFull method applies if we wanted the objects pushed to the bottom
	// and top of stack to have a joint capacity equal to the size of the array
	// What's interesting is with an array of n, each stack has size n
	// and together they make 2n
	// public boolean isFull() {
	//	return (topOfStack >= bottomOfStack);
	// }
	
	public static void main (String[] args) {
		int n = 3;
		TwoStack myStack = new TwoStack(n);
		System.out.println("Just made a stack with capacity of " + n);
		System.out.println("Peek top: " + myStack.peekTop());
		myStack.pushTop("First push to top");
		System.out.println("Peek top: " + myStack.peekTop());
		myStack.pushTop("Second push to top");
		System.out.println("Peek top: " + myStack.peekTop());
		myStack.pushTop("Third push to top");
		System.out.println("Peek top: " + myStack.peekTop());
	    myStack.pushBottom("First push to bottom");
	    System.out.println("Peek top: " + myStack.peekBottom());
	    System.out.println("About to pop and peek the bottom. Ok, decided not to peek this time, because I tried and it caused an underflow exception - as expected.");
	    myStack.popBottom();
	    //	    System.out.println(myStack.peekBottom()); 
	    System.out.println("Peek top again: " + myStack.peekTop());
	    myStack.pushBottom("Second push to bottom after it's been popped");
	    System.out.println("Peek bottom: " + myStack.peekBottom());
	    myStack.pushBottom("Third push to bottom");
	    System.out.println("Peek bottom: " + myStack.peekBottom());
	    myStack.pushBottom("Fourth push to bottom");
	    System.out.println("Peek bottom: " + myStack.peekBottom());
	    System.out.println("Now the stack is full, 3 pushes to top, 4 pushes to bottom (-1 pop bottom) means it's reached capacity of 3 from top and from bottom. I predict another push either from top or bottom will make the stack explode.");
	    myStack.pushBottom("Fourth push to bottom");
	}
}
