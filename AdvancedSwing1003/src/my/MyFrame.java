package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class MyFrame extends JFrame
{
	CustomClock clock = new CustomClock();
	
	Timer timer;
	
	public MyFrame(String title)
	{
		super(title);

		// Content Pane
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());	
		
		root.add(clock, BorderLayout.CENTER);
			
				
		// 工具栏
		Box toolbar = Box.createHorizontalBox();
		JButton startButton = new JButton("开始");
		JButton stopButton = new JButton("停止");
		toolbar.add(startButton);
		toolbar.add(Box.createHorizontalStrut(10));
		toolbar.add(stopButton);
		root.add(toolbar, BorderLayout.PAGE_START);
		
		startButton.addActionListener( (e)->{
			startClock();
		});
		stopButton.addActionListener( (e)->{
			stopClock();
		});
	}

	private void startClock()
	{
		if(timer != null) return;

		// 创建定时器: 每隔1000毫秒执行一次任务
		// 第1个参数 delay
		// 第2个参数 ActionListener，可以用匿名类，或Lambda表达式
		timer = new Timer(1000, (e)->{
			clock.repaint(); // 重绘时钟
		} );
		
		timer.start();
	}
	
	private void stopClock()
	{
		if(timer != null)
		{
			timer.stop();
			timer = null;
		}
	}	
	
}
