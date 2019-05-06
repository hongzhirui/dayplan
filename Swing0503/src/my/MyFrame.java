package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		Container contentPane = getContentPane();
		
		//盒布局
		LayoutManager layout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
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
		
		//通过setMaximumSize()改变长度和宽度
		a1.setMaximumSize(new Dimension(100, 40));
		a2.setMaximumSize(new Dimension(200,50));
		a3.setMaximumSize(new Dimension(300,60));
		a4.setMaximumSize(new Dimension(400,70));
		a5.setMaximumSize(new Dimension(500,80));
	}
	
	private static class ColorfulLabel extends JLabel{
		public ColorfulLabel(String text, Color bgColor) {
			super(text);
			setOpaque(true);
			setBackground(bgColor);
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

}
