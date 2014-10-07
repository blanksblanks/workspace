
import java.util.EmptyStackException;

public class doubleStack {

	// default stack of size 16
	String[] stackArray = (String[]) new String[16];
	private int leftTop = -1;
	private int rightTop = stackArray.length;

	public doubleStack() {
	}

	// specified stack size
	public doubleStack(int arraySize) {
		this.stackArray = (String[]) new String[arraySize];
		this.leftTop = -1;
		this.rightTop = stackArray.length;
	}

	// prints left stack (for debugging)
	public String printLeft() {
		if (leftTop == -1)
			throw new EmptyStackException();
		else {
			StringBuilder leftPrint = new StringBuilder();
			for (int i = 0; i < leftTop + 1; i++)
				leftPrint.append(stackArray[i]);
			return leftPrint.toString();
		}
	}

	// prints right stack (for debugging)
	public String printRight() {
		if (rightTop == stackArray.length)
			throw new EmptyStackException();
		else {
			StringBuilder rightPrint = new StringBuilder();
			for (int i = stackArray.length - 1; i > rightTop - 1; i--)
				rightPrint.append(stackArray[i]);
			return rightPrint.toString();
		}
	}

	// push onto left side of array
	public void leftPush(String newItem) {
		if (leftTop >= rightTop - 1)
			throw new IndexOutOfBoundsException("Both stacks are full");
		else {
			stackArray[leftTop + 1] = newItem;
			leftTop++;
		}
	}

	// pop from left side of array
	public String leftPop() {
		if (leftTop == -1)
			throw new EmptyStackException();
		else {
			String popItem = stackArray[leftTop];
			leftTop--;
			return popItem;
		}
	}

	// push onto right side of array
	public void rightPush(String newItem) {
		if (leftTop >= rightTop - 1)
			throw new IndexOutOfBoundsException("Both stacks are full");
		else {
			stackArray[rightTop - 1] = newItem;
			rightTop--;
		}
	}

	// pop from right side of array
	public String rightPop() {
		if (rightTop == stackArray.length)
			throw new EmptyStackException();
		else {
			String popItem = stackArray[rightTop];
			rightTop++;
			return popItem;
		}
	}

}
