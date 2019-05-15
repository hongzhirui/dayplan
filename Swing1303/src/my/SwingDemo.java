package my;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class SwingDemo
{

	private static void createGUI() {
		MyFrame frame = new MyFrame("Swing Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(400,300);
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		//设置界面样式Look And Feel
		try {
			//按当前系统样式自动显示
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
			e.printStackTrace();
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUI();
			}
		});

	}

}
