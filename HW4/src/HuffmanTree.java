import java.util.LinkedList;
import java.util.Queue;
import java.util.Hashtable;
// import javax.swing.*;
// import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


// import java.awt.geom.Line2D;
// import javax.swing.JPanel;
import javax.swing.JComponent;

import java.util.Random;

@SuppressWarnings("serial")
public class HuffmanTree extends JComponent {

	private HuffmanNode root;
	private Hashtable<String, String> hash;
	
	final int RADIUS = 10;
	final int HGAP = 10;
	final int VGAP = 100;
	private int hGap;
	private int levels;
	private int frameHeight;
	private int frameWidth;

	public HuffmanTree(HuffmanNode[] array) throws UnderflowException {
		BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>(array);
		buildHuffmanTree(heap);
		String binaryCode = ""; // After tree is built, build binary codes for
								// leaves
		encodeLeaves(root, binaryCode);
		levels = root.getHeight(root) + 1;
		frameHeight = (10 * (levels) + 3) * RADIUS; // + 3 for padding
		frameWidth = 1; // 2^(N+1) + 1
		for (int power = 1; power <= levels; power++)
			frameWidth *= 2;
		frameWidth = (frameWidth + 20) * RADIUS; // + 1 for padding
		hGap = RADIUS;
		for (int i = 1; i < levels; i++)
			hGap *= 2;
		System.out.println("Window height " + frameHeight + " and "
				+ frameWidth + " and horizontal gap " + hGap);
		setPreferredSize(new Dimension(frameWidth, frameHeight));
	}

	private void buildHuffmanTree(BinaryHeap<HuffmanNode> heap)
			throws UnderflowException {
		hash = new Hashtable<String, String>();
		int num = 1; // initialize at T1
		while (heap.getCurrentSize() > 1) { // merge two smallest trees
			HuffmanNode right = heap.deleteMin();
			HuffmanNode left = heap.deleteMin();
			HuffmanNode t = new HuffmanNode(num++, right, left);
			int h = Math.max(t.getHeight(t.left), t.getHeight(t.right)) + 1;
			t.setHeight(h);
			t.fixHeight();
			System.out.println("Inserted " + t.toString() + " with kids "
					+ left.toString() + " and " + right.toString()
					+ " at height" + t.getHeight(t) + " and "
					+ t.getHeight(t.left) + " and " + t.getHeight(t.right));
			heap.insert(t); // throw it back in the heap
			root = t; // last node is the root of the tree
		}
	}

	// TODO: edge cases: what happens if t is null or only o ne letter in the
	// tree?
	private String encodeLeaves(HuffmanNode t, String digits) {
		if (t.isLeaf()) {
			t.setBinaryCode(digits);
			hash.put(t.getCharacter(), t.getBinaryCode());
		} else {
			encodeLeaves(t.left, digits + "0");
			encodeLeaves(t.right, digits + "1");
		}
		return digits;
	}

	public String decode(String binary) {
		String text = "";
		String error = "This is not a valid binary encoding.";
		try {
			if (!binary.matches("[01]+")) return error;
			int i = 0;
			while (i < binary.length()) {
				HuffmanNode t = root;
				while (!t.isLeaf()) {
					t = (binary.charAt(i) == '0') ? t.left : t.right;
					i++;
				}
				text += t.getCharacter();
			}
			return text;
		} catch (IndexOutOfBoundsException e) {
			return error;
		} catch (NullPointerException n) {
			return error;
		}
	}

	public String encode(String s) {
		String binary = "";
		for (int i = 0; i < s.length(); i++) {
			String character = Character.toString(s.charAt(i));
			String code = hash.get(character);
			if (code == null)
				return (character + " is not in the Huffman tree.");
			else
				binary += code;
		}
		return binary;
	}

	/*
	 * GUI METHODS (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */

	public void paintComponent(Graphics g) {
		
		// Use a cast to recover the Graphics2D object from the Graphics param
		Graphics2D g2 = (Graphics2D) g;

		g2.setFont(new Font("Impact", Font.PLAIN, 12)); 
		displayTree(g2, root, (frameWidth - 1 ) / 2, RADIUS * 2, hGap);
		
		int x = 5;
		int y = 10;
		int d = 20;

		g2.setColor(Color.BLACK);
		g2.drawLine(15, 20, 45, 20);

		Ellipse2D.Double circle = new Ellipse2D.Double(x, y, d, d);
		Color mint = new Color(162, 255, 204);
		Color random = mixRandomColorWith(mint);
		g2.setColor(random);
		g2.fill(circle);
		g2.draw(circle);

		x += 30;

		Ellipse2D.Double circle2 = new Ellipse2D.Double(x, y, d, d);
		random = mixRandomColorWith(mint); // white is color to be mixed with
		g2.setColor(random);
		g2.fill(circle2);
		g2.draw(circle2);
	}

