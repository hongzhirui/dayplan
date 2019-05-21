package my;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.JPanel;


/*
 * Panit:颜色
 * Stroke:线型、线宽
 * Font:字体（在绘制文本时有效）
 * Clip:剪裁区域
 * Transform:变型（旋转、镜像等）
 * composite:叠加
 */

public class MyPanel extends JPanel
{

	@Override
	protected void paintComponent(Graphics g)
	{
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D)g;
		
//		g2d.setColor(Color.blue);
//		g2d.drawLine(0, 0, width, height);
//		g2d.drawLine(width, 0, 0, height);
		
		//平滑绘制（反锯齿）使之尽量平滑、少一点锯齿毛刺）
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//构造一个dash线型
		// Stroke是一个接口，BasicStroke实现了Stroke这个接口
		Stroke stroke = new BasicStroke(3,//线宽
				BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_ROUND,//圆角连接（当线条相交时）
				1,
				new float[] {2,4,8,4},//线型（线条、空白，线条、空白）
				0);
		g2d.setStroke(stroke);
		
		Paint color = Color.blue;
		g2d.setPaint(color);
		
		g2d.drawLine(0, 0, width, height);
		g2d.drawLine(width, 0, 0, height);
	}
	

}
