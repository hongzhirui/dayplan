package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

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
		
		timer = new Timer();
		
		// 定时执行 task任务，首次执行在50ms之后，以后每次间隔1000ms
		// timer: 每次执行的任务
		// delay: 50ms, 首次运行的时间延迟
		// interval: 1000ms, 后面每次运行的时间间隔
		timer.schedule(new UpdateTask(), 50, 1000);
	}
	
	private void onStop()
	{
		if(timer != null)
		{
			timer.cancel();
			timer = null;
		}
		display.setText("--:--:--");
	}
	
	// 创建一个TimerTask的子类
	private class UpdateTask extends TimerTask
	{
		@Override
		public void run()
		{
			// 这个也是工作线程，所以不能直接更新界面
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String timestr = sdf.format(System.currentTimeMillis());
			
			// 更新UI
			SwingUtilities.invokeLater( ()->{
				display.setText( timestr );
			});
		}		
	}
	
	
}
