package my;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel
{
	private int NN = 4;//4*4方格
	
	int curRow, curCol;//当前位置
	Rectangle area; //中间可玩区域（正方形）
	Rectangle[][] grid = new Rectangle[NN][NN];//每个单元格
	
	public GamePanel() {
		this.setFocusable(true);//允许输入
		this.enableEvents(KeyEvent.MOUSE_EVENT_MASK);
		this.enableEvents(KeyEvent.KEY_EVENT_MASK);
	}
	
	@Override
	protected void processMouseEvent(MouseEvent e) {
		if(e.getID() == MouseEvent.MOUSE_CLICKED) {
			this.repaint();
		}
		
		super.processMouseEvent(e);
	}
	
	@Override
	protected void processKeyEvent(KeyEvent e) {
		if(e.getID() == KeyEvent.KEY_RELEASED) {
			int code = e.getKeyCode();
			System.out.println("Key:" + code);
			
			//响应上下左右键
			if(code == KeyEvent.VK_UP)
				curRow -= 1;
			else if(code == KeyEvent.VK_DOWN)
				curRow += 1;
			else if(code == KeyEvent.VK_LEFT)
				curCol -= 1;
			else if(code == KeyEvent.VK_RIGHT)
				curCol += 1;
			
			//位置检测（是否已到边缘
			if(curRow < 0) curRow = 0;
			if(curRow >= NN) curRow = NN -1;
			if(curCol < 0) curCol = 0;
			if(curCol >= NN) curCol = NN -1;
			
			repaint();
		}
		
		super.processKeyEvent(e);
	}
	
	//计算与测量：得到网格数据
	private void calculate() {
		int width = getWidth();
		int height = getHeight();
		
		//中间去一个正方形
		int size = width<height ? width : height;
		size -= 4;
		if(size > 200)
			size =200;
		
		int centerX = (width-size)/2;
		int centerY = (height-size)/2;
		area = new Rectangle(centerX, centerY, size, size);
		
		//每个小格的尺寸
		int unit = area.width / NN;
		
		//计算出每个单元格
		for(int row = 0; row<NN; row++) {
			for(int col = 0; col<NN; col++) {
				int x = (int) (area.x + col * unit);
				int y = (int) (area.y + row * unit);
				grid[row][col] = new Rectangle(x, y, unit, unit);
				grid[row][col].grow(-2, -2);//缩一点
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, width, height);
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		calculate();
		System.out.println("row=" + curRow + "," + curCol);
		
		//两种颜色：焦点色与无焦点状态
		Color focusColor = new Color(0x1E90FF);
		Color darkColor = new Color(0xAAAAAA);
		
		//填充白色
		g2d.setPaint(Color.WHITE);
		g2d.fill(area);
		
		//画边框
		if(this.hasFocus())
			g2d.setPaint(focusColor);
		else
			g2d.setPaint(darkColor);
		g2d.setStroke(new BasicStroke(1));
		g2d.draw(area);
		
		//计算
		for(int i=0; i<NN; i++) {
			for(int k=0; k<NN; k++) {
				Rectangle cell = grid[i][k];
				if(i == curRow && k == curCol) {
					g2d.fill(cell);
				}
			}
		}
		
	}

}
