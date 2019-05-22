package my;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import af.swing.image.AfImageScaler;

/*
 * clip(shape)：添加剪裁区域，与当前Clip区域叠加
 * clip(null)：清空剪裁区域
 */
public class MyPanel extends JPanel
{
	BufferedImage image;
	
	public MyPanel() {
		//加载背景图片125X125
		URL imagePath = getClass().getResource("/images/qingtan15.jpg");
		try {
			image = ImageIO.read(imagePath);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D)g;
		
		//计算缩放后的区域
		int imgW = image.getWidth();
		int imgH = image.getHeight();
		AfImageScaler scaler = new AfImageScaler(imgW, imgH, width, height);
		Rectangle fit = scaler.fitCenterInside();
		
		//设置clip区域（仅此区域内可被绘制）
		int size = 200;
		Shape region1 = new Rectangle2D.Double((width-size)/2, (height-size)/2, size/2, size);
		Shape region2 = new Ellipse2D.Double((width-size)/2, (height-size)/2, size, size);
		g2d.clip(region1);
		g2d.clip(region2);//所得图形为region1和region2两形状的交集
		
		//绘制图形
		g2d.drawImage(image, fit.x, fit.y, fit.width, fit.height, null);
		
	}
	

}
