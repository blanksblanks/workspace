import java.util.LinkedList;

public class MyStack<T> {

	// Chose a linked list to minimize cost of insertions and deletions
	// Used generic T type instead of Object to avoid casting
	// Note: Without <T> to 'MyStack' class heading returns error
	LinkedList<T> myList = new LinkedList<T>();
	int size = 0;

	public MyStack() {
	}

	// Tests if stack is logically empty, returns true if so
	public boolean isEmpty() {
		return (myList == null || size == 0);
	}

	// Inserts new item into the top of the stack at index 0
	// isFull method is not necessary for Linked Lists
	public void push(T x) {
		myList.add(0, x);
		size++;
	}
	
	// Returns most recently inserted item from the stack
	public T peek() throws UnderflowException {
		if (isEmpty()) {
			throw new UnderflowException();
		} else {
			T element = myList.get(0);
			return element;
		}
	}
	
	// Removes most recently inserted item from the stack
	public void pop() throws UnderflowException{
		if (isEmpty()) {
			throw new UnderflowException();
		} else {
			myList.remove(0);
			size--;
		}		
	}

	// Removes and returns most recently inserted item from the stack
	// Returns underflow exception if stack is empty
	public T peekAndPop() throws UnderflowException {
		if (isEmpty()) {
			throw new UnderflowException();
		} else {
			T element = myList.get(0);
			myList.remove(0);
			size--;
			return element;
		}
	}
	
	public int size() {
		return size;
	}
}
