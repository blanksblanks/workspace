import java.util.LinkedList;

public class ExpressionTree {

	public Object element;
	public ExpressionTree left;
	public ExpressionTree right;

	// Constructor	
	public ExpressionTree(Object elem, ExpressionTree leftTree,
			ExpressionTree rightTree) {
		element = elem;
		left = leftTree;
		right = rightTree;
	}
	
	// Convert post-fix from expression tree to prefix expression
	public String printPrefix() {
		LinkedList<Object> prefixList = new LinkedList<Object>();
		recursivePrefixOrder(prefixList);
		StringBuilder prefixExpression = new StringBuilder(prefixList.size());
		for (int i = 0; i < prefixList.size(); i++)
			// adds every LinkedList element to prefix
			prefixExpression.append(prefixList.get(i));
		return prefixExpression.toString();
	}

	// Recursively add elements in correct order for prefix LinkedList
	private LinkedList<Object> recursivePrefixOrder(LinkedList<Object> prefix) {
		prefix.add(element);
		if (left != null)
			left.recursivePrefixOrder(prefix);
		if (right != null)
			right.recursivePrefixOrder(prefix);
		return prefix;
	}

	// Convert post-fix from expression tree to infix expression that we're all
	// familiar with
	public String infixPrint() {
		LinkedList<Object> infixList = new LinkedList<Object>();
		recursiveInfixOrder(infixList);
		StringBuilder infixExpression = new StringBuilder(infixList.size());
		for (int i = 0; i < infixList.size(); i++)
			infixExpression.append(infixList.get(i));
		return infixExpression.toString();
	}

	// Recursively return elements in correct order for infix LinkedList
	private LinkedList<Object> recursiveInfixOrder(LinkedList<Object> infix) {
		if (element != null) {
			if (left == null || right == null)
				infix.add(element);
			else if (element.equals("+") || element.equals("-")) {
				infix.add("(");
				left.recursiveInfixOrder(infix);
				infix.add(element);
				right.recursiveInfixOrder(infix);
				infix.add(")");
			} else if (left != null && right != null) {
				left.recursiveInfixOrder(infix);
				infix.add(element);
				right.recursiveInfixOrder(infix);
			}
		}
		return infix;
	}

	// Solve post-fix expression
	public Double compute() {
		Double lhs;
		Double rhs;
		Double result;
		if (left != null && right != null) {
			lhs = left.compute();
			rhs = right.compute();
			if (element.equals("+")) {
				result = lhs.doubleValue() + rhs.doubleValue();
			} else if (element.equals("-")) {
				result = lhs.doubleValue() - rhs.doubleValue();
			} else if (element.equals("*")) {
				result = lhs.doubleValue() * rhs.doubleValue();
			} else {
				result = lhs.doubleValue() / rhs.doubleValue();
			} 
			return result;
		} else {
//			if (!treeStack.isEmpty()) {
//				System.out
//				.println("Still elements on the stack. This probably means you have too many operands. System will now exit.");
//				System.exit(0);
//				result = 0.0;
//				return result;
//			} else {
			result = Double.parseDouble(element.toString());
			return result;
//			}
		}
	}

//	public MyStack<ExpressionTree> treeStack = new MyStack<ExpressionTree>();

}