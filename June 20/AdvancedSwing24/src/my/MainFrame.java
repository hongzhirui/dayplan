package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{
	public MainFrame(String title){
		super(title);

		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		ImageBox imageBox = new ImageBox();
		root.add(imageBox, BorderLayout.CENTER);
		
		
	}
}
