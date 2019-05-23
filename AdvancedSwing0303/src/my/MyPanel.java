package my;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
		
		//平滑绘制（反锯齿）
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setPaint(Color.BLUE);
		String str = "good boy!";
		/*
		 * font family:字体名（"宋体","微软雅黑","times"...）
		 * font style:字体样式（PLAIN, BOLD, ITALIC）    Font.BOLD|Font.ITALIC -> 既粗体又斜体，两样式中间加了或运算
		 * font size:字体大小（高度，单位时像素）
		 */
		g2d.setFont(new Font("宋体", Font.ITALIC, 60));
		
		//基于旧的字体创建新的Font对象
//		Font newFont = g2d.getFont().deriveFont(Font.BOLD, 60);
//		g2d.setFont(newFont);//字体高度
		
		g2d.drawString(str, 100, 100);

		//参考线
		g2d.setPaint(Color.RED);
		g2d.drawLine(0, 100, width, 100);
		g2d.drawLine(100, 0, 100, height);
		//文字基线baseline:指四线三格中从下往上的第二根线
		
		//文本的测量：FontMetrics
		FontMetrics fm = g.getFontMetrics(g2d.getFont());
		int textWidth = fm.stringWidth(str);
		int fontSize = fm.getHeight();//height=leading+ascent+descent
		int leading = fm.getLeading();
		int ascent = fm.getAscent();
		int descent = fm.getDescent();
		System.out.printf("文本宽度: %d,  字体高度: %d : (%d, %d, %d)\n", textWidth, fontSize, leading, ascent, descent);

		g2d.drawLine(0, 100-fm.getAscent()-fm.getLeading(), width, 100-fm.getAscent()-fm.getLeading());//第一条线
		g2d.drawLine(0, 100-fm.getAscent(), width, 100-fm.getAscent());//第二条线
		g2d.drawLine(0, 100, width, 100);//第三条线
		g2d.drawLine(0, 100+fm.getDescent(), width, 100+fm.getDescent());//第四条线
		g2d.drawLine(100+textWidth, 0, 100+textWidth, height);
		
		g2d.setPaint(Color.BLACK);
		String s1 = "Leading";
		g2d.setFont(new Font("楷体",Font.PLAIN,fm.getLeading()));
		g2d.drawString(s1, 70, 100-fm.getAscent());
		
		String s2 = "Ascent";
		g2d.setFont(new Font("楷体",Font.PLAIN,fm.getLeading()));
		g2d.drawString(s2, 70, 100);
		
		String s3 = "Descent";
		g2d.setFont(new Font("楷体",Font.PLAIN,fm.getDescent()));
		g2d.drawString(s3, 70, 100+fm.getDescent());
	}
	
	

}
