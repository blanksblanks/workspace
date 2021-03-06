/**
 * 
 * Exercise 4_16
 * 
 * @author nb2406
 * 
 *         Redo the binary search tree class to implement lazy deletion. Note
 *         carefully that this affects all of the routines. Especially
 *         challenging are findMin and findMax, which must now be done
 *         recursively.
 * 
 *         Clarifications: Section 4.3 in the book has the BinarySearchTree
 *         code. Use this as skeleton code from which to implement lazy
 *         deletion.
 * 
 *         Feel free to use Weiss's code for programming 3 (Lazy Deletion). If
 *         you want to re-write it yourself, you can do that too but it needs to
 *         have the same methods.
 * 
 *         For programming 3, please have your isEmpty() method have constant
 *         O(1) running time.
 * 
 *         Lastly, for programming 3, please also submit a Tester class that
 *         builds a tree and then shows each of the methods working after an
 *         item is lazily deleted.
 * 
 */

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate
// Uses lazy deletion for remove method

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */

public class LazyDeletion<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public LazyDeletion( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
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
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     * @throws UnderflowException 
     */
    public AnyType findMin( ) throws UnderflowException
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     * @throws UnderflowException 
     */
    public AnyType findMax( ) throws UnderflowException
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     * Changed for lazy deletion
     */
    public void makeEmpty( )
    {
        root.deleted = true;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     * Changed for lazy deletion to run on constant time
     */
    public boolean isEmpty( )
    {
    	return (size == 0);
//    	return (root.deleted == true || root == null);
    }
    
	public int size() {
		return size;
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

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     * Inserts a new node, but first checks to see if it exists and just
     * needs its "deleted" toggled back to false
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null ) {
        	size++;
            return new BinaryNode<>( x, null, null );
        }
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else if ( t.deleted) { // && compareResult == 0
        	t.deleted = false;
        	size++;
        }

        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     * Toggles boolean as "deleted" instead of fully deleting it
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null)
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
//        System.out.println("Compare result of " + x + " to " + t.element + " is " + compareResult);
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 ) 
            t.right = remove( x, t.right );
        else if( thereExists(t.left) && thereExists(t.right) )	// Two Children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else {
        	t.deleted = true;
        	size--;
//        	t = ( t.left != null ) ? t.left : t.right;
        }
        return t;
    }
    
    private boolean thereExists(BinaryNode<AnyType> t) {
    	boolean exists = (t != null && !t.deleted);
    	return exists;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     * Fixed for Lazy Deletion
     */
    
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t)
    {
        if( t == null )
            return null;
        
        if (t.left != null) {
            BinaryNode<AnyType> j = findMin( t.left ); // changed for lazy deletion
            if (j != null)
        	    return j;
        }
         
        if (t.deleted == false)
        	return t;
        
        return findMin( t.right );

    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     * Changed recursively for lazy deletion
     */
    
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        
        if (t.right != null) {
            BinaryNode<AnyType> j = findMax( t.right ); // recursive
            if (j != null)
        	    return j;
        }
        
        if (t.deleted == false)
        	return t;
        
        return findMax( t.left );
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     * Checks if node is lazy deleted
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else if( t.deleted) // returns false if node is deleted
        	return false;
        else
            return true;
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     * Checks to see if node is deleted before printing
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null)
        {
            printTree( t.left );
            if( t.deleted == false) // only print nodes that aren't deleted
                System.out.println( t.element );
            else // do nothing
                ;
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    @SuppressWarnings("unused")
	private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        @SuppressWarnings("unused")
		BinaryNode( AnyType theElement )
        {
            this( theElement, null, null);
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            deleted = false; // add new property for lazy deletion
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        boolean deleted;          // Add a deleted property for Lazy Deletion
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;
    private int size = 0;

}