	private void displayTree(Graphics2D g2, HuffmanNode root, int x, int y, int gap){
		Color mint = new Color(162, 255, 204);
		Color random = mixRandomColorWith(mint);
		g2.setColor(random);
		Ellipse2D.Double node = new Ellipse2D.Double(x - RADIUS, y - RADIUS, 2*RADIUS, 2*RADIUS);
		g2.fill(node);
		g2.draw(node);
		if (root.getCharacter() != null) {
			g2.setColor(Color.white);
//			g2.drawString(root.toString(), x-RADIUS, y);
			printSimpleString(g2, " " + root.toString(), RADIUS*2, x-RADIUS, y+4);
		}
		if (root.isLeaf()) {
			g2.setColor(Color.black);
			printSimpleString(g2, " " + root.getBinaryCode(), RADIUS*2, x-RADIUS, y+2*RADIUS+3);
//			g2.drawString(root.getBinaryCode(), x-RADIUS, y+2*RADIUS);
		}
		
		if (root.left != null) {
			g2.setColor(Color.black);
			// Draw / line to left child
			// Draw the left subtree recursively
			displayTree(g2, root.right, x - gap, y + VGAP, gap / 2);
		}
		if (root.right != null) {
			// Draw \ line to right child
//			g2.setColor(Color.black);
			// Draw the right subtree recursively
			displayTree(g2, root.left, x + gap, y + VGAP, gap / 2);
		}
		
	}
	
//	private void parent(Graphics2D g2, HuffmanNode root, int x, int y, int gap){
//		
//	}

//	private void leftChild(Graphics2D g2, int x1, int y1, int x2, int y2) {
//
//	}

    private void printSimpleString(Graphics2D g2, String s, int width, int XPos, int YPos){  
        int stringLen = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();  
        int start = width/2 - stringLen/2;  
        g2.drawString(s, start + XPos, YPos);
        } 
	
	private Color mixRandomColorWith(Color mix) {
		Random random = new Random();
		int red = random.nextInt(256);
		int green = random.nextInt(256);
		int blue = random.nextInt(256);

		// mix the color
		if (mix != null) {
			red = (red + mix.getRed()) / 2;
			green = (green + mix.getGreen()) / 2;
			blue = (blue + mix.getBlue()) / 2;
		}

		Color color = new Color(red, green, blue);
		return color;
	}

	//
	// public void dfs() {
	// // DFS uses Stack data structure
	// MyStack stack = new MyStack();
	// stack.push(this.rootNode);
	// rootNode.visited=true;
	// printNode(rootNode);
	// while(!stack.isEmpty()) {
	// HuffmanNode node = (Node)s.peek();
	// Node child = getUnvisitedChildNode(n);
	// if(child != null) {
	// child.visited = true;
	// printNode(child);
	// s.push(child);
	// }
	// else {
	// s.pop();
	// }
	// }
	// // Clear visited property of nodes
	// clearNodes();
	// }
	// }

	public void printTree() {
		this.printTree(root);
	}

	private void printTree(HuffmanNode t) {
		double nodes = 0.0;
		double level = 0.0;

		Queue<HuffmanNode> q = new LinkedList<HuffmanNode>();
		q.add(t);

		// System.out.println("Added " + t.getCharacter() + " to queue");
		while (!q.isEmpty()) {
			HuffmanNode node = q.poll();
			// System.out.println("Removed " + node.getCharacter() +
			// " from queue");

			if (node.left != null && node.right != null) {
				// nodes += 2;
				q.add(node.left);
				q.add(node.right);
				System.out.print("/\\");
				// System.out.println("Added " + node.left.getCharacter() +
				// " and " + node.right.getCharacter() + " to queue");
			}
			// else if (node.isLeaf())
			// level--;

			// else if (node.left != null && node.right == null) { // draw /
			// // nodes++;
			// q.add(node.left);
			// // HuffmanNode blankr = new HuffmanNode("blankr");
			// // q.add(blankr);
			// System.out.print("/");
			// // System.out.println("Added " + node.left.getCharacter() +
			// " and blank to queue");
			// } else if (node.left == null && node.right != null) { // draw \
			// // nodes++;
			// // HuffmanNode blankl = new HuffmanNode("blankl");
			// // q.add(blankl);
			// q.add(node.right);
			// System.out.print("\\");
			// // System.out.println("Added blank and " +
			// node.right.getCharacter() + " to queue");
			// } else { // (node.left == null && node.right == null)
			// // nodes += (Math.pow(2.0, level) - nodes);
			// //System.out.println("Reached leaf. do nothing");
			// }

			System.out.print(node.toString() + "\t");
			nodes++; // node
			// System.out.println(Math.pow(2.0, level) +
			// " compared to number of nodes " + nodes);
			if (Math.pow(2.0, level) == nodes) {
				level += 1.0;
				nodes = 0;
				System.out.println("\n");
				// System.out.println("increased level by 1");
			}
			// else {
			// nodes += (Math.pow(2.0, level) - nodes);
			// // System.out.println("increased nodes by 1");
			// }

			/*
			 * 
			 * /\ t5 /\/\ t4 t3 /\ t2 e a s /\ sp t1 t nl
			 */

		}
	}
}
