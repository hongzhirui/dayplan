package leftmenu;

//左侧菜单项的数据
public class LeftMenuItem
{
	public String text;
	public String cmd;
	// 还可以添加图标、等其他字段
	
	public LeftMenuItem(String text)
	{
		this.text = text;
	}
	public LeftMenuItem(String text, String cmd)
	{
		this.text = text;
		this.cmd = cmd;
	}
}
