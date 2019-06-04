package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;

import hzr.swing.HzrLabel;
import hzr.swing.HzrPopupMouseGrabber;

public class PopupContentPanel extends JPanel{
	public Popup popup;
	JLabel titleLabel = new JLabel();
	JTextPane content = new JTextPane();
	
	public PopupContentPanel() {
		this.setLayout( new BorderLayout());
		this.setOpaque(true);
		this.setBackground(new Color(0xFFFFE1));
		
		titleLabel.setText("全职罚时");
		titleLabel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		add(titleLabel, BorderLayout.PAGE_START);
		
		content.setText("按不出的风格爱的大幅的法文为 分 方式打破i好办法是法国色去监控链接哦了看就离开家离开家简历烤鸡肉飞机十六日司法会计公司开发");
		this.add(content, BorderLayout.CENTER);
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(300,200);
	}
	
	//实现时只需要添加这个方法就行
	public void showPopup(Component owner, int x, int y) {
		Point pt = new Point(x,y);
		SwingUtilities.convertPointToScreen(pt, owner);
		
		PopupFactory factory = PopupFactory.getSharedInstance();
		this.popup = factory.getPopup(owner, this, pt.x, pt.y);
		
		// 添加监控 （grabber内部已经实现“当点击在popup窗口之外时自动关闭popup”的逻辑)
		HzrPopupMouseGrabber grabber = new HzrPopupMouseGrabber(owner,this,popup);
		grabber.installListeners();
		
		// 显示弹出式窗口
		popup.show();
	}
	
}