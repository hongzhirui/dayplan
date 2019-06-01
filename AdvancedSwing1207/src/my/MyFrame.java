package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		GamePanel root = new GamePanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		try {
			Image image = ImageIO.read(getClass().getResource("/icons/bg.jpg"));
			root.setBgImage(image);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
