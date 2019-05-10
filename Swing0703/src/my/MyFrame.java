package my;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class MyFrame extends JFrame
{
	JLabel a1 = new ColorfulLabel("Hello,World",Color.yellow);
	JLabel a2 = new ColorfulLabel("样例文本",Color.blue);
	
	public MyFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new SimpleLayout());
		
		Border border = BorderFactory.createLineBorder(Color.GREEN,6);
		root.setBorder(border);
		
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
			Rectangle rect = parent.getBounds();
			System.out.println("父容器："+rect);
			
			//Insets类用于表示内边的宽度
			Insets insets = parent.getInsets();
			System.out.println("insets："+insets);
			
			rect.x += insets.left;
			rect.y += insets.top;
			rect.width -= (insets.left+insets.right);
			rect.height -= (insets.top+insets.bottom);
			
			if(a1.isVisible()) {
				Dimension size = a1.getPreferredSize();
				//System.out.println(size);
				int x = (rect.width - size.width) / 2;
				int y = (rect.height - size.height) / 2;
				a1.setBounds(rect.x+x,rect.y+y,size.width,size.height);
			}
			
			//a2显示在右上角
			if(a2.isVisible()) {
				Dimension size = a2.getPreferredSize();
				//System.out.println(size);
				int x = (rect.width - size.width);
				int y = 0;
				a2.setBounds(rect.x+x,rect.y+y,size.width,size.height);
			}
		}
		
	}
	
	private static class ColorfulLabel extends JLabel{
		public ColorfulLabel(String test, Color bgColor) {
			super(test);
			setOpaque(true);
			setBackground(bgColor);
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}
}
