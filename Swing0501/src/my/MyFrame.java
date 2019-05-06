package my;

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
		
		//流布局FlowLayout
		LayoutManager layout = new FlowLayout(FlowLayout.LEFT);
		contentPane.setLayout(layout);
		
		
		ColorfulLabel a1 = new ColorfulLabel("1",Color.ORANGE);
		ColorfulLabel a2 = new ColorfulLabel("2",Color.RED);
		ColorfulLabel a3 = new ColorfulLabel("3",Color.YELLOW);
		ColorfulLabel a4 = new ColorfulLabel("4",Color.GREEN);
		ColorfulLabel a5 = new ColorfulLabel("5",Color.WHITE);
		
		contentPane.add(a1);
		contentPane.add(a2);
		contentPane.add(a3);
		contentPane.add(a4);
		contentPane.add(a5);
		
		//可以设置宽度和高度
//		a4.setPreferredSize(new Dimension(50, 30));
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
