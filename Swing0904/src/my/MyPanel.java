package my;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel
{
	public int grain = 1; //线条的精细度
	public int range = 50; //高度（振幅半径）
	public int period = 100; //X轴，每100像素表示一个周期（2PI）

	@Override
	protected void paintComponent(Graphics g)
	{
		int width = this.getWidth();
		int height = this.getHeight();
		
		g.clearRect(0, 0, width, height);

		int center = height/2;
		
		g.setColor(Color.blue);
		g.drawLine(0, height/2, width, height/2);
		
		//正弦曲线
		int x1 = 0;
		int y1 = 0;
		for(int i=0; i<width; i += grain) {
			int x2 = i;
			int y2 = (-1)*(int)(Math.sin(x2*2*Math.PI/period)*range);
			g.drawLine(x1, center+y1, x2, center+y2);
			
			x1 = x2;
			y1 = y2;
		}
	}
	
}
