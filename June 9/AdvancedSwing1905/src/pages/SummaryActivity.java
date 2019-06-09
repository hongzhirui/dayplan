package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import hzr.swing.HzrActivity;
import hzr.swing.HzrToaster;

public class SummaryActivity extends HzrActivity
{
	
	public SummaryActivity(){
		this.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("已经处理你的简历，请等待通知");
		label.setFont(new Font("宋体 ", Font.PLAIN, 20));
		label.setOpaque(true);
		label.setBackground(new Color(0xFFFFFF));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.add(label, BorderLayout.CENTER);
		
		
	}


	@Override
	public void onCreate(Object intent)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStart()
	{
		Resume r = (Resume) context.getParam("resume", null);
		
		// 处理 ...
		
		
		HzrToaster.show(this, "处理简历...");
	}


}
