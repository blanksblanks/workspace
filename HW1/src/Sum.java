
// Implements six loops that return sums from Exercise 2_7 in Weiss textbook
public class Sum {
	
	// Expected O(N)
	public double sumOne(double n) {
		double sum = 0;
		for ( double i = 0; i < n; i++ ) {
			sum++;
		}
		return sum;
	}
	
	// Expected O(N^2)
	public double sumTwo(double n) {
		double sum = 0;
		for ( double i = 0; i < n; i++ ) {
			for ( double j = 0; j < n; j++ ) {
				sum++;
			}
		}
		return sum;
	}
	
	// Expected O(N^3)
	public double sumThree(double n) {
		double sum = 0;
        for ( double i = 0; i < n; i++ ) {
            for ( double j = 0; j < n * n; j++ ) {
                sum++;
            }
        }
        return sum;
	}

	// Expected O(N^2)
	public double sumFour(double n) {
		double sum = 0;
        for ( double i = 0; i < n; i++ ) {
            for ( double j = 0; j < i; j++ ) {
                sum++;
            }
        }
        return sum;
	}
	
	// Expected O(N^5)
	public double sumFive(double n) {
		double sum = 0;
		for ( double i = 0; i < n; i++ ) {
			for ( double j = 0; j < (i * i); j++ ) {
		    	 for ( double k = 0; k < j; k++ ) {
		                    	   		sum++;
		    	 }
			}
		}
		return sum;
	}
	
	// Expected O(N^4)
	public double sumSix(double n) {
		double sum = 0;
		for ( double i = 1; i < n; i++ ) {
			for ( double j = 1; j < i * i; j++ ) {
				if ( j % i == 0 ) {
					for ( double k = 0; k < j; k++ ) {
		                              sum++;
					}
				}
			}
		}
		return sum;
	}
	
}
