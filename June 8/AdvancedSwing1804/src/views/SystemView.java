package views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;

import hzr.swing.HzrColumnLayout;
import hzr.swing.HzrPanel;
import hzr.swing.HzrRowLayout;
import hzr.swing.HzrToggleButton;


public class SystemView extends HzrPanel
{
	public SystemView()
	{
		this.setLayout(new BorderLayout());
		this.padding(20);
		
		JLabel title = new JLabel("系统设置");
		this.add(title, BorderLayout.PAGE_START);
		
		HzrPanel center = new HzrPanel();
		center.padding(20);
		
		center.setMaximumSize(new Dimension(200,0));
		this.add(center, BorderLayout.CENTER);
		
		initCenter(center);
	}
	
	private void initCenter(HzrPanel p)
	{
		p.setLayout( new HzrColumnLayout(10, true));
		
		if(true)
		{
			HzrPanel row = new HzrPanel();
			row.setPreferredSize(new Dimension(300,0));
			row.setLayout(new HzrRowLayout());
			row.add(new JLabel("允许匿名聊天"), "1w");
			row.add(new HzrToggleButton(), "40px");
			p.add( row, "20px");
		}
		
		if(true)
		{
			HzrPanel row = new HzrPanel();
			row.setPreferredSize(new Dimension(300,0));
			row.setLayout(new HzrRowLayout());
			row.add(new JLabel("防止发呆"), "1w");
			row.add(new HzrToggleButton(), "40px");
			p.add( row, "20px");
		}
		
		if(true)
		{
			HzrPanel row = new HzrPanel();
			row.setPreferredSize(new Dimension(300,0));
			row.setLayout(new HzrRowLayout());
			row.add(new JLabel("允许异地登录"), "1w");
			row.add(new HzrToggleButton(), "40px");
			p.add( row, "20px");
		}
	}

}
