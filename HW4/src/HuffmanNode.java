public class HuffmanNode implements Comparable<HuffmanNode> {
		
		// Declaring instance vars
		private String character;
		public HuffmanNode left;
		public HuffmanNode right;
		private int frequency;
		private int height;
		private String code;
		
		// Base constructor
		public HuffmanNode(String c){
			this(c, null, null, 1, 0, "");
		}
		
		private HuffmanNode(String c, HuffmanNode lt, HuffmanNode rt, int f, int d, String bc) {
			character =  c;
			left = lt;
			right = rt;
			frequency = f;
			height = 0;
			code = bc;
		}
		
	    public HuffmanNode(int tn, HuffmanNode lt, HuffmanNode rt) {
	        character = "T" + tn;
	        frequency = lt.frequency + rt.frequency;
	        left = lt;
	        right = rt;
	    }
		
	    // the promise
		public int compareTo(HuffmanNode t){
			if (this.frequency > t.frequency) return 1;
			else if (this.frequency < t.frequency) return -1;
			else return 0;
		}
		
		public boolean isLeaf(){
			return (this.right == null && this.left == null);
		}
		
		public void increaseFrequency() {
	        this.frequency++;
	    }
		
	    public int getFrequency() {
	        return this.frequency;
	    }
	    
	    public void setHeight(int h){
	    	this.height = h;
	    }
	    	    
		public int getHeight(HuffmanNode t) {
			return t == null ? -1 : t.height;
		}
		
	    public void fixHeight(){
	    	if (this.left.height != this.height - 1){
	    		this.left.height = this.height - 1;
	    		if (!this.left.isLeaf())
	    			this.left.fixHeight();
	    	}
	    	if (this.right.height != this.height - 1){
	    		this.right.height = this.height - 1;
	    		if (!this.right.isLeaf())
	    			this.right.fixHeight();
	    	}
	    }

	    public void setBinaryCode(String binary) {
	        code = binary;
	    }
	    
	    public String getBinaryCode(){     
	        return this.code;
	    }
	    
	    public String getCharacter(){
	        return this.character;
	    }

	    public String toString() {
	            if (this.character.equals(" "))              
	                return "sp";
	            else if (this.character.equals("\n"))               
	                return "nl";
	            else
	                return this.character;
	    }
	    


	}