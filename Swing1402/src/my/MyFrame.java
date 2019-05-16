package my;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());
		
		JButton testButton = new JButton("测试");
		root.add(testButton);
		
		testButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String str = getUserInput();
				System.out.println("用户输入了："+str);
			}
			
		});
	}
	
	//创建对话框，显示，并获取用户输入
	private String getUserInput() {
		MyDialog dialog = new MyDialog(this);
		dialog.setSize(300,100);
		String input = dialog.exec();
		return input;
	}

}
