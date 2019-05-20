package my;

import javax.swing.JFrame;

public class SwingDemo
{

	public static void createGUI() {
		MyFrame frame = new MyFrame("My Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,300);
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
