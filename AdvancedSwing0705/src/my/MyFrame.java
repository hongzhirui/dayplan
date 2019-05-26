package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MyFrame extends JFrame
{
	JLabel textField = new JLabel();
	public MyFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		JButton start = new JButton("开始");
		start.setPreferredSize(new Dimension(getWidth(),30));
		
		root.add(start, BorderLayout.PAGE_START);
		root.add(textField, BorderLayout.CENTER);
		textField.setFont(new Font("宋体", Font.PLAIN, 80));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setOpaque(true);
		textField.setBackground(Color.WHITE);
		textField.setForeground(Color.blue);
		start.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				onMouseClicked();
			}
			
		});
	}
	
	private void onMouseClicked() {
		myWorkThread th = new myWorkThread();
		th.start();
	}
	
	private class myWorkThread extends Thread{

		@Override
		public void run()
		{
			try {
				updateUi("5");
				Thread.sleep(1000);
				updateUi("4");
				Thread.sleep(1000);
				updateUi("3");
				Thread.sleep(1000);
				updateUi("2");
				Thread.sleep(1000);
				updateUi("1");
				Thread.sleep(1000);
				updateUi("新年快乐");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void updateUi(String progress) {
		SwingUtilities.invokeLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				textField.setText(progress);
			}
		});
	}
}
