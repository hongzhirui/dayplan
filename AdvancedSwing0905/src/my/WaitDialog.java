package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/* 提示等待对话框
 * 
 * 无标题栏 setUndecorated(true)
 * 
 * 对话框里没有任何关闭按钮，所以用户不能手工关闭，只能等待操作结束
 * 
 */
public class WaitDialog extends JDialog
{
	public JLabel display = new JLabel("请等待...");
	public JProgressBar pbar =  new JProgressBar(); // 进度条控件，用于显示进度
	
	public WaitDialog(JFrame owner)
	{
		super(owner, "", true);
		
		// 去掉标题栏
		this.setUndecorated(true);
		
		// 布局
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		// 背景
		root.setOpaque(true);
		root.setBackground(new Color(0xF4F4F4));
		
		Border b1 = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border b2 = BorderFactory.createEmptyBorder(4, 4, 4, 4);
		Border b3 = BorderFactory.createCompoundBorder(b1, b2);
		root.setBorder(b3);
		
		// 文字提示
		display.setHorizontalAlignment(SwingConstants.CENTER);
		root.add(display, BorderLayout.CENTER);
		
		// 进度条 (0-100)
		pbar.setMinimum(0);  // 最小值
		pbar.setMaximum(100); // 最大值
		pbar.setPreferredSize(new Dimension(0,20)); // 高度设为20
		root.add(pbar, BorderLayout.PAGE_END);
		
		// 设置大小
		this.setSize(200, 80);
		
	}

	// 设置进度 (0-100)
	public void setProgress(int n)
	{
		pbar.setValue(n);
	}
	
	public void exec()
	{
		// 相对owner居中显示
		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width) / 2;
		int y = frmRect.y + (frmRect.height - height) / 2;
		this.setBounds(x, y, width, height);

		// 显示窗口 ( 阻塞 ，直接对话框窗口被关闭)
		this.setVisible(true);
	}
}
