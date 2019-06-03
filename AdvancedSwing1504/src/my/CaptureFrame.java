package my;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CaptureFrame extends JFrame
{
	MainFrame mainFrame;
	BufferedImage image;
	
	public CaptureFrame(MainFrame mainFrame, BufferedImage image) {
		
		this.setTitle("截图编辑窗口");
		this.setSize(800, 600);
		
		//设为全屏显示
		this.setUndecorated(true);
		GraphicsDevice gd = getGraphicsConfiguration().getDevice();
		gd.setFullScreenWindow(this);
		
		this.mainFrame = mainFrame;
		this.image = image;
		CapturePanel root = new CapturePanel();
		this.setContentPane(root);
	}

	private class CapturePanel extends JPanel{
		boolean pressed=false;//是否为按下的状态
		Point cursorPos;//鼠标按下的起点
		Point startPos;//当前鼠标位置
		
		public CapturePanel() {
			MyMouseAdapter mouseAdapter = new MyMouseAdapter();
			this.addMouseListener(mouseAdapter);
			this.addMouseMotionListener(mouseAdapter);
		}
		
		//已知起点和终点，求它们围成的矩形
		//注意没有说明p1在上还是p2在上
		private Rectangle getArea(Point p1, Point p2) {
			int x = p1.x;
			int y = p1.y;
			int w = p2.x - p1.x;
			int h = p2.y - p1.y;
			if(w < 0) {
				x = p2.x;
				w = -w;
			}
			if(h < 0) {
				y = p2.y;
				h = -h;
			}
			return new Rectangle(x, y, w, h);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			int width = getWidth();
			int height = getHeight();
			Graphics2D g2d = (Graphics2D)g;
			
			//绘制图片
			if(image != null) {
				g2d.drawImage(image, 0, 0, null);
			}
			
			//参考线
			if(cursorPos != null) {
				int cx = (int)cursorPos.getX();
				int cy = (int)cursorPos.getY();
				g2d.setColor(Color.gray);
				g2d.drawLine(0, cy, width, cy);
				g2d.drawLine(cx, 0, cx, height);
				
				//中间画一个小圆圈
				Ellipse2D circle = new Ellipse2D.Double(cx-2, cy-2, 4, 4);
				g2d.setPaint(Color.white);
				g2d.fill(circle);
				g2d.setPaint(Color.gray);
				g2d.draw(circle);
			}
			
			//绘制选中的区域
			if(pressed) {
				Rectangle area = getArea(startPos, cursorPos);
				g2d.setPaint(new Color(100, 100, 100, 100));
				g2d.fill(area);
			}
		}
		
		private class MyMouseAdapter extends MouseAdapter{
			@Override
			public void mouseMoved(MouseEvent e) {
				cursorPos = e.getPoint();
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				if(e.getButton() == MouseEvent.BUTTON1) {
					System.out.println("mousePressed");
					pressed = true;
					cursorPos = startPos = e.getPoint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				System.out.println("mouseReleased");
				//鼠标松开时，完成区域选取。此时应关闭本窗口，把选中的区域回传给主窗口
				if(pressed) {
					pressed = false;
					cursorPos = e.getPoint();
					
					//取得图片，回传给主窗口
					CaptureFrame.this.setVisible(false);
					Rectangle area = getArea(startPos, cursorPos);
					mainFrame.setVisible(true);
					mainFrame.showImage(image, area);
				}
			}

			@Override
			public void mouseDragged(MouseEvent e)
			{
				if(pressed) {
					cursorPos = e.getPoint();
					repaint();
				}
			}
		}
	}
	
}
