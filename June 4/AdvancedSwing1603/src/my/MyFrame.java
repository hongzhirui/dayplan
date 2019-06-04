package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import hzr.swing.HzrLabel;

public class MyFrame extends JFrame
{
	JButton testButton = new JButton("测试");
	public MyFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());
		
		root.add(testButton);
		testButton.addActionListener( (e) -> {
			showPopup();
		});
	}
	
	private void showPopup() {
		//按钮左下角
		Point pt = new Point(0, testButton.getHeight());
		//转换成屏幕坐标
		SwingUtilities.convertPointToScreen(pt, testButton);
		
		//显示弹出式窗口
		PopupContentPanel contents = new PopupContentPanel();
		PopupFactory factory = PopupFactory.getSharedInstance();
		Popup popup = factory.getPopup(testButton, contents, pt.x, pt.y);
		contents.popup = popup; //把popup传给内容面板
		popup.show();
	}

}
