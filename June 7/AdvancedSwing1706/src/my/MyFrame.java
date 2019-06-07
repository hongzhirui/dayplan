package my;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import hzr.swing.HzrToaster;

public class MyFrame extends JFrame
{
	JButton testButton = new JButton("测试");
	
	public MyFrame(String title) {
		super(title);
		//Content Pane
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());
		root.setOpaque(true);
		root.setBackground(Color.WHITE);
		
		root.add(testButton);
		testButton.setPreferredSize(new Dimension(60, 30));
		testButton.setFocusPainted(false);
		
		testButton.addActionListener( (e) -> {
			test();
		});
	}
	
	private void test() {
//		HzrToaster toaster = new HzrToaster();
//		toaster.setMessage("欢迎测试！");
//		toaster.setLevel(HzrToaster.Level.WARN);
//		toaster.showPopup(this);
		
		HzrToaster.show(testButton, HzrToaster.Level.ERROR, 1800, "欢迎测试!");
	}

}
