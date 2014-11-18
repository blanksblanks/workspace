//import javax.swing.*;
//import java.awt.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
//import java.awt.geom.Line2D;
//import javax.swing.JPanel;
import javax.swing.JComponent;
import java.util.Random;

@SuppressWarnings("serial")
public class HuffmanTreeComponent extends JComponent {

    public HuffmanTreeComponent() {
		setPreferredSize(new Dimension(500,500));
    }

    public void paintComponent(Graphics g) {
    	
		// Use a cast to recover the Graphics2D object from the Graphics param
		Graphics2D g2 = (Graphics2D) g;

		int x = 5;
		int y = 10;
		int d = 20;
		
		g2.setColor(Color.BLACK);
		g2.drawLine(15, 20, 45, 20);
    	
		Ellipse2D.Double circle = new Ellipse2D.Double(x, y, d, d);
		Color mint = new Color(162, 255, 204);
		Color random = mixRandomColorWith(mint); // white is color to be mixed with
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
    
    public Color mixRandomColorWith(Color mix) {
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
}