package hzr.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.swing.JPanel;

/*
 * 前往新页面 startActivity() : 
 *   1 finish()自身 
 *   2 不finish()自身，则维持一个调用栈
 */

public class HzrActivityPane extends JPanel
{
	List<HzrActivity> stack = new ArrayList<>(); // 返回栈
	Map<String,Object> params = new HashMap<>(); // 上下文
	
	public HzrActivityPane()
	{		
		this.setLayout(new CustomLayout());
	}
	
	// 前往新的 Hzrtivity
	public void startActivity(Class<? extends HzrActivity> clazz, Object intent)
	{
		HzrActivity a = null;	
		try {
			a = (HzrActivity)clazz.newInstance();
			a.context = this;
			a.intent = intent;
		}catch(Exception e)
		{
			System.out.println("**AfActivityPane: 不能创建类 " + clazz.getName() 
			    +", 请确认此类具有public的默认构造方法(无参数构造方法),并且不要有异常抛出！");
			e.printStackTrace();
			return;
		}
		
		
		this.add(a);
		
		stack.add( a );
		
		if(!a.created)
		{
			a.onCreate(intent);
			a.created = true;
		}
		
		// 隐藏上一个界面
		HzrActivity lastActivity = getLastActivity();
		if(lastActivity != null)
		{
			if(lastActivity.started)
			{
				lastActivity.onStop();
				lastActivity.started = false;
			}
		}
		
		// 显示新的界面
		showLastActivity();
		
		if(!a.started)
		{
			a.onStart();
			a.started = true;
		}		
	}
	
	public void finish(HzrActivity a)
	{
		if(a == null) return;
		
		if(a.started)
		{
			a.onStop();
			a.started = false;
		}
		
		if(a.created)
		{
			a.onDestroy();
			a.created = false;
		}
		
		stack.remove( a );
		
		this.remove( a);
	}
	
	public void back( HzrActivity caller )
	{	
		HzrActivity a = getLastActivity();
		if(  a == caller)
		{
			finish( caller );
		}
		
		showLastActivity();
	}

	
	// 最后一个HzrActivity
	private HzrActivity getLastActivity()
	{
		if(stack.size() == 0) return null;
		return stack.get( stack.size() - 1);
	}
	
	// 显示最后一个Activityss
	private void showLastActivity()
	{
		this.validate();
	}
	
	
	
	// 自定义布局
	private class CustomLayout implements LayoutManager
	{
		@Override
		public void addLayoutComponent(String name, Component comp)
		{			
		}

		@Override
		public void removeLayoutComponent(Component comp)
		{			
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
		public void layoutContainer(Container parent)
		{
			Rectangle rect = parent.getBounds();
			//System.out.println("parent: " + rect);
			
			for(int i=0; i<stack.size();i++)
			{
				HzrActivity a = stack.get(i);
				if(i < stack.size() - 1)
				{
					a.setVisible(false);
				}
				else
				{
					a.setBounds( rect );
					a.setVisible( true );
				}
			}
		}		
	}	
	
	
	//////////////获取参数  //////////////
	public Object getParam(String key, Object defValue)
	{
		Object v = params.get(key);
		if( v == null)
			return defValue;
		return v;
	}
	public void putParam(String key, Object value)
	{
		params.put(key, value);
	}
	
	public long getParamLong(String key, long defValue)
	{
		Long v = (Long)params.get(key);
		if( v == null)
			return defValue;
		return v;
	}
	public int getParamInt(String key, int defValue)
	{
		Integer v = (Integer)params.get(key);
		if( v == null)
			return defValue;
		return v;
	}
	public short getParamShort(String key, short defValue)
	{
		Short v = (Short)params.get(key);
		if( v == null)
			return defValue;
		return v;
	}
	public boolean getParamBoolean(String key, boolean defValue)
	{
		Boolean v = (Boolean)params.get(key);
		if( v == null)
			return defValue;
		return v;
	}	
	public String getParamString(String key, String defValue)
	{
		String v = (String)params.get(key);
		if( v == null)
			return defValue;
		return v;
	}		
}
