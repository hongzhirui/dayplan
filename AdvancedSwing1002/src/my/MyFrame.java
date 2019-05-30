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
	JLabel display = new JLabel("--:--:--");
	
	Timer timer;
	
	public MyFrame(String title)
	{
		super(title);

		// Content Pane
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());	
		
		root.add(display, BorderLayout.CENTER);
				
		display.setFont(new Font("宋体", Font.PLAIN, 80));
		display.setHorizontalAlignment(SwingConstants.CENTER);
		display.setOpaque(true);
		display.setBackground(Color.WHITE);
		display.setForeground(Color.BLUE);
		
		// 工具栏
		Box toolbar = Box.createHorizontalBox();
		JButton startButton = new JButton("开始");
		JButton stopButton = new JButton("停止");
		toolbar.add(startButton);
		toolbar.add(Box.createHorizontalStrut(10));
		toolbar.add(stopButton);
		root.add(toolbar, BorderLayout.PAGE_START);
		
		startButton.addActionListener( (e)->{
			onStart();
		});
		stopButton.addActionListener( (e)->{
			onStop();
		});
	}

	private void onStart()
	{
		if(timer != null) return;

		// 创建定时器: 每隔1000毫秒执行一次任务
		ActionListener task = new UpdateTask();
		timer = new Timer(1000, task);
		timer.start();
	}
	
	private void onStop()
	{
		if(timer != null)
		{
			timer.stop();
			timer = null;
		}
		display.setText("--:--:--");
	}

	private class UpdateTask implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// 1 actionPerformed() 在界面线程里运行，所以必须迅速返回
			// 2   在这里可以直接更新UI
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String timestr = sdf.format(System.currentTimeMillis());
			display.setText( timestr );
		}		
	}
	
	
}
