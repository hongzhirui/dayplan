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
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setPaint(Color.BLACK);
		String str = " 一  二  三  四  五  六  日 ";
		g2d.setFont(new Font("楷体",Font.PLAIN,25));
		
		FontMetrics fm = g2d.getFontMetrics();
		g2d.drawLine(0, 0, fm.stringWidth(str), 0);
		g2d.drawLine(0, fm.getHeight()+10, fm.stringWidth(str), fm.getHeight()+10);
		
		System.out.println(fm.getHeight());
		System.out.println(fm.stringWidth(str));

		g2d.drawString(str, 0, (fm.getHeight()+10)/2+(fm.getHeight()-fm.getLeading()-fm.getDescent())/2);
		
		
		
	}
	

}
