import java.util.LinkedList;
import java.util.Queue;

public class HuffmanTree {
	
	private HuffmanNode root;
	
	public HuffmanTree(HuffmanNode[] array) throws UnderflowException {		
		BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>(array);
		buildHuffmanTree(heap);
		String binaryCode = ""; // After tree is built, build binary codes for leaves
		encodeLeaves(root, binaryCode);
	}
	
	private void buildHuffmanTree(BinaryHeap<HuffmanNode> heap) throws UnderflowException {
		int num = 1; // initialize at T1
		while (heap.getCurrentSize() > 1) { // merge two smallest trees
			HuffmanNode right = heap.deleteMin();
			HuffmanNode left = heap.deleteMin();
			HuffmanNode t = new HuffmanNode(num++, right, left);
			System.out.println("Inserted t-l-r: " + t.getCharacter() + ", " + left.getCharacter() + " and " + right.getCharacter());
			heap.insert(t); // throw it back in the heap
			root = t; // last node is the root of the tree
		}
	}
	
	// TODO: edge cases: what happens if t is null or only one letter in the tree?
	private String encodeLeaves(HuffmanNode t, String digits) {
		if (t.isLeaf())
			t.setBinaryCode(digits);
		else {
			encodeLeaves(t.left, digits + "0");
			encodeLeaves(t.right, digits + "1");
		}
		return digits;
	}
	

	
	public String decode(String binary){
//		String text = decode(root, binary, 0, "");
		String text = "";
		int length = binary.length();
		int i = 0;
		while (i < length){
			HuffmanNode t = root;
			while (!t.isLeaf()){
				System.out.println(binary.charAt(i));
				t = (binary.charAt(i) == '0') ? t.left : t.right;
				i++;
			}
			text += t.getCharacter();
		}
		return text;
	}
	
	
	@SuppressWarnings("unused")
	private String decode(HuffmanNode t, String code, int index, String text){
		if (index <= code.length()){
			if (t.isLeaf()) {
				text += t.getCharacter(); // concatenate char
				if (index == code.length())
					return text;
				else
					decode(root, code, index, text);
			} else {
				if (code.charAt(index) == '0')
					decode(t.left, code, index+1, text);
				else if (code.charAt(index) == '1')
					decode(t.right, code, index+1, text);
			}
		}
		return text;
	}
	
	public void printTree(){
		this.printTree(root);
	}
	
    private void printTree(HuffmanNode t) {
        double nodes = 0.0;
        double level = 0.0;

        Queue<HuffmanNode> q = new LinkedList<HuffmanNode>();
        q.add(t);
        
//        System.out.println("Added " + t.getCharacter() + " to queue");
        while (!q.isEmpty()) {
            HuffmanNode node = q.poll();
//            	System.out.println("Removed " + node.getCharacter() + " from queue");

            if (node.left != null && node.right != null) {
//            	nodes += 2;
            	q.add(node.left);
            	q.add(node.right);
            	System.out.print("/\\");
//            	System.out.println("Added " + node.left.getCharacter() + " and " + node.right.getCharacter() + " to queue");
            } else if (node.left != null && node.right == null) { // draw /
//                nodes++;
                q.add(node.left);
            	HuffmanNode blankr = new HuffmanNode("blankr");
                q.add(blankr);
            	System.out.print("/");
//            	System.out.println("Added " + node.left.getCharacter() + " and blank to queue");
            } else if (node.left == null && node.right != null) { // draw \
//            	nodes++;
            	HuffmanNode blankl = new HuffmanNode("blankl");
            	q.add(blankl);
            	q.add(node.right);
            	System.out.print("\\");
//            	System.out.println("Added blank and " + node.right.getCharacter() + " to queue");
            } else { // (node.left == null && node.right == null)
//            	 nodes += (Math.pow(2.0, level) - nodes);
            	//System.out.println("Reached leaf. do nothing");
            }
            
            System.out.print(node.getCharacter() + "\t");
            nodes++; // node
//          System.out.println(Math.pow(2.0, level) + " compared to number of nodes " + nodes);
          if (Math.pow(2.0, level) == nodes) {
              level += 1.0;
              nodes = 0;
              System.out.println("\n");
//              System.out.println("increased level by 1");
          } 
//          else { 
//              nodes += (Math.pow(2.0, level) - nodes);
////          	System.out.println("increased nodes by 1");
//          }
          
/*

/\
 t5
/\/\
t4 t3
/\
t2 e a s
   /\
sp t1
  t nl

 */
        
        }
    }
}
