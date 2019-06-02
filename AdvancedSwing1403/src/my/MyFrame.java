package my;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import hzr.swing.HzrImageView;

public class MyFrame extends JFrame
{
	//原始照片
	BufferedImage srcImage;
	
	HzrImageView srcFrame = new HzrImageView();
	HzrImageView dstFrame = new HzrImageView();
	
	public MyFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		JButton scaleButton = new JButton("镜像");
		scaleButton.addActionListener( (e) -> {
			testReverseImage();
		});
		
		JButton cropButton = new JButton("旋转");
		cropButton.addActionListener( (e) -> {
			testRotateImage();
		});
		
		//工具栏上的按钮
		JPanel toolbar = new JPanel();
		toolbar.setLayout(new FlowLayout());
		toolbar.add(scaleButton);
		toolbar.add(cropButton);
		root.add(toolbar, BorderLayout.PAGE_START);
		
		//加载原始图片
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(srcFrame);
		splitPane.setRightComponent(dstFrame);
		splitPane.setResizeWeight(0.5);//分割比例
		root.add(splitPane, BorderLayout.CENTER);
		
		JLabel aa = new JLabel("(左侧为原图 )     =>    (右侧为转换后的图)");
		aa.setHorizontalAlignment(SwingConstants.CENTER);
		root.add(aa,BorderLayout.PAGE_END);
		try {
			URL url = getClass().getResource("/images/abc.jpg");
			this.srcImage = ImageIO.read(url);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		srcFrame.setImage(srcImage);
	}
	
	// 上下翻转(镜像)
	// x' = x              m00=1  m01=0  m02=0
	// y' = h - y      	   m10=0  m11=-1 m12=h
	// matrix = {1,0,   0,-1,   0,h }
	
	// 水平翻转(镜像)
	// x' = w - x          m00=-1  m01=0  m02=w
	// y' = y		       m10=0   m11=1  m12=0
	// matrix = {-1,0,  0,1,  w, 0 }
	public void testReverseImage() {
		int w = srcImage.getWidth() - 1;
		int h = srcImage.getHeight() - 1;
		
		//转换
		double[] matrix = {1,0,   0,-1,   0,h };
		AffineTransform transform = new AffineTransform(matrix);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		Image dstImage = op.filter(srcImage, null);
		
		// 将转换后的结果显示到界面上
		dstFrame.setImage(dstImage);		
		System.out.println("完成");
	}
	
	// 左转90度  
	// x' = y'              m00=0  m01=1  m02=0
	// y' = w - x'      	m10=-1 m11=0  m12=w
	// matrix = {0,-1, 1,0, 0, w }
	
	// 左转180度  
	// x' = w-x             m00=-1  m01=0  m02=w
	// y' = h-y      		m10=0   m11=-1 m12=h
	// matrix = {-1,0, 0,-1, w, h }
	
	// 左转270度  ( 即右转90度 )
	// x' = h - y           m00=0  m01=-1  m02=h
	// y' = x      			m10=1   m11=0  m12=0
	// matrix = {0,1, -1,0, h, 0}
	public void testRotateImage()
	{
		int w = srcImage.getWidth() - 1;
		int h = srcImage.getHeight()- 1;
		
		// 转换
		double[] matrix = {-1,0, 0,-1, w, h }  ;
		AffineTransform transform = new AffineTransform(matrix);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		Image dstImage = op.filter(srcImage, null);
		
		// 将转换后的结果显示到界面上
		dstFrame.setImage(dstImage);		
		System.out.println("完成");
	}

}
