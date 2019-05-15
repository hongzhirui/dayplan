package my;

import java.awt.Container;

import javax.swing.JFrame;

/*
 * 对话框的最大特点：阻塞
 * 当对话框显示时，后台界面被阻塞
 * 调用方法阻塞，直到对话框被关闭
 */
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
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUI();
			}
		});

	}

}
