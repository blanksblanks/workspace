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
		// After tree is built, build binary codes for the leaves
		String binaryCode = "";
		encodeLeaves(root, binaryCode);
		levels = root.getHeight(root) + 1;
		frameHeight = (10 * (levels) + 3) * RADIUS; // + 3 for padding
		frameWidth = 1; // 2^(N+1) + 1
		for (int power = 1; power <= levels; power++)
			frameWidth *= 2;
		frameWidth = (frameWidth + 30) * RADIUS; // + 30 for padding
		hGap = RADIUS + 1;
		for (int i = 1; i < levels-1; i++)
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
//			System.out.println("Inserted " + t.toString() + " with kids "
//					+ left.toString() + " and " + right.toString()
//					+ " at height " + t.getHeight(t) + " and "
//					+ t.getHeight(t.left) + " and " + t.getHeight(t.right));
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
		if (s == "") return "You forgot to enter text.";
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
			printSimpleString(g2, " " + root.toString(), RADIUS*2, x-RADIUS, y+4);
		}
		if (root.isLeaf()) {
			g2.setColor(Color.black);			
			printSimpleString(g2, " " + root.getBinaryCode(), RADIUS*2, x-RADIUS, y+2*RADIUS+3);
		}

		if (root.left != null && root.right != null) {
			g2.setColor(Color.black);
			// Draw / line to left child
			g2.drawLine(x, y + RADIUS, x + gap, y + VGAP - RADIUS);
			// Draw the left subtree recursively
			displayTree(g2, root.left, x + gap, y + VGAP, gap / 2);
			// Draw \ line to right child
			g2.drawLine(x, y + RADIUS, x - gap, y + VGAP - RADIUS);
			// Draw the right subtree recursively
			displayTree(g2, root.right, x - gap, y + VGAP, gap / 2);
		}
	}

    private void printSimpleString(Graphics2D g2, String s, int width, int x, int y){  
        int stringLen = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();  
        int start = width/2 - stringLen/2;  
        g2.drawString(s, start + x, y);
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
