package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import hzr.swing.HzrLabel;

public class MyFrame extends JFrame
{
	JButton testButton = new JButton("测试");
	public MyFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());
		
		root.add(testButton);
		testButton.addActionListener( (e)->{ 
			// 点击按钮时，显示popup窗口
			PopupContentPanel contents = new PopupContentPanel();
			contents.showPopup(testButton, 0, testButton.getHeight());
		});
	}

}
