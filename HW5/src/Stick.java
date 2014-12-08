public class Stick implements Comparable<Stick> {

	public String name;
	public double x1;
	public double y1;
	public double z1;
	public double x2;
	public double y2;
	public double z2;
	public double x; // (x, y, z) = intersection point
	public double y;
	public double z;
	public double m; // where mx + ny = b
	public double n;
	public double b;

	public Stick(String name, double x1, double y1, double z1, double x2,
			double y2, double z2) {
		this.name = name;
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
	}

	public int compareTo(Stick v) {
		// Steps 1-4:
		this.checkRelationship(v);

		// Step 5. Now that we know z for both sticks, we know their
		// relationship
		if (this.z < v.z)
			return -1;
		else if (this.z > v.z)
			return 1;
		else
			return 0;
	}

	/*
	 * Returns 0 if unrelated, 1 if a is above b, -1 if a is below b
	 */
	private void checkRelationship(Stick v) {

		// Set coefficients for a 2x2 matrix representing a system of linear
		// equations:
		// [ a.m a.n ] [ x ] = [ a.b ]
		// [ b.m b.n ] [ y ] [ b.b ]
		setCoefficients(this);
		setCoefficients(v);

		double[][] matrix = new double[2][2];
		double[][] vector = new double[2][1];
		matrix[0][0] = this.m;
		matrix[0][1] = v.m;
		matrix[1][0] = this.n;
		matrix[1][1] = v.n;
		vector[0][0] = this.b;
		vector[1][0] = v.b;

		// Step 1. Check the determinant
		// If the determinant is 0, the matrix is invertible, which means it
		// does not have a unique solution to mx + ny = b, i.e. no intersection

		double det = (this.m * v.n) - (this.n * v.m);
		System.out.println("Determinant: " + det);
		if (det == 0)
			unrelated(v); // they are unrelated

		// Step 2. Find the intersection point
		if (det != 0) {
			matrix = augment(matrix, vector, 2);
			matrix = forwardEliminate(matrix, 2);
			vector = backSubstitute(matrix, 2);
			setXandY(this, vector);
			setXandY(v, vector);
			System.out.println("Line segments intersect at: (" + this.x + ", "
					+ this.y + ")");

			// Step 3. Check if intersection point is valid
			// return 0 if the intersection point is beyond the stick/line
			// segment of A or B
			if (this.x < Math.min(this.x1, this.x2)
					|| this.x > Math.max(this.x1, this.x2))
				unrelated(v);
			else if (v.x < Math.min(v.x1, v.x2) || v.x > Math.max(v.x1, v.x2))
				unrelated(v);
			else {
				// Step 4. Find z using parametric equations of lines A and B:
				// x = x1 + r (x2 - x1)
				// y = y1 + r (y2 - y1)
				// z = z1 + r (z2 - z1)
				// Since we know x (and y) now, we can find r = (xi - x1) / (x2
				// - x1)
				// Find z for both A and B, the line with the higher z is on top
				setZ(this);
				setZ(v);
				System.out.println("The z-coordinate for " + this.name + " is "
						+ this.z + " and for " + v.name + " is " + v.z);
			}
		}

	}

	private void setCoefficients(Stick v) {
		v.m = (v.y2 - v.y1) / (v.x2 - v.x1);
		v.b = v.y1 - v.m * (v.x1);
		v.n = 1;
		System.out.println("Stick " + v.name + ": " + v.m + "x + " + v.n + "y"
				+ " = " + v.b);
	}

	private void setXandY(Stick v, double[][] vector) { // set intersection
														// values
		v.x = vector[0][0];
		v.y = vector[1][0];
	}

	private void setZ(Stick v) {
		double r = (v.x - v.x1) / (v.x2 - v.x1);
		v.z = v.z1 + r * (v.z2 - v.z1);
	}

	private void unrelated(Stick v) {
		this.z = 0;
		v.z = 0;
		System.out
				.println("Either the determinant is 0, or the intersection is not in the segment");
	}

	private double[][] augment(double[][] matrix, double[][] vector, int n) {
		double[][] augmented = new double[n][n + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				augmented[i][j] = matrix[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			augmented[i][n] = vector[i][0];
		}
		return augmented;
	}

	private double[][] forwardEliminate(double[][] A, int n) {
		for (int k = 0; k < (n - 1); k++) {
			for (int i = k + 1; i < n; i++) {
				double multiplier = A[i][k] / A[k][k];
				for (int j = k; j < n; j++)
					A[i][j] = A[i][j] - (multiplier * A[k][j]);
				A[i][n] = A[i][n] - (multiplier * A[k][n]);
			}
		}
		return A;
	}

	private double[][] backSubstitute(double[][] U, int n) {
		double[][] x = new double[n][1];
		x[n - 1][0] = U[n - 1][n] / U[n - 1][n - 1];
		for (int i = (n - 1); i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				U[i][n] -= U[i][j] * x[j][0]; 
			}
			x[i][0] = U[i][n] / U[i][i]; 
		}
		return x;
	}

	public static void main(String[] args) {
		Stick a = new Stick("a", 0, 0, 0, 6, 6, 6);
		Stick b = new Stick("b", 0, 6, 2, 6, 0, 5);

		int relationship = a.compareTo(b);
		System.out.println("\nIn conclusion...");
		switch (relationship) {
			case 0:
				System.out.println("They are unrelated");
				break;
			case 1:
				System.out.println("Stick a is above stick b");
				break;
			case -1:
				System.out.println("Stick a is below stick b");
				break;
		}
	}

}
