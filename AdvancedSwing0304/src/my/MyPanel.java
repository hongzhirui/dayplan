package my;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

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
		
		g2d.setPaint(Color.BLUE);
		Rectangle rect = new Rectangle(40, 40, 400, 100);
		g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
		
		String str = "略略略略略略";
		g2d.setFont(new Font("宋体",Font.BOLD,30));
		
		//FontMetrics用于文本测量
		FontMetrics fm = g2d.getFontMetrics(g2d.getFont());
		int textWidth = fm.stringWidth(str);
		int fontSize = fm.getHeight();
		int leading = fm.getLeading();
		int ascent = fm.getAscent();
		int descent = fm.getDescent();
		
		int x=0, y=0;
		
		/* 水平方向  */
//		x = rect.x;//水平靠左
		x = rect.x + (rect.width - textWidth)/2;//水平居中
//		x = rect.x + rect.width - textWidth;//水平靠右
		
		/* 竖直方向 */
//		y = rect.y;//竖直靠上
		y = rect.y + rect.height/2 + (fontSize - leading - descent)/2;//竖直居中
		y = rect.y + rect.height/2 + (fontSize-leading)/2 - descent;
//		y = rect.y + rect.height - descent;//竖直靠下
		
		g2d.drawString(str, x, y);
	}
	
	

}
