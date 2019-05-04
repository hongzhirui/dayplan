package my;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame extends JFrame
{
	JLabel timeLabel = new JLabel("00:00:00");
	JButton button = new JButton("显示时间");
	
	public MyFrame(String title) {
		
		super(title);
		
		//内容面板（ContentPane）
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		//向内容面板里添加控件
		contentPane.add(button);
		contentPane.add(timeLabel);
		
		//创建监听对象
		MyButtonListener listener = new MyButtonListener();
		
		//把监听器注册给按钮
		button.addActionListener(listener);
	}
	
	//更新时间
	public void showTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timestr = sdf.format(new Date());
		timeLabel.setText(timestr);
	}
	
	//ActionListener是一个interface
	private class MyButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			//当按钮被点击时，Swing框架会调用监听器的actionPerformed()方法
			MyFrame.this.showTime();
		}
		
	}
}
