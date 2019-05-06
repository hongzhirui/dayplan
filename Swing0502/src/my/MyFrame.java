package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		Container contentPane = getContentPane();
		
		//边界布局BordeLayout
		contentPane.setLayout(new BorderLayout());
		
		
		ColorfulLabel a1 = new ColorfulLabel("1",Color.ORANGE);
		ColorfulLabel a2 = new ColorfulLabel("2",Color.RED);
		ColorfulLabel a3 = new ColorfulLabel("3",Color.YELLOW);
		ColorfulLabel a4 = new ColorfulLabel("4",Color.GREEN);
		ColorfulLabel a5 = new ColorfulLabel("5",Color.WHITE);
		
		contentPane.add(a1,BorderLayout.PAGE_START);//上边界
		contentPane.add(a2,BorderLayout.PAGE_END);//下边界
		contentPane.add(a3,BorderLayout.LINE_START);//左边界
		contentPane.add(a4,BorderLayout.LINE_END);//右边界
		contentPane.add(a5,BorderLayout.CENTER);//中间
		
		//上下边界：可以调整高度,宽度无论写啥都不会变
		a1.setPreferredSize(new Dimension(1000000,80));
		
		//左右边界，可以调整宽度
		a3.setPreferredSize(new Dimension(100,0));
	}
	
	private static class ColorfulLabel extends JLabel{
		public ColorfulLabel(String text, Color bgColor) {
			super(text);
			setOpaque(true);
			setBackground(bgColor);
			setPreferredSize(new Dimension(60,30));
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

}
