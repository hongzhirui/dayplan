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
	
	public MyFrame(String title)
	{
		super(title);

		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());

		JButton testButton = new JButton ("测试");
		root.add(testButton);
		
		testButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				getUserInput();
			}
		});
	}

	// 创建对话框，显示，并获取用户输入
	private void getUserInput()
	{
		MyDialog dialog = new MyDialog(this);
		dialog.setSize(350,200);
		
		//MyDialog.exec()的返回值：表示用户是点了“确定”还是“取消”
		if(dialog.exec()) {
			System.out.println("用户点了确定");
			String text = dialog.getValue();
			System.out.println("用户输入的值："+text);
		}
	}
}
