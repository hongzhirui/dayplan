package my;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyFrame extends JFrame
{
	JLabel a1 = new ColorfulLabel("Hello,World",Color.yellow);
	JLabel a2 = new ColorfulLabel("样例文本",Color.blue);
	
	public MyFrame(String title) {
		super(title);
		
		//使用自定义布局器
		Container root = this.getContentPane();
		root.setLayout(new SimpleLayout());
		
		root.add(a1);
		root.add(a2);
	}
	
	private class SimpleLayout implements LayoutManager{

		@Override
		public void addLayoutComponent(String name, Component comp)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeLayoutComponent(Component comp)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public Dimension preferredLayoutSize(Container parent)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Dimension minimumLayoutSize(Container parent)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void layoutContainer(Container parent)
		{
			//得到父容器地大小
			Rectangle rect = parent.getBounds();
			
			//a1显示在中间，以Preferred Size作为大小
			if(a1.isVisible()) {
				Dimension size = a1.getPreferredSize();
				//System.out.println(size);
				int x = (rect.width - size.width) / 2;
				int y = (rect.height - size.height) / 2;
				a1.setBounds(x,y,size.width,size.height);
			}
			
			//a2显示在右上角
			if(a2.isVisible()) {
				Dimension size = a2.getPreferredSize();
				//System.out.println(size);
				int x = (rect.width - size.width);
				int y = 0;
				a2.setBounds(x,y,size.width,size.height);
			}
		}
		
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
