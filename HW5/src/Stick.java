
public class Stick implements Comparable<Stick> {
	
	public String name;
	public int x1;
	public int y1;
	public int z1;
	public int x2;
	public int y2;
	public int z2;
	public int x; // (x, y, z) = intersection point
	public int y;
	public int z; 
	public int m; // where mx + ny = b
	public int n;
	public int b;
		
	
	public Stick(String name, int x1, int y1, int z1, int x2, int y2, int z2){
		this.name = name;
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
	}
				
	public int compareTo(Stick v){
		this.checkRelationshipTo(v);
		if (this.z < v.z) return -1;
		else if (this.z > v.z) return 1;
		else return 0;
	}
	
	
	/* A 2x2 matrix representing a system of linear equations: 
	* [ a.m  a.n ] [ x ] = [ a.b ]
	* [ b.m  b.n ] [ y ]   [ b.b ]
	*
	* Returns 0 if unrelated, 1 if a is above b, -1 if a is below b
	*/
	private void checkRelationshipTo(Stick v){
		
//		setCoefficients(this);
//		setCoefficients(v);
		
//		Stick[] sticks = new Stick[2];
//		sticks[0] = this;
//		sticks[1] = v;
//		
//		for (int i = 0; i < 2; i++){
//			sticks[i].m = 
//		}
	   
	    // If the determinant is 0, the matrix is invertible, which means it 
	    // does not have a unique solution to mx + ny = b, i.e. no intersection

	    int det = (this.x1 - this.x2)*(v.y1 - v.y2) - (this.y1 - this.y2)*(v.x1 - v.x2) ;
//		int det = (1*1 - (-1*1));
	    System.out.println("Determinant:" + det);
	    if (det == 0) unrelated(v); // they are unrelated
	    
	    int xi = ((v.x1 - v.x2) * (this.x1*this.y2 - this.y1*this.x2) - (this.x1 - this.x2) * (v.x1*v.y2 - v.y1*v.x2)) / det;
	    int yi = ((v.y1 - v.y2) * (this.x1*this.y2 - this.y1*this.x2) - (this.y1 - this.y2) * (v.x1*v.y2 - v.y1*v.x2)) / det;
	    
	    
	    System.out.println("Line segments intersect at " + xi + " and " + yi);

	    // return 0 if the intersection point is beyond the stick/line segment of A or B
	    if (xi < Math.min(this.x1,this.x2) || xi > Math.max(this.x1,this.x2)) unrelated(v);
	    if (xi < Math.min(v.x1,v.x2) || xi > Math.max(v.x1,v.x2)) unrelated(v);

	    // Represents parametric equations of lines A and B
	    // xi = x1 + r (x2 - x1)
	    // yi = y1 + r (y2 - y1)
	    // zi = z1 + r (z2 - z1)
	    // Since we know xi now, we can find r = (xi - x1) / (x2 - x1)
	    //
	    // Find zi for both A and B, the line with the higher z is on top

	    this.z = this.z1 + ((xi - this.x1) / (this.x2 - this.x1)) * (this.z2 - this.z1);
	    v.z = v.z1 + ((xi - v.x1) / (v.x2 - v.x1)) * (v.z2 - v.z1);
	    
	    System.out.println("The z-coordinate for " + this.name + " is " + this.z
	    		+ " and for " + v.name + " is " + v.z);
	    
	}
	
	private void setCoefficients(Stick v){
		v.m = (v.y2 - v.y1) / (v.x2 - v.x1);
		v.b = v.y1 - v.m*(v.x1);
		v.n = 1;
	}
	
	private void unrelated(Stick v){
    	this.z = 0;
    	v.z = 0;
    	System.out.println("Either the determinant is 0, or the intersection found is beyond the line segment");
	}
	
	public static void main(String[] args){
		Stick a = new Stick("a", 0, 0, 0, 6, 6, 6);
		Stick b = new Stick("b", 0, 6, 2, 0, 6, 5);

		System.out.print("Therefore ");
		int relationship = a.compareTo(b);
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
