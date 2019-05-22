package my;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

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
		
		//设置线型
		Stroke stroke = new BasicStroke(10);
		g2d.setStroke(stroke);
		
		Color c1 = Color.GREEN;
		Color c2 = Color.RED;
		Color c3 = Color.BLUE;
		Color c4 = Color.CYAN;
		drawLine(g2d, 0, 0, width, 0, c1, c2);//上边界
		drawLine(g2d, width, 0, width, height, c2, c3);//右边界
		drawLine(g2d, width, height, 0, height, c3, c4);//下边界
		drawLine(g2d, 0, height, 0, 0, c4, c1);//左边界
	}
	
	private void drawLine(Graphics2D g2d, 
			int x1, int y1, int x2, int y2,
			Color c1, Color c2) {
		//Point2D
		Point2D start = new Point2D.Double(x1, y1);//起点
		Point2D end = new Point2D.Double(x2, y2);//终点（渐变方向）
		
		float[] dist = {0.0f, 1.0f};//插入关键点
		Color[] colors = {c1, c2};//关键点的颜色
		Paint paint = new LinearGradientPaint(start, end, dist, colors);
		
		//设置Paint
		g2d.setPaint(paint);
		
		//构造一个Shape
		Shape shape = new Line2D.Double(start, end);
		g2d.draw(shape);
	}
	

}
