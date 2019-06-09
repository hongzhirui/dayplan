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

import hzr.swing.HzrActivity;
import hzr.swing.HzrBorder;
import hzr.swing.HzrMarginLayout;

public class Step3Activity extends HzrActivity
{
	JTextArea textarea = new JTextArea();

	
	public Step3Activity(){
		this.setLayout(new BorderLayout());
		
		initTop("自我介绍");
			
		initCenter();
		
		initBottom();
	}

	private void initTop(String title){		
		JLabel label = new JLabel(title);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));

		this.add(label, BorderLayout.PAGE_START);
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0xCCCCCC)));

		HzrBorder.addPadding(label, 10);		
	}
	
	private void initCenter(){
		textarea.setPreferredSize(new Dimension(9999,9999));
		
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new HzrMarginLayout());
		wrapper.add(textarea, new HzrMarginLayout.Margin(4,4,4,4));
		wrapper.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0xCCCCCC)));
		this.add(wrapper, BorderLayout.CENTER);		
	}
	
	private void initBottom(){
		JPanel bottom = new JPanel();
		this.add(bottom, BorderLayout.PAGE_END);
		
		JButton finishButton = new JButton("完成");
		bottom.add(finishButton);		
		finishButton.addActionListener( (e)->{
			saveData();
			
			startActivity(SummaryActivity.class, null);
		});
	}
	
	@Override
	public void onCreate(Object intent)
	{
		
	}

	@Override
	public void onStart()
	{
		
	}
	
	public void saveData(){
		Resume r = (Resume) context.getParam("resume", null);
		
		r.introduce= textarea.getText();
	}
}
