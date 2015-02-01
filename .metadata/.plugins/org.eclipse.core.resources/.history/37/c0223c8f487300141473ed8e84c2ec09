public class HuffmanNode implements Comparable<HuffmanNode> {
		
		// Declaring instance vars
		private String character;
		public HuffmanNode left;
		public HuffmanNode right;
		private int frequency;
		private int depth;
		private String code;
		
		// Base constructor
		public HuffmanNode(String c){
			this(c, null, null, 1, 0, "tempvalue");
		}
		
		private HuffmanNode(String c, HuffmanNode lt, HuffmanNode rt, int f, int d, String bc) {
			character =  c;
			left = lt;
			right = rt;
			frequency = f;
			depth = d;
			code = bc;
		}
		
	    public HuffmanNode(int tn, HuffmanNode rt, HuffmanNode lt) {
	        character = "T" + tn;
	        frequency = rt.frequency + lt.frequency;
//	        increaseDepth(rt);
//			increaseDepth(lt);
	        right = rt;
	        left = lt;
	    }
		
	    // the promise
		public int compareTo(HuffmanNode node){
			if (this.frequency > node.frequency) return 1;
			else if (this.frequency < node.frequency) return -1;
			else return 0;
		}
		
		public boolean isLeaf(){
			return (this.right == null && this.left == null);
		}
		
		public void increaseFrequency() {
	        frequency++;
	    }
		
	    public int getFrequency() {
	        return this.frequency;
	    }
	    
//		public void increaseDepth(HuffmanNode node) {
//			node.depth++;
//			if (!node.isLeaf()){ // if not a leaf increase depth of all its children
//				increaseDepth(node.right);
//				increaseDepth(node.left);
//			}
//		}
		
		public int getDepth() {
			return this.depth;
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