package hzr.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class HzrToaster extends JPanel
{
	//警告级别
	public enum Level{ INFO, WARN, ERROR};
	
	//背景色
	public static Color[] bgColors = {
			new Color(60, 60, 60, 200),
			Color.ORANGE,
			Color.RED
	};
	
	//文字颜色
	public static Color[] textColors = {
			new Color(0xF4F4F4),
			new Color(0x333333),
			new Color(0xF4F4F4)
	};

	//使用HzrLabel进行文本测算
	private HzrLabel content = new HzrLabel();
	private Timer timer;
	private int delay = 1500;
	
	//消息窗口(使用JWindow实现)
	JWindow popup;
	
	public HzrToaster() {
		//设置HzrLabel
		content.setWrappingWidth(240);
		content.setOpaque(true);
		content.setBackground(new Color(60, 60, 60, 200));
		content.setForeground(new Color(0xF4F4F4));
		content.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);
	}
	
	//设置消息
	public void setMessage(String message) {
		content.setText(message);
	}
	
	//设置消息级别
	public void setLevel(Level level) {
		if(level == level.INFO) {
			content.setBackground(bgColors[0]);
			content.setForeground(textColors[0]);
		}
		else if(level == level.WARN) {
			content.setBackground(bgColors[1]);
			content.setForeground(textColors[1]);
		}
		else if(level == level.ERROR) {
			content.setBackground(bgColors[2]);
			content.setForeground(textColors[2]);
		}
	}
	
	// 设置延时
	public void setDelay(int delay)
	{
		this.delay = delay;
	}
	
	//计算窗口所需的大小
	@Override
	public Dimension getPreferredSize() {
		Dimension size = content.getPreferredSize();
		return size;
	}
	
	//显示消息提示
	public void showPopup(Window ownerWindow) {
		Rectangle rect = ownerWindow.getBounds();
		Dimension size = this.getPreferredSize();
		int x = (int) ((int)rect.getCenterX()-size.getWidth()/2);
		int y = (int) ((int)rect.getCenterY()-size.getHeight()/2);
		
		//创建并显示消息窗口
		
		//创建JWindow,参数为owner窗口
		popup = new JWindow(ownerWindow);
		//设置ContentPane(固定用法)
		popup.getRootPane().setContentPane(this);
		popup.setSize(this.getPreferredSize());
		popup.setLocation(x, y);
		popup.setVisible(true);
		
		//启动定时，自动关闭
		timer = new Timer(100, new TimerHandler());
		timer.start();
	}
	
	//关闭消息提示
	public void hidePopup() {
		if(popup != null) {
			popup.setVisible(false);
			popup.dispose();//销毁窗口
			popup = null;
		}
	}
	
	private class TimerHandler implements ActionListener{
		//开始时间
		long startTime = System.currentTimeMillis();
		
		@Override
		public void actionPerformed(ActionEvent e) {
			long pass = System.currentTimeMillis() - startTime;
			int remain = delay - (int)pass;
			
			//已经结束
			if(remain <= 0) {
				timer.stop();
				hidePopup();
				return ;
			}
			
			//倒计时30秒
			if(remain <= 300) {
				float percent = remain / 300.0f;
				popup.setOpacity(percent);
			}
		}
	}
	
	////////////////工具方法 ///////////////
	
	public static void show(Window ownerWindow, String text){
		show(ownerWindow, Level.INFO, 1500, text);
	}

	public static void show(Window ownerWindow, Level level, String text){
		show(ownerWindow, level, 1500, text);
	}

	public static void show(Window ownerWindow, Level level, int delay, String text){
		HzrToaster toaster = new HzrToaster();
		toaster.setMessage(text);
		toaster.setLevel(level); // 消息级别
		toaster.setDelay(delay); // 延时
		toaster.showPopup( ownerWindow );		
	}


	// 第一个参数为 控件 Component
	public static void show(Component owner, String text){
		show(owner, Level.INFO, 1500, text);
	}

	public static void show(Component owner, Level level, String text){
		show(owner, level, 1500, text);
	}
	
	public static void show(Component owner, Level level, int delay, String text){
		Window ownerWindow = SwingUtilities.getWindowAncestor(owner);
		show(ownerWindow, level, delay, text);
	}
}
