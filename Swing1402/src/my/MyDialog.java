package my;

import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyDialog extends JDialog
{
	public JTextField textField = new JTextField(20);
	public JButton okButton = new JButton("确定");
	
	public MyDialog(JFrame owner) {
		super(owner, "测试", true);
		
		//设置一个容器
		JPanel root = new JPanel();
		this.setContentPane(root);
		this.setLayout(new FlowLayout());
		
		//布局子控件
		root.add(textField);
		root.add(okButton);
		
		//此处使用Lambda表达式的写法
		okButton.addActionListener( (e)->{
			setVisible(false);
		});
		
	}
	
	//显示对话框，并等待对话框关闭，返回用户输入的结果
	public String exec() {
		//显示窗口（阻塞，直接对话框窗口被关闭）
		this.setVisible(true);
		
		String value = textField.getText();
		return value;
	}
}
