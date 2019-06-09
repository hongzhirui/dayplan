package my;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import hzr.swing.HzrActivityPane;
import pages.Resume;
import pages.Step1Activity;

public class MainFrame extends JFrame
{
	HzrActivityPane activityPane = new HzrActivityPane();

	public MainFrame(String title)
	{
		super(title);

		// Content Pane	
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());	
		
		this.add(activityPane, BorderLayout.CENTER);	
		
		
		activityPane.putParam("resume", new Resume());
		activityPane.startActivity(Step1Activity.class, null);
		
	}

}
