package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		JPanel root = new GamePanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
	}
}
