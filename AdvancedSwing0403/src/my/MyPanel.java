package my;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;

import javax.swing.JPanel;

public class MyPanel extends JPanel
{
	public MyPanel() {
		
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setPaint(Color.white);
		g2d.fillRect(0, 0, width, height);
		
		//U型跑道
		if(true) {
			//外圈
			int x = 50, y = 50;
			Rectangle r1 = new Rectangle(x, y, 100, 100);
			Rectangle r2 = new Rectangle(x+50, y, 100, 100);
			
			Shape s1 = new Arc2D.Double(r1, 90, 180, Arc2D.PIE);
			Shape s2 = new Arc2D.Double(r2, 270, 180, Arc2D.PIE);
			
			//两个半圆，一个矩形
			g2d.setPaint(Color.red);
			g2d.fill(new Rectangle(x+50,y,50,100));
			g2d.fill(s1);
			g2d.fill(s2);
		}
		
		if(true) {
			//内圈
			int x = 65, y = 65;
			Rectangle r1 = new Rectangle(x, y, 70, 70);
			Rectangle r2 = new Rectangle(x+50, y, 70, 70);
			
			Shape s1 = new Arc2D.Double(r1, 90, 180, Arc2D.OPEN);
			Shape s2 = new Arc2D.Double(r2, 270, 180, Arc2D.OPEN);

			//构造一条闭合路径
			Path2D outline = new Path2D.Double();
			outline.append(s1, true);
			outline.append(s2, true);//true表示自动连接上一条线的终点和下一条线的起点
			outline.closePath();
			
			g2d.setPaint(Color.green);
			g2d.fill(outline);
		}
	}
	

}
