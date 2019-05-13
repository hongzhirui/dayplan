package my;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		
		panel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					System.out.println("左键");
				else if(e.getButton() == MouseEvent.BUTTON2)
					System.out.println("中键");
				else if(e.getButton() == MouseEvent.BUTTON3)
					System.out.println("右键");	
				
				int cc = e.getClickCount();
				if(cc == 1)
					System.out.println("单击");
				else if(cc == 2)
					System.out.println("双击");
				
				System.out.println("鼠标位置: " + e.getX() + "," + e.getY());
			}
		});
	}

}
