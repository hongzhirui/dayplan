package hzr.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HzrMarginLayout implements LayoutManager2
{
	public static final int AUTO = -1; 
	public static final Margin TOP_LEFT = new Margin(0,0,AUTO,AUTO);
	public static final Margin TOP_CENTER = new Margin(0,AUTO,AUTO,AUTO);
	public static final Margin TOP_RIGHT = new Margin(0,AUTO,AUTO,0);
	public static final Margin CENTER_LEFT = new Margin(AUTO,0,AUTO,AUTO);
	public static final Margin CENTER = new Margin(AUTO,AUTO,AUTO,AUTO);
	public static final Margin CENTER_RIGHT = new Margin(AUTO,AUTO,AUTO,0);
	public static final Margin BOTTOM_LEFT = new Margin(AUTO,0,0,AUTO);
	public static final Margin BOTTOM_CENTER = new Margin(AUTO,AUTO,0,AUTO);
	public static final Margin BOTTOM_RIGHT = new Margin(AUTO,AUTO,0,0);

	
	List<Item> items = new ArrayList<>();
	
	@Override
	public void addLayoutComponent(String name, Component comp)
	{	
		Item item = new Item();
		item.comp = comp;
		item.margin = null; // null则占满空间
		items.add(item);	
	}
	@Override
	public void addLayoutComponent(Component comp, Object constraints)
	{
		Item item = new Item();
		item.comp = comp;
		item.margin = (Margin) constraints;
		items.add(item);		
	}	
	@Override
	public void removeLayoutComponent(Component comp)
	{
		Iterator<Item> iter = items.iterator();
		while(iter.hasNext())
		{
			Item item = iter.next();
			if(item.comp == comp)
			{
				iter.remove();
			}
		}
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		return null;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		return null;
	}
	@Override
	public Dimension maximumLayoutSize(Container target)
	{
		return null;
	}

	@Override
	public float getLayoutAlignmentX(Container target)
	{
		return 0;
	}

	@Override
	public float getLayoutAlignmentY(Container target)
	{
		return 0;
	}

	@Override
	public void invalidateLayout(Container target)
	{
	}
	
	
	
	@Override
	public void layoutContainer(Container parent)
	{
		Component[] children = parent.getComponents();
		for(Component child: children)
		{
			for(Item item : items )
			{
				if(item.comp == child)
				{
					layoutWithMargin(parent, item);
				}
			}
		}		
	}

	private void layoutWithMargin(Container parent, Item item)
	{
		int width = parent.getWidth();
		int height = parent.getHeight();
		Component comp = item.comp;
		Margin margin = item.margin;
		
		if(item.margin ==null)
		{
			comp.setBounds(0, 0, width, height);
			return;
		}
		
		Dimension size = comp.getPreferredSize();			
		
		int left = margin.left;
		int right = margin.right;
		int top = margin.top;
		int bottom = margin.bottom;
		
		// 如果左边是auto，则左边努力占据剩余空间; 右边是auto，则右边占据剩余空间
		if(size.width > width) size.width = width;
		if(left <0 && right<0)
		{
			left = ( (width - size.width)/2);
		}
		else 
		{
			if(left >=0 && right >=0)
			{
				size.width = width - left - right;
			}
			if(left <0 && right >=0)
			{
				if(size.width + right > width) 
					size.width = width - right;
				left = width - size.width - right;
			}
			if(left >=0 && right <0)
			{
				if(left + size.width > width)
					size.width = width - left;
			}
		}
		
		// 如果左边是auto，则左边努力占据剩余空间; 右边是auto，则右边占据剩余空间
		if(size.height > height) size.height = height;
		if(top <0 && bottom <0)
		{
			top = ( (height - size.height)/2);
		}
		else 
		{
			if(top >=0 && bottom >=0)
			{
				size.height = height - top - bottom;
			}
			if(top <0 && bottom >=0)
			{
				if(size.height + bottom > height) 
					size.height = height - bottom;
				top = height - size.height - bottom;
			}
			if(top >=0 && bottom <0)
			{
				if(top + size.height > height)
					size.height = height - top;
			}
		}	
		
		comp.setBounds(left, top, size.width, size.height);
	}

	public static class Margin
	{
		int top, left, bottom, right;
		
		// 与 Swing 的惯例相同： 上 左 下 右
		public Margin(int top, int left, int bottom, int right)
		{
			this.top = top;
			this.left = left;
			this.bottom = bottom;
			this.right = right;
		}
	}
	
	private static class Item
	{
		Component comp;
		Margin margin;
		
		public Item() {}
		
		public Item(Component comp, Margin margin)
		{
			this.comp = comp;
			this.margin = margin;
		}
	}

}
