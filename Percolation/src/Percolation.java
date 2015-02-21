public class Percolation {
	
	public int N;
	public boolean[][] grid;
	public WeightedQuickUnionUF uf;
	
	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		this.N = N;
		grid = new boolean[N][N];
		for (int i = 1; i < N; i++ ) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = false;
				System.out.println("Index " + i + "," + j + " is " + grid[i][j]);
			}
		}
		uf = new WeightedQuickUnionUF(N * N + 2);
	}
	
	// open site (row i, column j) if it is not open already
	public void open(int i, int j) {
		isIndexValid(i,j);
		if (!isOpen(i,j))
			grid[i-1][j-1] = true;
		
		int id = setID(i,j);
		
		// if index is in top/bottom row, union with source/destination
		if (i == 1)
			uf.union(0, id);
		if (i == N)
			uf.union(N*N+1, id);
		
		// check all neighbors: up, down, left, right
        if (i < N) {
            if (isOpen(i + 1, j))
                uf.union(setID(i + 1, j), id);
        }
		if (i > 1) {
            if (isOpen(i - 1, j))
                uf.union(setID(i - 1, j), id);
        }
        if (j > 1) {
            if (isOpen(i, j - 1))
                uf.union(setID(i, j - 1), id);
        }
        if (j < N) {
            if (isOpen(i, j + 1))
                uf.union(setID(i, j + 1), id);
        }
	}
	
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		isIndexValid(i,j);
		boolean isOpen = grid[i-1][j-1];
		System.out.println("Is site (" + i + "," + j + ") open? " + isOpen);
		return isOpen;
	}
	
	// is site (row i, column j) full?
	// 'full' definition: connects to open site in top row
	public boolean isFull(int i, int j) {
		isIndexValid(i,j);
		boolean isFull = uf.connected(0, setID(i,j));
		System.out.println("Is site (" + i + "," + j + ") full? " + isFull);
		return isFull;
	}
	   
	// does the system percolate?
	public boolean percolates() {
		boolean percolates = uf.connected(0,N*N+1);
		System.out.println("Does the system percolate? " + percolates);
		return percolates;
	}
	
	public void isIndexValid(int i, int j) {
		i--; j--;
        if (i < 0 || i > N-1 || j < 0 || j > N-1) {
        	System.err.println("Out of bounds. Exiting.");
        	System.exit(0);
        }
	}
	
	// set unique identifier for (i,j) index
	// for example: [0][0], [0][1], [1][0], [1][1]
	// N = 2, called by (1,1), (1,2), (2,1), (2,2)
	// becomes 1, 2, 3, 4 :)
	public int setID(int i, int j) {
		int id = (i - 1) * N + j;
		return id;
	}
	
	public static void main(String[] args) {
		Percolation p = new Percolation(2);
		
		// Test if percolation works
		p.open(1,1);		
		p.open(2,2);
		p.open(1,2);
		p.isFull(2, 2);
		p.percolates();
		
		// Test if out of bounds works
		// p.isIndexValid(3,3);
	}

}
