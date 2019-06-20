package my;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{
	public MainFrame(String title)
	{
		super(title);

		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());
		
		// 添加一个 JLabel 标签 
		root.add(new MyLabel("hongzhirui.cn", Color.YELLOW));
		root.add(new MyLabel("hongzhirui.com", Color.CYAN));
	}
}
