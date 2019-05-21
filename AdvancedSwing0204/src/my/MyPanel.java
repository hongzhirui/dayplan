package my;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.TexturePaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


/*
 * Color:纯色填充
 * LinearGradientPaint:线性渐变填充
 * RadialGradientPaint:辐射渐变填充
 * TexturePaint:纹理填充
 */

public class MyPanel extends JPanel
{
	BufferedImage textureImage;

	public MyPanel() {
		//加载背景图片125X125
		URL imagePath = getClass().getResource("/images/im_texture.jpg");
		try {
			textureImage = ImageIO.read(imagePath);
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
		
		//纯色填充
		if(true) {
			Color color = new Color(0xFF7F24);
			g2d.setPaint(color);
			g2d.fillRect(0, 0, width/2, height/2);
		}
	
		//线性渐变填充
		if(true) {
			//右上角
			Rectangle r = new Rectangle(width/2, 0, width/2, height/2);
			
			//线性渐变
			//渐变的方向：从start -> end
			//渐变的颜色：设置N个关键点
			Point2D start = new Point2D.Float(r.x, r.y);
			Point2D end = new Point2D.Float(r.x+r.width, r.y);
			float[] dist = {0.0f, 0.8f, 1.0f};//插入关键点
			Color[] colors = {Color.GREEN, Color.yellow, Color.WHITE};//设置关键点的颜色
			Paint paint = new LinearGradientPaint(start, end, dist, colors);
			
			//设置填充
			g2d.setPaint(paint);
			g2d.fillRect(r.x, r.y, r.width, r.height);
		}
		
		//辐射渐变填充
		if(true) {
			//左下角
			Rectangle r = new Rectangle(0, height/2, width/2, height/2);
			
			Point2D center = new Point2D.Double(r.getCenterX(), r.getCenterY());
			float radius = width/2;
			float[] dist = {0.0f, 0.2f, 1.0f};
			Color[] colors = {Color.RED, Color.WHITE, Color.BLUE};
			RadialGradientPaint paint = new RadialGradientPaint(center, radius, dist, colors);
			
			//设置填充
			g2d.setPaint(paint);
			g2d.fillRect(r.x, r.y, r.width, r.height);
		}
		
		//纹理填充
		if(true) {
			//右下角
			Rectangle r = new Rectangle(width/2, height/2, width/2, height/2);
			
			//图片是125X125的图片，实际填充时的循环填充直到填充满
			//anchor用于指定图片的哪一部分用于填充
			Rectangle anchor = new Rectangle(0,0,textureImage.getWidth(), textureImage.getHeight());
			Paint paint = new TexturePaint(textureImage, anchor);
			
			//设置填充
			g2d.setPaint(paint);
			g2d.fillRect(r.x, r.y, r.width, r.height);
		}
	}
	

}
