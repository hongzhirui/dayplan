package my;

import javax.swing.JFrame;

public class Swing
{
	private static void createGUI() {
		JFrame frame = new MyFrame("My Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUI();
			}
		});
	}

}
