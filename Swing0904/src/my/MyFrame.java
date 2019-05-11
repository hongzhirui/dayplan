package my;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		MyPanel p = new MyPanel();
		contentPane.add(p, BorderLayout.CENTER);
	}

}
