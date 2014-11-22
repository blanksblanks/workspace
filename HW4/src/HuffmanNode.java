public class HuffmanNode implements Comparable<HuffmanNode> {
		
		// Declaring instance vars
		private String character;
		public HuffmanNode left;
		public HuffmanNode right;
		private int frequency;
		@SuppressWarnings("unused")
		private int height;
		private String code;
		
		// Base constructor
		public HuffmanNode(String c){
			this(c, null, null, 1, 0, "tempvalue");
		}
		
		private HuffmanNode(String c, HuffmanNode lt, HuffmanNode rt, int f, int h, String bc) {
			character =  c;
			left = lt;
			right = rt;
			frequency = f;
			height = h;
			code = bc;
		}
		
	    public HuffmanNode(int tn, HuffmanNode rt, HuffmanNode lt) {
	        character = "T" + tn;
	        frequency = rt.frequency + lt.frequency;
	        right = rt;
	        left = lt;
	    }
		
		public int compareTo(HuffmanNode node){
			if (this.frequency > node.frequency)
				return 1;
			else if (this.frequency < node.frequency)
				return -1;
			else
				return 0;
		}
		
		public boolean isLeaf(){
			return (this.right == null && this.left == null);
		}
		
		public void incrementFrequency() {
	        frequency += 1;
	    }

	    public int getFrequency() {
	        return this.frequency;
	    }

	    public void setBinaryCode(String binary) {
	        code = binary;
	    }
	    
	    public String getBinaryCode(){     
	        return this.code;
	    }
	    
	    public String getCharacter(){
//	    	if (character.equals(" "))              
//                return "sp";
//            else if (character.equals("\n"))               
//                return "nl";
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