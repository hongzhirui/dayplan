package my;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		Box root = Box.createVerticalBox();
		this.setContentPane(root);
		
		//带标题的边框
		root.setBorder(BorderFactory.createTitledBorder("学生信息"));
		addMargin(root,10,10,10,10);
		
		//第一行
		Box row1 = Box.createHorizontalBox();
		addMargin(row1,10,10,10,10);
		JLabel nameLabel = new JLabel("姓名");
		JTextField nameField = new JTextField(20);
		row1.add(nameLabel);
		row1.add(Box.createHorizontalStrut(10));
		row1.add(nameField);
		row1.setMaximumSize(new Dimension(9999, 50));
		
		//第二行
		Box row2 = Box.createHorizontalBox();
		addMargin(row2,10,10,10,10);
		JLabel phoneLabel = new JLabel("手机");
		JTextField phoneField = new JTextField(20);
		row2.add(phoneLabel);
		row2.add(Box.createHorizontalStrut(10));
		row2.add(phoneField);
		row2.setMaximumSize(new Dimension(9999, 50));
		
		//第三行
		Box row3 = Box.createHorizontalBox();
		addMargin(row3,10,10,10,10);
		JLabel sexLabel = new JLabel("性别");
		JComboBox<String> sexField = new JComboBox<>();
		sexField.addItem("男");
		sexField.addItem("女");
		row3.add(sexLabel);
		row3.add(Box.createHorizontalStrut(10));
		row3.add(sexField);
		row3.setMaximumSize(new Dimension(9999,50));
		
		root.add(row1);
		root.add(row2);
		root.add(row3);
	}

	public static void addMargin(JComponent c, int top, int left, int bottom, int right) {
		Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
		Border oldBorder = c.getBorder();
		if(oldBorder != null) {
			border = BorderFactory.createCompoundBorder(border, oldBorder);
		}
		c.setBorder(border);
	}
}
