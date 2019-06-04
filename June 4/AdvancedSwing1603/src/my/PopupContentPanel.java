package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;

import hzr.swing.HzrLabel;

public class PopupContentPanel extends JPanel{
	public Popup popup;
	JLabel titleLabel = new JLabel();
	HzrLabel content = new HzrLabel();
	
	public PopupContentPanel() {
		this.setLayout( new BorderLayout());
		this.setOpaque(true);
		this.setBackground(new Color(0xFFFFE1));
		
		titleLabel.setText("全职罚时");
		titleLabel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		add(titleLabel, BorderLayout.PAGE_START);
		
		content.setWrappingWidth(240);
		content.setText("按不出的风格爱的大幅的法文为 分 方式打破i好办法是法国色去监控链接哦了看就离开家离开家简历烤鸡肉飞机十六日司法会计公司开发");
		
		this.add(content, BorderLayout.CENTER);
		JButton closeButton = new JButton("关闭");
		closeButton.addActionListener( (e) -> {
			popup.hide();//关闭popup
		});
		JPanel bottom = new JPanel();
		bottom.add(closeButton);
		bottom.setOpaque(true);
		bottom.setBackground(new Color(0,0,0,0));
//		add(closeButton, BorderLayout.PAGE_END);
	}
	
	@Override
	public Dimension getPreferredSize() {
		Dimension size1 = titleLabel.getPreferredSize();
		Dimension size2 = content.getPreferredSize();//自动自动测算多行文本的宽高
		int width = size2.width;
		int height = size1.height + size2.height + 4;
		return new Dimension(width, height);
	}
	
}