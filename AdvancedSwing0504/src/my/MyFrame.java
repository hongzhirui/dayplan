package my;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("流量监控");
		MyToggleButton toggle = new MyToggleButton();
		root.add(label);
		root.add(toggle);
		
		toggle.setPreferredSize(new Dimension(180, 100));
		
		toggle.setSelected(true);
		
		toggle.setStateListener(new MyToggleButton.StateListener()
		{
			
			@Override
			public void stateChanged(Object source)
			{
				System.out.println("hh");
			}
		});
	}

}
