package my;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class MyFrame extends JFrame
{

	public MyFrame(String title)
	{
		super(title);
		
		// 创建一个Box，并设置为顶层容器 （取代原有的顶层容器)
		Box root = Box.createVerticalBox();
		this.setContentPane(root);
		
		// 第1行，标题
		Box line1 = Box.createHorizontalBox();
		JLabel labelTitle = new JLabel("用户登录");
		labelTitle.setFont(new Font("宋体", Font.BOLD, 20));
		line1.add(labelTitle);

		// 第2行
		Box line2 = Box.createHorizontalBox();
		JLabel userNameLabel = new JLabel("用户名");
		userNameLabel.setPreferredSize(new Dimension(50, 0));
		JTextField userNameField = new JTextField(20);
		userNameField.setPreferredSize(new Dimension(50, 30));
		line2.add(userNameLabel);
		line2.add(userNameField);
		line2.setMaximumSize(new Dimension(9999,0)); // 水平占满
		
		// 第3行
		Box line3 = Box.createHorizontalBox();
		JLabel passwordLabel = new JLabel("密码");
		passwordLabel.setPreferredSize(new Dimension(50, 0));
		JPasswordField passwordField = new JPasswordField(20);
		passwordField.setPreferredSize(new Dimension(50, 30));
		line3.add(passwordLabel);
		line3.add(passwordField);
		line3.setMaximumSize(new Dimension(9999,0)); // 水平占满
		
		// 第4行
		Box line4 = Box.createHorizontalBox();
		JButton buttonLogin = new JButton("登录");
		JButton buttonRegister = new JButton("注册");
		line4.add(Box.createHorizontalGlue());
		line4.add(buttonLogin );
		line4.add(Box.createHorizontalStrut(10));
		line4.add(buttonRegister );
		line4.add(Box.createHorizontalGlue());
		line4.setMaximumSize(new Dimension(9999,0)); // 水平占满

		
		root.add(Box.createVerticalStrut(20)); // 间距
		root.add( line1);
		root.add(Box.createVerticalStrut(20)); // 间距
		root.add( line2);
		root.add(Box.createVerticalStrut(10)); // 间距
		root.add( line3);
		root.add(Box.createVerticalStrut(20)); // 间距
		root.add( line4);
		root.add(Box.createVerticalGlue()); // 占据剩下的空间
	}
	
	
	
	
}
