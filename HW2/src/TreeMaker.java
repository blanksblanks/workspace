public class TreeMaker {

	public TreeMaker() {
	}

	public ExpressionTree makeTree(String[] s) {
		
		Object element;
		ExpressionTree left;
		ExpressionTree right;
		
		// Traverse through all elements in the user input array and push them into stack
		// If an element is an operator, pop last two trees and assign them as operands
		// Use new operator and two operands, push new expression (sub)tree onto stack
		// If an element is a number (!operator), push onto stack
		for (int i = 0; i < s.length; i++) {
			element = s[i];
//			if (Arrays.binarySearch(operators, element) >= 0) { // if -1, not found
				if (element.equals("+") || element.equals("-") || element.equals("*") || element.equals("/")) {
				right = treeStack.peekAndPop(); // pop right out first
				left = treeStack.peekAndPop();
				treeStack.push(new ExpressionTree(element, left,
						right));
			} else {
				treeStack.push(new ExpressionTree(element, null, null));
			}
		}
		return treeStack.peekAndPop();
	}

	MyStack<ExpressionTree> treeStack = new MyStack<ExpressionTree>();
}
