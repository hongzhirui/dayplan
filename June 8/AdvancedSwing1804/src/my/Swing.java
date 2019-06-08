package my;

import javax.swing.JFrame;

public class Swing
{

	public static void createGUI() {
		JFrame frame = new MainFrame("demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
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
