package views;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import hzr.swing.HzrPanel;

public class UserView extends HzrPanel
{
	public UserView()
	{
		this.setLayout(new BorderLayout());
		this.padding(20);
		
		JLabel title = new JLabel("个人设置");
		this.add(title, BorderLayout.PAGE_START);
		
		HzrPanel center = new HzrPanel();
		this.add(center, BorderLayout.CENTER);
	}	

}
