package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
/*
 *提示等待对话框
 *
 *无标题栏 setUndecorated(true)
 *
 *对话框里没有任何关闭按钮，所以用户不能手工关闭，只能等待操作结束
 */
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
public class WaitDialog extends JDialog
{
	public JLabel display = new JLabel("请等待");
	
	public WaitDialog(JFrame owner) {
		super(owner, "", true);
		
		//去掉标题栏
		this.setUndecorated(true);
		
		//布局
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		//背景
		root.setOpaque(true);
		root.setBackground(new Color(0xF4F4F4));
		root.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//文字提示
		display.setHorizontalAlignment(SwingConstants.CENTER);
		root.add(display, BorderLayout.CENTER);
		
		//设置大小
		this.setSize(200, 80);
	}
	
	public void exec() {
		//相对owner居中显示
		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width) / 2;
		int y = frmRect.y + (frmRect.height - height) / 2;
		this.setBounds(x, y, width, height);
		
		//显示窗口（阻塞，直接对话框窗口被关闭）
		this.setVisible(true);
	}

}
