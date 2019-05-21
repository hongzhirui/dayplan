package my;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Swing
{
	private static void createGUI() {
		MyFrame frame = new MyFrame("My Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(400, 300));
		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				createGUI();
			}
		});
		
	}

}
