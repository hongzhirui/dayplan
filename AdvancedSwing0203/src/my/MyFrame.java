package my;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		MyPanel root = new MyPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
	}

}
