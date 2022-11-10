import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JFrameToImage {

	public static void main(String[] args) throws IOException {
		Component c = new JPanel(); // the component you would like to print to a BufferedImage
		
		File outputFile = new File("image.jpg");
		RenderedImage rendImage = getImage(c);
		boolean b = ImageIO.write(rendImage, "PNG", outputFile);
		System.out.println(b);
	}

	public  static BufferedImage getImage(Component c) {
		JFrame frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setUndecorated(true);
		frame.getContentPane().add(c);
		frame.pack();
		BufferedImage bi = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = bi.createGraphics();
		c.print(graphics);
		graphics.dispose();
		frame.dispose();
		return bi;
	}

}
