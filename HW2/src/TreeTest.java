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

// Test class that takes postfix expression from user input and transforms it
// into a tree
public class TreeTest {

	public static void main(String[] args) {

		System.out
				.println("Please enter a postfix expression (e.g. 12+3*6+23+/):");
		// Equivalent to: (((1 + 2) * 3) + 6) / (2 + 3))  and  (/ (+ (* (+ 1 2) 3) 6) (+ 2 3))
		
		Scanner readin = new Scanner(System.in); // scanner reads in user input
		String postfixIn = readin.nextLine();
		readin.close();

		String s = postfixIn.replaceAll("\\s", ""); // remove spaces
		s = s.replaceAll("[^\\d/*+-]", ""); // remove any illegal characters
		String[] postfixArray = s.split("(?!^)"); // converts string into array

		// Call stack-based method for constructing expression trees from postfix array
		TreeMaker construct = new TreeMaker();
		
		// Store postfix expression in Expression Tree class
		ExpressionTree postFixTree = construct.makeTree(postfixArray);
		
		// Print out results
		System.out.println("Prefix expression: " + postFixTree.printPrefix());
		System.out.println("Infix expression: " + postFixTree.infixPrint());
		System.out.println("Evaluation of postfix expression: "
				+ postFixTree.compute());

	}

}