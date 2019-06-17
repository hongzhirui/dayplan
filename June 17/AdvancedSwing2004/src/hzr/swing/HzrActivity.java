package hzr.swing;

import javax.swing.JPanel;

public abstract class HzrActivity extends JPanel
{
	protected HzrActivityPane context;
	protected boolean created = false;
	protected boolean started = false;
	protected Object intent; 
	
	public String name;     // 备用字段：名字
	public Object userData; // 备用字段：用户数据
	
	// 生命期回调
	public abstract void onCreate(Object intent) ;
	
	public void onDestroy(){}
	
	public abstract void onStart();
	
	public void onStop(){}
	
	
	// 结束本Activity
	public final void finish()
	{
		if(context != null)
		{
			context.finish(this);
		}
	}
	
	// 结束本Activity, 并返回到上一个Activity
	public final void back()
	{
		if(context != null)
		{
			context.back(this);
		}
	}
	
	// 启动新的Activity	
	public final void startActivity(Class<? extends HzrActivity> clazz, Object intent)
	{
		if(context != null)
		{
			context.startActivity(clazz, intent);
		}
	}
}
