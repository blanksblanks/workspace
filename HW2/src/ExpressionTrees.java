/**
 * 
 * Expression Tree
 * 
 * @author nb2406
 * 
 *         Implementing and displaying expression trees: Your program will be a
 *         text-based Java application. Prompt the user to enter a postfix
 *         expression (made up of single digit numbers and the +, -, *, and /
 *         operators). When the user presses enter, the stack based method for
 *         constructing expression trees will be executed. Use your stack from
 *         the previous question. You should implement your own ExpressionTree
 *         class to store the expression. Once the tree has been built, have
 *         your program use the tree to output the equivalent prefix expression
 *         and infix expression (don't forget parenthesis for this one). Finally
 *         the program should evaluate the expression tree and print the result.
 * 
 *         Clarifications: There are two kinds of bad situations you need to
 *         account for. The first is if you pop from the stack and the stack
 *         happens to be empty. When this happens, your program should die
 *         gracefully and tell the user what went wrong. The second case is at
 *         the end of the algorithm when you pop from the stack one last time.
 *         If the stack still has elements on it, then you will also need to die
 *         gracefully and tell the user what went wrong.
 * 
 */

import java.util.Scanner;
import java.util.NoSuchElementException;

public class ExpressionTrees {

	private MyStack<TreeNode> tree;
	private TreeNode root;
	static MyStack<TreeNode> treeStack;

	private static class TreeNode {
		private String element;
		private TreeNode left;
		private TreeNode right;

		private TreeNode(String s) {
			element = s;
		}

		private TreeNode(String s, TreeNode left, TreeNode right) {
			element = s;
			this.left = left;
			this.right = right;
		}
	}

	// public constructor to build tree
	public ExpressionTrees(String[] s) {
		tree = buildTree(s);
		root = tree.peek();
	}

	// private constructor to build tree
	private static MyStack<TreeNode> buildTree(String[] s) {
		try {
			treeStack = new MyStack<>();
			for (int i = 0; i < s.length; i++) {
				String element = s[i];
				if (isOperator(element)) {
					TreeNode right = treeStack.peekAndPop();
					TreeNode left = treeStack.peekAndPop();
					TreeNode root = new TreeNode(element, left, right);
					treeStack.push(root);
				} else {
					treeStack.push(new TreeNode(element));
				}
			}
			if (treeStack.size() > 1) {
				System.err.println("You have too many operands and too few operators. Please try again.");
				System.exit(1);
			}
		} catch (NoSuchElementException e) {
			System.err.println("You have too many operators and too few operands. Please try again.");
			System.exit(1);
		} catch (IndexOutOfBoundsException f) {
			System.err.println("You have too many operators and too few operands. Please try again.");
			System.exit(1);
		}
		return treeStack;
	}

	// private method to check if String is an operator
	private static boolean isOperator(String s) {
		return (s.equals("+") || s.equals("-") || s.equals("*") || s
				.equals("/"));
	}

	// public method to convert expression tree to print prefix expression
	public static void printPrefix(ExpressionTrees tree) {
		printPrefix(tree.root);
		System.out.println();
	}

	// private recursive method to convert expression tree to print prefix expression
	private static void printPrefix(TreeNode node) {
		while (node != null) {
			System.out.print(node.element);
			printPrefix(node.left);
			node = node.right;
		}
	}

	// public method to convert expression tree to print infix expression
	public static void printInfix(ExpressionTrees tree) {
		printInfix(tree.root);
		System.out.println();
	}

	// private method to convert expression tree to print infix expression
	private static void printInfix(TreeNode node) {
		if (node.left != null) {
			System.out.print("(");
			printInfix(node.left);
		}
		System.out.print(node.element);
		if (node.right != null) {
			printInfix(node.right);
			System.out.print(")");
		}
	}

	// public method to evaluate expression tree
	public static Double evaluate(ExpressionTrees tree) {
		Double result = evaluate(tree.root);
		treeStack.pop();
		return result;
	}
	
	// private method to evaluate expression tree
	// if no children, that's our base case, return the double in the node
	// else evaluate(right) and evaluate(left)
	// when there is two children, it's an operator
	private static Double evaluate(TreeNode node) {
		Double result = 0.0;
		try {
			if (node.left == null && node.right == null) {
				result = (Double.parseDouble(node.element.toString()));
			} else {
				double left = evaluate(node.left);
				double right = evaluate(node.right);
				String operator = node.element;
				switch (operator) {
					case "+":
						result = left + right;
						break;
					case "-":
						result = left - right;
						break;
					case "*":
						result = left * right;
						break;
					case "/":
						result = left / right;
						break;
					default:
						System.err.println("Your expression is invalid. Please try again.");
						System.exit(1);
						break;
				}
			}
		} catch (NumberFormatException e) {
			System.err.print("You have an invalid expression. Please try again.");
			System.exit(1);
		}
		return result;
	}
	
	// obsolete method to solve post-fix expression with String array as parameter
/*	public static Double solve(String[] s) {
		MyStack<Double> solStack = new MyStack<>();
		Double result = 0.0;
		try {
			for (int i = 0; i < s.length; i++) {
				String element = s[i];
	
				if (isOperator(element)) {
					double right = solStack.peekAndPop();
					double left = solStack.peekAndPop();
	
					switch (element) {
						case "+":
							result = left + right;
							solStack.push(result);
							break;
						case "-":
							result = left - right;
							solStack.push(result);
							break;
						case "*":
							result = left * right;
							solStack.push(result);
							break;
						case "/":
							result = left / right;
							solStack.push(result);
							break;
						default:
							System.err.println("Your expression is invalid. Please try again.");
							System.exit(1);
							break;
					}
	
				} else {
					solStack.push(Double.parseDouble(element.toString()));
				}
			}
		} catch (NumberFormatException e) {
					System.err.print("You have an invalid expression. Please try again.");
					System.exit(1);
				}
			result = solStack.peekAndPop();
			return result;
	}*/
	
	public static void main(String[] args) {

		System.out
				.println("Please enter a postfix expression (e.g. 12+3*6+23+/):");
		Scanner readin = new Scanner(System.in);
		
			String postfix = readin.nextLine();
			readin.close();
			
			// remove any spaces and illegal characters
			postfix = postfix.replaceAll("\\s", "");
			postfix = postfix.replaceAll("[^\\d/*+-]", "");
			
			// convert string into string array
			String[] postfixArray = postfix.split("(?!^)");
			
			ExpressionTrees tree = new ExpressionTrees(postfixArray);
			System.out.print("Prefix expression: ");
			printPrefix(tree);
			System.out.print("Infix expression: ");
			printInfix(tree);
			System.out.println("Evaluation of postfix expression: "
					+ evaluate(tree));

	}
}
