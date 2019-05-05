package my;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyFrame2 extends JFrame
{
	public MyFrame2(String title) {
		super(title);
		
		//内容面板
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		ColorfulLabel a1 = new ColorfulLabel("1",Color.BLACK);
		ColorfulLabel a2 = new ColorfulLabel("2",Color.RED);
		ColorfulLabel a3 = new ColorfulLabel("3",Color.YELLOW);
		ColorfulLabel a4 = new ColorfulLabel("4",Color.WHITE);
		
		contentPane.add(a1);
		contentPane.add(a2);
		contentPane.add(a3);
		contentPane.add(a4);
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
