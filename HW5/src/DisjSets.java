// DisjSets class
//
// CONSTRUCTION: with int representing initial number of sets
//
// ******************PUBLIC OPERATIONS*********************
// void union( root1, root2 ) --> Merge two sets
// int find( x )              --> Return set containing x
// ******************ERRORS********************************
// No error checking is performed

/**
 * Disjoint set class, using union by rank and path compression.
 * Elements in the set are numbered starting at 0.
 * @author Mark Allen Weiss
 */
public class DisjSets<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the disjoint sets object.
     * @param numElements the initial number of disjoint sets.
     */
    @SuppressWarnings("unchecked")
	public DisjSets( int numElements )
    {
        s = (AnyType []) new Comparable[ numElements ];
        for( int i = 0; i < s.length; i++ )
            s[ i ] = -1;
    }

    /**
     * Union two disjoint sets using the height heuristic.
     * For simplicity, we assume root1 and root2 are distinct
     * and represent set names.
     * @param root1 the root of set 1.
     * @param root2 the root of set 2.
     */
    public void union( Object root1, Object root2 )
    {
        if( ((Comparable<? super AnyType>) s[ root2 ]).compareTo((AnyType) s[ (int) root1 ]) < 0 )  // root2 is deeper
            s[ (int) root1 ] = root2;        // Make root2 new root
        else
        {
            if( ((Comparable<? super AnyType>) s[ (int) root1 ]).compareTo(s[ root2 ]) == 1 )
                s[ root1 ]--;          // Update height if same
            s[ root2 ] = root1;        // Make root1 new root
        }
    }
    

    /**
     * Perform a find with path compression.
     * Error checks omitted again for simplicity.
     * @param x the element being searched for.
     * @return the set containing x.
     */
    public int find( Object x )
    {
        if( s[ x ].compareTo(0) < 0 ) // if less than 0
            return x;
        else
            return s[ x ] = find( s[ x ] );
    }

    private Object [ ] s;


//    // Test main; all finds on same output line should be identical
//    public static void main( String [ ] args )
//    {
//        int NumElements = 128;
//        int NumInSameSet = 16;
//
//        DisjSets ds = new DisjSets( NumElements );
//        int set1, set2;
//
//        for( int k = 1; k < NumInSameSet; k *= 2 )
//        {
//            for( int j = 0; j + k < NumElements; j += 2 * k )
//            {
//                set1 = ds.find( j );
//                set2 = ds.find( j + k );
//                ds.union( set1, set2 );
//            }
//        }
//
//        for( int i = 0; i < NumElements; i++ )
//        {
//            System.out.print( ds.find( i )+ "*" );
//            if( i % NumInSameSet == NumInSameSet - 1 )
//                System.out.println( );
//        }
//        System.out.println( );
//    }
}
