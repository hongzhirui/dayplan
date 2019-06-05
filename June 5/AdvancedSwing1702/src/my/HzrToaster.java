package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.JPanel;
import javax.swing.JWindow;

import hzr.swing.HzrLabel;

public class HzrToaster extends JPanel
{
	//使用HzrLabel进行文本测算
	private HzrLabel content = new HzrLabel();
	
	//消息窗口（使用JWindow)
	JWindow popup;
	
	public HzrToaster() {
		content.setWrappingWidth(240);
		content.setOpaque(true);
		content.setBackground(new Color(60, 60, 60, 200));
		content.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		content.setForeground(new Color(0xF4F4F4));
		
		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);
	}
	
	//设置消息
	public void setMessage(String message) {
		content.setText(message);
	}
	
	//计算窗口所需的大小
	@Override
	public Dimension getPreferredSize() {
		Dimension size = content.getPreferredSize();
		return size;
	}
	
	//显示消息提示
	public void showPopup(Window ownerWindow) {
		//根据主窗口的位置计算，将消息显示在主窗口的中心位置
		Rectangle rect = ownerWindow.getBounds();
		Dimension size = this.getPreferredSize();
		int x = (int)rect.getCenterX() - size.width/2;
		int y = (int)rect.getCenterY() - size.height/2;
		
		/*创建并显示消息窗口*/
		
		//创建JWindow,参数为owner窗口
		popup = new JWindow(ownerWindow);
		//设置ContentPane(固定用法)
		popup.getRootPane().setContentPane(this);
		//设置窗口大小
		popup.setSize(this.getPreferredSize());
		//显示窗口位置
		popup.setLocation(x, y);
		//显示窗口
		popup.setVisible(true);
	}
	
	//关闭消息提示
	public void hidePopup() {
		if(popup != null) {
			popup.setVisible(false);//隐藏窗口
			popup.dispose();//销毁窗口
			popup = null;
		}
	}

}
