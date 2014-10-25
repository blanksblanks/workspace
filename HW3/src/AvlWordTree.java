/**
 * 
 * AvlWordTree
 * 
 * @author nb2406
 * 
 *         Write a command line application that indexes the words contained in
 *         a text file (provided to the program as a command line argument).
 *         Your program should go through the input file line by line. For each
 *         line, extract each word, and insert that word, along with it's
 *         location into an AVL tree. Each element of the AVL tree should
 *         contain a unique word and a linked list of line numbers where that
 *         word occurs. If word already exists in the AVL Tree, simply add the
 *         new line number to the existing node. When you have finished, print
 *         out each unique word that appeared in the input file along with a
 *         list of line on which that word appears. You may use Weiss' AVL tree
 *         code as a starting point for your program.
 * 
 */

// Modifies AvlTree class by Weiss
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x, line ) --> Insert x + line number
// ^ modified so if tree already contains x, line number still added as new
// entry to the linked list I added to the private AVL word node definition
// void remove( x )       --> Remove x (unimplemented)
// boolean contains( x )  --> Return true if x is present
// boolean remove( x )    --> Return true if x was present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order a-z
// ^ modified so tree prints line numbers the word node appears too
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Iterator;


/**
 * Implements an AVL tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class AvlWordTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public AvlWordTree( )
    {
        root = null;
    } 
    
    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x, int line )
    {
        root = insert( x, root, line);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

       
    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlWordNode<AnyType> remove( AnyType x, AvlWordNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        { 
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return balance( t );
    }
    
    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if x is found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    private static final int ALLOWED_IMBALANCE = 1;
    
    // Assume t is either balanced or within one of being balanced
    private AvlWordNode<AnyType> balance( AvlWordNode<AnyType> t )
    {
        if( t == null )
            return t;
        
        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
            if( height( t.left.left ) >= height( t.left.right ) )
                t = rotateWithLeftChild( t );
            else
                t = doubleWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild( t );
            else
                t = doubleWithRightChild( t );

        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    
    public void checkBalance( )
    {
        checkBalance( root );
    }
    
    private int checkBalance( AvlWordNode<AnyType> t )
    {
        if( t == null )
            return -1;
        
        if( t != null )
        {
            int hl = checkBalance( t.left );
            int hr = checkBalance( t.right );
            if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
                    height( t.left ) != hl || height( t.right ) != hr )
                System.out.println( "OOPS!!" );
        }
        
        return height( t );
    }
    
    
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    
    // Added line number parameter
    private AvlWordNode<AnyType> insert( AnyType x, AvlWordNode<AnyType> t, int line )
    {
        if( t == null )
            return new AvlWordNode<>( x, null, null, line);
        
        int compareResult = x.compareTo( t.element );
        
        if( compareResult < 0 )
            t.left = insert( x, t.left, line ); // nb
        else if( compareResult > 0 )
            t.right = insert( x, t.right, line ); // nb
        else
            t.lines.add(line);  // nb add line numbers to it if it exists already
        return balance( t );
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private AvlWordNode<AnyType> findMin( AvlWordNode<AnyType> t )
    {
        if( t == null )
            return t;

        while( t.left != null )
            t = t.left;
        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private AvlWordNode<AnyType> findMax( AvlWordNode<AnyType> t )
    {
        if( t == null )
            return t;

        while( t.right != null )
            t = t.right;
        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return true if x is found in subtree.
     */
    private boolean contains( AnyType x, AvlWordNode<AnyType> t )
    {
        while( t != null )
        {
            int compareResult = x.compareTo( t.element );
            
            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else
                return true;    // Match
        }

        return false;   // No match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree( AvlWordNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            for (int i = 0; i <t.lines.size(); i++){
        		System.out.print(t.lines.get(i) + " ");
            }
            System.out.println( t.element );
            // TODO: fix up formatting
            printTree( t.right );
        }
    }

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height( AvlWordNode<AnyType> t )
    {
        return t == null ? -1 : t.height;
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private AvlWordNode<AnyType> rotateWithLeftChild( AvlWordNode<AnyType> k2 )
    {
        AvlWordNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private AvlWordNode<AnyType> rotateWithRightChild( AvlWordNode<AnyType> k1 )
    {
        AvlWordNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private AvlWordNode<AnyType> doubleWithLeftChild( AvlWordNode<AnyType> k3 )
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private AvlWordNode<AnyType> doubleWithRightChild( AvlWordNode<AnyType> k1 )
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }

    private static class AvlWordNode<AnyType>
    {
            @SuppressWarnings("unused")
        
        // Base constructor     
        AvlWordNode(){
            	this(null, 0);
        }
        
        // Overloaded constructor with more arguments
        AvlWordNode( AnyType theElement, int line)
        {
            this( theElement, null, null, line);
        }

        // Designated constructor - the one that's doing the work
        AvlWordNode( AnyType theElement, AvlWordNode<AnyType> lt, AvlWordNode<AnyType> rt, int line )
        {
        	// Initializing instance vars
            lines = new LinkedList<Integer>();
            lines.add(line);
        	element  = theElement;
            left     = lt;
            right    = rt;
            height   = 0;
        }

        // Declaring instance vars
        AnyType           element;      // The data in the node
        AvlWordNode<AnyType>  left;         // Left child
        AvlWordNode<AnyType>  right;        // Right child
        int               height;       // Height
        LinkedList<Integer> lines;		// Line number
    }

      /** The tree root. */
    private AvlWordNode<AnyType> root;


// Test program
    
	public static void main(String[] args) throws FileNotFoundException {
	    if (args.length < 1 || args.length > 1) {
	        System.out.println("Input file not properly specified. Please try again!");
		    System.exit(1);
		} else {
			File inFile = new File(args[0]);
			if (inFile.exists()) {
				LinkedList<String>linesList = new LinkedList<>();
				Scanner input = new Scanner(inFile);
				while (input.hasNextLine()) {
					String line = input.nextLine();
					linesList.add(line);
				}
				input.close();
				AvlWordTree<String> wordTree = buildTree(linesList);
				wordTree.printTree();	
			} else {
				System.out.print("That file does not appear to exist. Please try again!");
				System.exit(1);
			}
		}
	}
		
// TODO: Should this method be private and initialized in the wordTree constructor?
	public static AvlWordTree<String> buildTree(LinkedList<String> LoL){
		// Initialize tree, line number and iterator for each line
		AvlWordTree<String> wordTree = new AvlWordTree<>();
		int lineNumber = 1;
		Iterator<String> lines = LoL.iterator();
		// Insert words and line number from each line into the tree
		while (lines.hasNext()) {
			String line = lines.next();
			StringTokenizer words = new StringTokenizer(line);
			while (words.hasMoreTokens()) {
				String word = words.nextToken();
				// Check that token contains alphabetical characters i.e. a word
				if (word.matches(".*[a-zA-Z]+.*")) {
					// Remove punctuation before and after each word (but not
					// inside, or single quotes)
					word = word.replaceFirst("^[^a-zA-Z']+", "")
							.replaceAll("[^a-zA-Z']+$", "").toLowerCase();
					wordTree.insert(word, lineNumber);
				}
			}
			lineNumber++;
        }
		return wordTree;	
	}

}
