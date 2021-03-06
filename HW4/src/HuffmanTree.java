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
	final int HGAP = 350; // perfect width for my laptop
	final int VGAP = 60;
	private int hGap;
	private int levels;
	private int frameHeight;
	private int frameWidth;

	public HuffmanTree(HuffmanNode[] array) throws UnderflowException {
		BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>(array);
		buildHuffmanTree(heap);
		// After tree is built, build binary codes for the leaves
		String binaryCode = "";
		encodeLeaves(root, binaryCode);
		
		// Set up dimensions of the component in the constructor method
		levels = root.getHeight(root) + 1;
		hGap = RADIUS;
		for (int i = 1; i < levels-1; i++)// double levels - 1 times
			hGap *= 2;
		frameWidth = RADIUS;
		for (int i = 0; i < levels; i++)
			frameWidth *= 2;
		frameHeight = (VGAP + RADIUS) * levels + 10; // + 10 for padding
		// System.out.println("Window height " + frameHeight + " and "
		// + frameWidth + " and horizontal gap " + hGap);
		// frameHeight = (10 * (levels) + 3) * RADIUS;g
		// frameWidth = 1; // 2^(N+1) + 1
		// for (int power = 1; power <= levels; power++)
		// frameWidth *= 2;
		// frameWidth = (frameWidth + 30) * RADIUS;
		setPreferredSize(new Dimension(frameWidth, frameHeight));
	}

	private void buildHuffmanTree(BinaryHeap<HuffmanNode> heap)
			throws UnderflowException {
		hash = new Hashtable<String, String>();
		int num = 1; // initialize at T1
		while (heap.getCurrentSize() > 1) { // merge two smallest trees
			HuffmanNode left = heap.deleteMin();
			HuffmanNode right = heap.deleteMin();
			HuffmanNode t = new HuffmanNode(num++, left, right);
			int h = Math.max(t.getHeight(t.left), t.getHeight(t.right)) + 1;
			t.setHeight(h);
			t.fixHeight();
//			System.out.println("Inserted " + t.toString() + " with kids "
//					+ left.toString() + " (" + left.getFrequency()
//					+ ") and " + right.toString() + " ("
//					+ right.getFrequency() + ") at heights " + t.getHeight(t)
//					+ " and " + t.getHeight(t.left) + " and "
//					+ t.getHeight(t.right));
			heap.insert(t); // throw it back in the heap
			root = t; // last node is the root of the tree
		}
	}

	// TODO: edge cases: what happens if t is null or only one letter in tree?
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
	
	public String encode(String s) {
		if (s == null)
			return "You forgot to enter text in the text field.";
		String binary = "";
		for (int i = 0; i < s.length(); i++) {
			String character = Character.toString(s.charAt(i));
			String code = hash.get(character);
			if (code == null)
				return ("That was not a valid string as " + character + " is not in the Huffman tree.");
			else
				binary += code;
		}
		return binary;
	}

	public String decode(String binary) {
		String text = "";
		String error = "That was not a valid binary encoding.";
		try {
			if (!binary.matches("[01]+"))
				return error;
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
	
	/* Header Methods for Exercise 10.4 */
	
	// root of Huffman Tree is passed in as Huffman Node t
	public void getHeader(){
		getHeader(root);
	}

	private void getHeader(HuffmanNode t) {
		if (t != null) {
			// if node is a leaf, print 1 and corresponding character
			if (t.left == null && t.right == null)
				System.out.print("1" + t.toString());
			// else, print 0 to indicate cont'd traversal through tree
			else { // and recursively call method on children of t
				System.out.print("0");	
				getHeader(t.left);
				getHeader(t.right);
			}
		}
	}

	
	@SuppressWarnings("unused")
	private void getHead(HuffmanNode t){
		if (t!= null) {
			// if node is a leaf, print frequency and corresponding character
			if (t.left == null && t.right == null)
				System.out.print(t.getFrequency() + t.toString());
			else { // else, print nothing
				getHead(t.left);
				getHead(t.right);
			}
		}
	}

	/* GUI-Related Methods */
	
	public void paintComponent(Graphics g) {
		// Use a cast to recover the Graphics2D object from the Graphics param
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("Impact", Font.PLAIN, 12));
		displayTree(g2, root, (frameWidth - 1) / 2, RADIUS * 2, hGap);
	}
	
//	public int getFrameWidth(){
//		return frameWidth;
//	}
//	
//	public int getMidX(){
//		return (frameWidth - 1) / 2;
//	}

	private void displayTree(Graphics2D g2, HuffmanNode root, int x, int y, int gap) {
		// Generate random color, draw parent node and label
		Color mint = new Color(162, 255, 204);
		Color random = mixRandomColorWith(mint);
		g2.setColor(random);
		Ellipse2D.Double node = new Ellipse2D.Double(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
		g2.fill(node);
		g2.draw(node);
		g2.setColor(Color.white);
		drawCenteredString(g2, " " + root.toString(), RADIUS * 2, x - RADIUS, y + 4);

		// If node is a leaf, include binary code below the node
		if (root.isLeaf()) {
			g2.setColor(Color.black);
			drawVerticalString(g2, root.getBinaryCode(), RADIUS * 2, x
					- RADIUS, y + RADIUS);
		} else { // if (root.left != null && root.right != null)
			g2.setColor(Color.black);
			// Draw / line to left child
			g2.drawLine(x, y + RADIUS, x - gap, y + VGAP - RADIUS);
			// Draw left subtree recursively
			displayTree(g2, root.left, x - gap, y + VGAP, gap / 2);
			// Draw \ line to right child
			g2.drawLine(x, y + RADIUS, x + gap, y + VGAP - RADIUS);
			// Draw right subtree recursively
			displayTree(g2, root.right, x + gap, y + VGAP, gap / 2);
		}
	}

	private void drawCenteredString(Graphics2D g2, String s, int width, int x, int y) {
		int stringLen = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
		int start = width / 2 - stringLen / 2;
		g2.drawString(s, start + x, y);
	}

	private void drawVerticalString(Graphics2D g2, String s, int width, int x, int y) {
//		String text = "";
		for (int i = 0; i < s.length(); i++)
//			text += s.charAt(i) + "\n";
//		for (String line : text.split("\n"))
			drawCenteredString(g2, Character.toString(s.charAt(i)), width, x, y += g2.getFontMetrics()
					.getHeight());
	}

	private Color mixRandomColorWith(Color mix) {
		Random random = new Random();
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		// mix the color
		if (mix != null) {
			r = (r + mix.getRed()) / 2;
			g = (g + mix.getGreen()) / 2;
			b = (b + mix.getBlue()) / 2;
		}
		Color color = new Color(r, g, b);
		return color;
	}

}
