package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hzr.swing.HzrPanel;
import hzr.swing.HzrRowLayout;

public class MainFrame extends JFrame
{
	ImageBox srcBox = new ImageBox();
	ImageBox dstBox = new ImageBox();
	
	public MainFrame(String title){
		super(title);

		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		//中间区域，左右两个方块
		HzrPanel panel = new HzrPanel();
		panel.setLayout(new HzrRowLayout());
		panel.add(srcBox, "1w");
		panel.add(new JLabel(">>>"));
		panel.add(dstBox, "1w");
		root.add(panel, BorderLayout.CENTER);
		
		srcBox.random();
		dstBox.clear();
		
		dstBox.setStateListener( new ImageBox.StateListener()
		{
			
			@Override
			public void stateChanged(ImageBox box)
			{
				if(box.isComplete()) {
					JOptionPane.showMessageDialog(box, "恭喜通关");
				}
			}
		});
	}
}
