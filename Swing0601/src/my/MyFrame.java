package my;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		//创建一个Box，并设置为顶层容器(取代原有的顶层容器)
		Box root = Box.createVerticalBox();
		this.setContentPane(root);
		
		//设置边框
		Border border = BorderFactory.createLineBorder(Color.BLUE,4);
		root.setBorder(border);
		
		root.add(new JButton("按钮一"));
		root.add(Box.createVerticalStrut(10));
		root.add(new JButton("按钮二"));
		root.add(Box.createVerticalStrut(10));
		root.add(new JButton("按钮三"));
		
//		JButton button = new JButton("Hello World");
//		button.setBorder(BorderFactory.createLineBorder(Color.RED));
//		root.add(Box.createVerticalStrut(10));
//		root.add(button);
		
		//复合边框
		JPanel panel = new JPanel();
		root.add(panel);
		panel.add(new JLabel("haha"));
		Border greenBorder = BorderFactory.createLineBorder(Color.GREEN,8);
		Border redBorder = BorderFactory.createLineBorder(Color.RED,6);
		Border blueBorder = BorderFactory.createLineBorder(Color.BLUE,4);
		Border compound = BorderFactory.createCompoundBorder(greenBorder, redBorder);
		compound = BorderFactory.createCompoundBorder(compound, blueBorder);
		panel.setBorder(compound);
	}
}
