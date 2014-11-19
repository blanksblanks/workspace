import java.util.LinkedList;
import java.util.Queue;

public class HuffmanTree {
	
	private HuffmanNode root;
	private BinaryHeap<HuffmanNode> tree;
	
	public HuffmanTree(HuffmanNode[] array) throws UnderflowException {
		
		this.tree = new BinaryHeap<HuffmanNode>(array);
		int leaves = array.length;
		buildTree(leaves);
		
		String binaryCode = "";
		buildBinaryCode(root, binaryCode);
	}
	
	private void buildTree(int leaves) throws UnderflowException {
		int num = 1; // init at T1
		while (tree.getCurrentSize() > 1) {
			HuffmanNode right = tree.deleteMin();
			HuffmanNode left = tree.deleteMin();
			HuffmanNode t = new HuffmanNode(num++, right, left);
			System.out.println("Inserted t-l-r: " + t.getCharacter() + ", " + left.getCharacter() + " and " + right.getCharacter());
			tree.insert(t);
			root = t; // last node is the root of the tree
		}
	}
	
	// TODO: edge cases: what happens if t is null or only one letter in the tree?
	private String buildBinaryCode(HuffmanNode t, String digits) {
		if (t.right == null && t.left == null) // we've reached a leaf!
			t.setBinaryCode(digits);
		else {
			buildBinaryCode(t.left, digits + "0");
			buildBinaryCode(t.right, digits + "1");
		}
		return digits;
	}
	
	public void printTree(){
		this.printTree(root);
	}
	
    private void printTree(HuffmanNode t) {
        double nodes = 1.0;
        double level = 0.0;
        Queue<HuffmanNode> q = new LinkedList<HuffmanNode>();
        q.add(t);
        while (!q.isEmpty()) {
            HuffmanNode node = q.poll();
            System.out.print(node + "\t"); // node
            if (Math.pow(2.0, level) == nodes) {
                level += 1.0;
                System.out.println("\n");
            }
            else 
                nodes += 1.0;
            if (node.left != null) // draw \
                q.add(node.left);
            if (node.right != null)
                q.add(node.right); // draw /
        }
    }
    
}
