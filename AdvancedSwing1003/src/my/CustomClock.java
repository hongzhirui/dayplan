package my;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.Calendar;

import javax.swing.JPanel;

public class CustomClock extends JPanel
{
	
	@Override
	protected void paintComponent(Graphics g)
	{
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setPaint(Color.white);
		g2d.fillRect(0, 0, width, height);
				
		// 取得一个最大的正文形
		//Rectangle rect = new Rectangle(0,0,200,200);
		int w = width;
		int h = width;
		if( h > height)
		{
			h = height;
			w = height;
		}
		Rectangle rect = new Rectangle((width-w)/2, (height-h)/2, w, h);		
		rect.grow(-4,-4); // 往里缩一点
		
		// 中心点及半径
		int centerX = (int)rect.getCenterX();
		int centerY = (int)rect.getCenterY();
		int radius = (int)rect.width / 2;
		
		//////////////  表盘的圆框 /////////////
		if(true)
		{			
			// 背景填充白色
			g2d.setPaint(new Color(0xFFFFFF));
			g2d.fillOval(rect.x, rect.y, rect.width, rect.height);
			
			// 边框
			g2d.setStroke(new BasicStroke(4));
			g2d.setPaint(new Color(0x666666));
			g2d.drawOval(rect.x, rect.y, rect.width, rect.height);
		}
		
		/////////////// 刻度 /////////////////
		if(true)
		{
			g2d.setStroke(new BasicStroke(4));
			for(double angle=0; angle<360; angle += 30)
			{
				drawRadialLine(g2d, centerX, centerY, radius, angle);
			}				
			// 截断辐条得到刻度
			radius -= 5;
			g2d.setPaint(Color.WHITE);			
			g2d.fillOval(centerX-radius, centerY-radius, radius*2, radius*2);
			// 中心画一个小圆点
			int r = 4;
			g2d.setPaint(new Color(0x666666));			
			g2d.fillOval(centerX-r, centerY-r, r*2, r*2);			
		}
		
		////////////// 秒针，分针，时针 ///////////
		
		// 得到时分秒值
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR); // 12小时制
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		// 时针
		if(true)
		{
			double radius2 = radius *0.5;
			g2d.setPaint(Color.BLACK);
			g2d.setStroke(new BasicStroke(6));
			
			double vv = hour + minute/60.0; // 角度计算有一点点复杂
			drawRadialLine(g2d, centerX, centerY, radius2, vv/12.0 * 360 - 90);
		}
		
		// 分针
		if(true)
		{
			double radius2 = radius *0.7;
			g2d.setPaint(Color.BLACK);
			g2d.setStroke(new BasicStroke(3));
			
			double vv = minute + second/60.0;
			drawRadialLine(g2d, centerX, centerY, radius2, minute/60.0 * 360 - 90);
		}
		
		// 秒针
		if(true)
		{
			double radius2 = radius *0.9;
			g2d.setPaint(Color.RED);
			g2d.setStroke(new BasicStroke(1));			
			drawRadialLine(g2d, centerX, centerY, radius2, second/60.0 * 360 - 90 );
		}		

	}
	
	// 绘制从圆心发散出的辐条线条，中心centerX, centerY, 半径radius, 角度angle
	private void drawRadialLine(Graphics2D g2d, double centerX, double centerY, double radius, double angle)
	{
		double radian = angle / 180 * Math.PI; // 角度转成弧度
		
		double x = centerX + radius * Math.cos( radian );
		double y = centerY + radius * Math.sin( radian );		
		
		Line2D line = new Line2D.Double(x,y, centerX, centerY);
		g2d.draw( line );
	}
}
