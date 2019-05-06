package my;

import javax.swing.JFrame;

public class SwingDemo
{
	private static void createGUI() {
		
		//构造一个窗口
		MyFrame frame = new MyFrame("Swing Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//设置窗口大小
		frame.setSize(600, 300);
		
		//显示窗口
		frame.setVisible(true);
	}

	
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUI();
			}
		});
		
	}
}

	