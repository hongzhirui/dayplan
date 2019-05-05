package my;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		//内容面板
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		JLabel a1 = new JLabel("1");
		//设置背景为不透明，默认透明
		a1.setOpaque(true);
		//设置背景颜色
		a1.setBackground(Color.RED);
		//设置最佳尺寸
		a1.setPreferredSize(new Dimension(60,30));
		//设置水平对齐,居中
		a1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel a2 = new JLabel("2");
		//设置背景为不透明，默认透明
		a2.setOpaque(true);
		//设置背景颜色
		a2.setBackground(Color.YELLOW);
		//设置最佳尺寸
		a2.setPreferredSize(new Dimension(60,30));
		//设置水平对齐
		a2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel a3 = new JLabel("3");
		//设置背景为不透明，默认透明
		a3.setOpaque(true);
		//设置背景颜色
		a3.setBackground(Color.WHITE);
		//设置最佳尺寸
		a3.setPreferredSize(new Dimension(60,30));
		//设置水平对齐
		a3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel a4 = new JLabel("4");
		//设置背景为不透明，默认透明
		a4.setOpaque(true);
		//设置背景颜色
		a4.setBackground(Color.BLACK);
		//设置最佳尺寸
		a4.setPreferredSize(new Dimension(60,30));
		//设置水平对齐
		a4.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPane.add(a1);
		contentPane.add(a2);
		contentPane.add(a3);
		contentPane.add(a4);
	}
}
