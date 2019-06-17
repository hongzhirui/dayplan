package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JInternalFrame;

import hzr.swing.HzrImageView;


public class DocumentFrame extends JInternalFrame 
{
	// 内部数据
	private File file;
	private Image image;
	
	// 界面控件
	private HzrImageView imageView;
	
	public DocumentFrame(File file)
	{
		this.file=file;
		
		// 界面初始化
		this.setLayout(new BorderLayout());
		imageView = new HzrImageView();
		imageView.setScaleType(HzrImageView.FIT_CENTER_INSIDE);
		imageView.setBgColor(new Color(60,60,60));
		this.add(imageView, BorderLayout.CENTER);		
	}
		
	// 加载显示
	public void load() throws Exception
	{
		image = ImageIO.read( file );
		imageView.setImage(image);
	}
	
	// 获取文档名称
	public String getDocumentTitle ()
	{
		return file.getName();
	}
	
	// 获取文档路径 
	public File getDocument ()
	{
		return file;
	}
	
	
}
