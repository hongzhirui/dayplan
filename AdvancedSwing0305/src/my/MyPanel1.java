package my;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Calendar;

import javax.swing.JPanel;

public class MyPanel1 extends JPanel
{
	public MyPanel1() {
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setPaint(Color.WHITE);
		g2d.fillRect(0, 0, width, height);
		
		int x = 0, y = 0;
		int x_size = 40;//单元格宽度
		int y_size = 40;//单元格高度
		
		//第一行：一、二、三、四、五、六、日
		g2d.setPaint(Color.BLACK);
		g2d.drawLine(x, y, x + x_size*7, y);
		g2d.drawLine(x, y + y_size, x + x_size*7, y + y_size);
		String[] cc = {"一","二","三","四","五","六","日"};
		
		g2d.setPaint(Color.BLACK);
		g2d.setFont(new Font("宋体", Font.PLAIN, 20));
		for(int i=0; i<cc.length; i++) {
			Rectangle rect = new Rectangle(x + x_size*i, y, x_size, y_size);
			drawText(g2d, cc[i], rect);
		}
		
		//计算本月第一天是星期几
		Calendar cal = Calendar.getInstance();
		int theMonth = cal.get(Calendar.MONTH);
		int theDay = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, 1);//向前推到本月第一天
		int weekday = cal.get(Calendar.DAY_OF_WEEK);//计算本月第一天是周几
		System.out.println("week:"+weekday);
		System.out.println(Calendar.MONDAY);
		
		//往前推N天
		//Calendar里规定Sunday=1（周日）, Monday=2, ... Saturday=7(周六)
		//它是周一，则前推0天，周二，推一天
		int start = weekday - Calendar.MONDAY;
		System.out.println("start:"+start);
		if(start < 0) start = 6;
		cal.add(Calendar.DAY_OF_MONTH, 0 - start);//前推，从上月开始显示
		
		//绘制5行
		//每月最多31天，可分5行显示
		x=0;
		y += y_size;
		for(int i=0; i<5; i++) {//五行
			for(int j=0; j<7; j++) {//7列
				//判断月份是否相同
				if(cal.get(Calendar.MONTH) == theMonth) {
					g2d.setPaint(Color.BLACK);
				}else {
					g2d.setPaint(Color.GRAY);
				}
				int day = cal.get(Calendar.DAY_OF_MONTH);
				Rectangle rect = new Rectangle(x + x_size*j, y + y_size*i, x_size, y_size);
				drawText(g2d, String.valueOf(day), rect);
				
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
	}
	
	//将str绘制在矩形rect中，居中显示
	private void drawText(Graphics2D g2d, String str, Rectangle rect) {
		FontMetrics fm = g2d.getFontMetrics(g2d.getFont());
		int fontSize = fm.getHeight();
		int textWidth = fm.stringWidth(str);
		int leading = fm.getLeading();
		int ascent = fm.getAscent();
		int descent = fm.getDescent();
		
		int x=0, y=0;
		x = rect.x + (rect.width - textWidth)/2; //水平居中
		y = rect.y + rect.height/2 + (fontSize - leading - descent)/2; //竖直居中
		g2d.drawString(str, x, y);
	}
}
