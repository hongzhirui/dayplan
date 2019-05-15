package my;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		//Content Pane
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());
		
		JTextField textField = new JTextField(20);
		JButton button = new JButton("测试");
		root.add(textField);
		root.add(button);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				test4();
			}
			
		});
	}
	
	//简单消息提示框
	private void test1() {
		JOptionPane.showMessageDialog(this, "操作已经完成");
		System.out.println("test1 exit");
	}
	
	//简单确认输入框
	private void test2() {
		int select = JOptionPane.showConfirmDialog(this, "是否确认删除?");
		
	}
	
	//简单数据输入框
	private void test3() {
		String input = JOptionPane.showInputDialog(this, "请输入你的身份证号\n（字母以X代替）", "0000");
		if(input != null) {
			System.out.println("输入的号码为："+input);
		}
		System.out.println("test3 exit");
	}
	
	//简单选项对话框
	private void test4() {
		Object[] colors = {"红色", "绿色", "蓝色"};
		String select = (String)JOptionPane.showInputDialog(this,"你最喜欢的颜色", "请选择", JOptionPane.PLAIN_MESSAGE, null, colors, "红色");
		
		if(select != null) {
			System.out.println("选择了："+select);
		}
		System.out.println("test4 exit");
	}
}
