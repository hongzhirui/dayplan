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

import javax.swing.BorderFactory;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import af.swing.AfPanel;
import af.swing.border.AfBorder;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;


public class MyFrame extends JFrame
{

	//为了方便布局，提供3个类
	//AfRowLayout:横向布局器
	//AfColumnLayout:纵向布局器
	//AfPanel:一个用于快速布局的类，继承于JPanel
	public MyFrame(String title)
	{
		super(title);
		
		AfPanel root = new AfPanel().layout(new AfColumnLayout(10));
		this.setContentPane(root);
		root.padding(10);
			
		AfPanel row1 = new AfPanel().layout(new AfRowLayout(10));
		row1.padding(5).perferredHeight(40);
		JTextField nameField = new JTextField(20);
		row1.add(new JLabel("姓名"), "80px");		
		row1.add(nameField, "1w");
		
		AfPanel row2 = new AfPanel().layout(new AfRowLayout(10));
		row2.padding(5).perferredHeight(40);
		JTextField phoneField = new JTextField(20);
		row2.add(new JLabel("手机号"), "80px");			
		row2.add(phoneField, "1w");
		
		AfPanel row3 = new AfPanel().layout(new AfRowLayout(10));
		row3.padding(5).perferredHeight(40);
		JComboBox<String> sexField = new JComboBox<>();
		sexField.addItem("男");
		sexField.addItem("女");
		row3.add(new JLabel("性别"), "80px");			
		row3.add(sexField, "1w");
		
		root.add(row1);
		root.add(row2);
		root.add(row3);
		
		
	}
	
	
	
	
}
