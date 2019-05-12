package my;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MyFrame extends JFrame
{

	public MyFrame(String title)
	{
		super(title);
		
		// 添加图片显示控件
		BgPanel root = new BgPanel();
		this.setContentPane(root);
					
		//////////////////////////////////
		// 由于 AfImageView 是一个 JPanel，是一个容器，所以还可以添加子控件
		root.setLayout(new BoxLayout(root,BoxLayout.Y_AXIS));
		root.add(new JLabel("样例文本"));
		root.add(new JButton("测试按钮"));
		
	}
	
	
	private class BgPanel extends JPanel
	{
		Image image = null ;
		
		public BgPanel()
		{
			URL imageUrl = MyFrame.class.getResource("/images/bg.jpg");
			try{
				image = ImageIO.read(imageUrl);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		@Override
		protected void paintComponent(Graphics g)
		{
			int width = this.getWidth();
			int height = this.getHeight();
			g.clearRect(0, 0, width, height);
			
			// 画背景图
			g.drawImage(image, 0, 0, width, height, null);
			
			// 加上一层半透明的遮罩
			g.setColor(new Color(255,255,255,200));
			g.fillRect(0, 0, width, height);
		}
	}
	
}
