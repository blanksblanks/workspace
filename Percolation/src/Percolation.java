public class Percolation {
	
	public int N;
	public boolean[][] grid;
	public WeightedQuickUnionUF uf;
	
	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		this.N = N;
		grid = new boolean[N][N];
		for (int i = 1; i <= N; i++ ) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = false;
				System.out.println("Grid " + i + j + "is" + grid[i][j]);
			}
		}
		uf = new WeightedQuickUnionUF(N * N + 2);
	}
	
	// open site (row i, column j) if it is not open already
	public void open(int i, int j) {
		isIndexValid(i,j);
		if (!isOpen(i, j))
			grid[i][j] = true;		   
	}
	
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		int row = i - 1;
		int col = j - 1;
		return true;   
	}
	
	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		int row = i - 1;
		int col = j - 1;
		return true;   
	}
	   
	// does the system percolate?
	public boolean percolates() {
		
		   return true;
	}
	
	public void isIndexValid(int i, int j) {
        if (i < 0 || i > N-1 || j < 0 || j > N-1) {
        	System.err.println("Out of bounds. Exiting.");
        	System.exit(0);
        }
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		Percolation perc = new Percolation(10);
		System.out.println(perc);
	}

}
