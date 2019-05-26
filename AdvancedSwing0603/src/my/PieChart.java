package my;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class PieChart extends JPanel
{
	//数据：pie表示每一小块
	private List<Part> partList = new ArrayList<>();
	
	public PieChart() {
		this.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				onMouseClicked(e);
			}
		});
	}
	
	private void onMouseClicked(MouseEvent e) {
		//被选中的分区
		Part activePart = null;
		
		//判断点中了哪一个小块
		for(Part p : partList) {
			if(p.shape == null) continue;
			if(p.shape.contains(e.getX(), e.getY())) {
				p.selected = true;
				activePart = p;
			}else {
				p.selected = false;
			}
		}
		
		if( activePart != null) {
			repaint();
			
			if(this.partClickedListener != null) {
				partClickedListener.partClicked( activePart.tag);
			}
		}
	}
	
	public interface PartClickedListener{
		public void partClicked(Object tag);
	}
	
	private PartClickedListener partClickedListener;
	
	public void setPartClickedListener(PartClickedListener listener) {
		this.partClickedListener = listener;
	}
	
	//Color如果为null,则由内部自动分配\
	public void addPart(double amount, Object tag, Color color) {
		if(amount <= 0) return ;
		Part p = new Part();
		p.amount = amount;
		p.tag = tag;
		p.color = color;
		if(color == null) {
			//自动分配随机颜色
			int rgb = new Random().nextInt(0xFFFFFF);
			p.color = new Color(rgb);
		}
		partList.add(p);
	}
	
	//根据每一个饼的数值，分配角度比例，自动分配颜色
	private void calculate() {
		if(partList.size() == 0) return ;
		
		//求出总数量
		int totalAmount = 0;
		for(Part p : partList)
			totalAmount += p.amount;
		
		//分配每一份所占的角度
		int totalDegrees = 0;
		for(int i=0; i<partList.size(); i++) {
			Part p = partList.get(i);
			p.degreee = (int) (360 * p.amount / totalAmount);
			if(p.degreee < 3)
				p.degreee = 3;//有的份额太少，最低分配3角度
			if(i == partList.size() - 1)
				p.degreee = 360 - totalDegrees;//确保占满360度
			totalDegrees += p.degreee;
		}
		
		//取得一个最大的正方形
		int width = getWidth();
		int height = getHeight();
		int w = width;
		int h = width;
		if(h > height) {
			w = height;
			h = height;
		}
		Rectangle rect = new Rectangle((width-w)/2, (height-h)/2, w, h);
		rect.grow(-4,  -4);//往里缩一点
		
		//计算每一个扇形的形状
		int start = 90;
		for(Part p : partList) {
			p.shape = new Arc2D.Double(rect, start, -p.degreee, Arc2D.PIE);
			start -= p.degreee;
		}
	}

	private static class Part{
		double amount;//该块区域所代表的数量
		int degreee; //根据amount经计算得到
		Color color; //图例颜色
		Object tag; //相关数据对象，由外部指定
		Arc2D shape; //实际绘制占据的形状
		boolean selected;//是否为选中状态
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//计算出每一份额的角度和扇形
		this.calculate();
		
		for(Part p : partList) {
			if(p.shape != null) {
				g2d.setPaint(p.color);
				g2d.fill(p.shape);
				
				if(p.selected) {
					g2d.setPaint(Color.YELLOW);
					g2d.setStroke(new BasicStroke(2));
					g2d.draw(p.shape);
				}
			}
		}
	}
}
