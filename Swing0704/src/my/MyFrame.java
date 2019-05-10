package my;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import af.swing.layout.AfRowLayout;

public class MyFrame extends JFrame
{
	JLabel a1 = new ColorfulLabel("hello,world",Color.YELLOW);
	JLabel a2 = new ColorfulLabel("样例文本",Color.blue);
	JLabel a3 = new ColorfulLabel("Good Boy",Color.cyan);
	JLabel a4 = new ColorfulLabel("占满剩余",Color.red);
	
	public MyFrame(String title) {
		super(title);
		
		//使用自定义布局器
		JPanel root = new JPanel();
		this.setContentPane(root);
		
		//第一个参数表示间隔，第二个参数表示竖直方向是否占满,true表示不占满
		root.setLayout(new AfRowLayout(2, flase));
		
		Border border = BorderFactory.createLineBorder(Color.GREEN,6);
		root.setBorder(border);
		
		root.add(a1,"100");//100,或100px，表示恒定100像素
		root.add(a2,"30%");//百分比
		root.add(a3,"auto");//按Preferred Size，自动大小
		root.add(a4,"1w");//权重为1，扣除所有已经宽度，剩下的空间按比击划分
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
