package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import hzr.swing.HzrImageView;

public class MainFrame extends JFrame
{
	//原始图片
	BufferedImage srcImage;
	
	//用来显示图片的控件
	HzrImageView imageView = new HzrImageView();
	
	public MainFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		root.add(imageView, BorderLayout.CENTER);
		imageView.setBgColor(new Color(0x333333));
		imageView.setScaleType(HzrImageView.FIT_CENTER_INSIDE);
		
		JButton scaleButton = new JButton("屏幕截图");
		scaleButton.addActionListener( (e) -> {
			startCapture();
		});
		
		JButton cropButton = new JButton("保存");
		cropButton.addActionListener( (e)-> {
			saveCapture();
		});
		
		//工具栏上的按钮
		JPanel toolbar = new JPanel();
		toolbar.setLayout(new FlowLayout());
		toolbar.add(scaleButton);
		toolbar.add(cropButton);
		root.add(toolbar, BorderLayout.PAGE_START);
	}

	public void saveCapture()
	{
		BufferedImage img = (BufferedImage) imageView.getImage();
		if(img == null) return;
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("图片", "jpg");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File("."));
		int ret = chooser.showSaveDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			// 结果为：用户要保存的文件的路径
			File file = chooser.getSelectedFile();
			String filePath = file.getAbsolutePath();
			if(!filePath.endsWith(".jpg"))
			{
				filePath += ".jpg";
				file = new File(filePath);
			}
			
			try {
				ImageIO.write(img, "JPEG", file);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	//显示选取到的图片
	public void showImage(BufferedImage screenImg, Rectangle area)
	{
		BufferedImage img = screenImg.getSubimage(area.x, area.y, area.width, area.height);
		imageView.setImage(img);
	}
	
	public void startCapture() {
		this.setVisible(false);//隐藏窗口
		
		new CaptureThread().start();
	}
	
	//抓取屏幕
	private void captureScreen() {
		BufferedImage snapshot = null;
		
		try {
			//获取屏幕的尺寸
			Dimension screanSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			//屏幕截图
			Robot robot = new Robot();
			snapshot = robot.createScreenCapture(new Rectangle(screanSize));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		CaptureFrame otherFrame = new CaptureFrame(this,snapshot);
		otherFrame.setVisible(true);
	}

	private class CaptureThread extends Thread{
		@Override
		public void run() {
			try {
				sleep(500);
			}catch(Exception e) {
				e.printStackTrace();
			}
			SwingUtilities.invokeLater( () -> {
				captureScreen();
			});
		}
	}
}
