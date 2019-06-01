package my;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
	Game game = new Game();
	
	Rectangle area;//中间可玩区域（正方形）
	Rectangle[][] grid;//每个单元格
	Image icChild, icBox, icTarger; //角色图标
	String display = "";//状态提示
	Image bgImage;
	
	public GamePanel() {
		this.setFocusable(true);
		this.enableEvents(KeyEvent.MOUSE_EVENT_MASK);
		this.enableEvents(KeyEvent.KEY_EVENT_MASK);
		
		//添加图标
		try {
			icChild = ImageIO.read(getClass().getResource("/icons/ic_child.png"));
			icBox = ImageIO.read(getClass().getResource("/icons/ic_box.png"));
			icTarger = ImageIO.read(getClass().getResource("/icons/ic_target.png"));
		}catch(Exception e) {
			e.printStackTrace();
		}
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
		if(e.getID() == KeyEvent.KEY_PRESSED) {
			int code = e.getKeyCode();
			
			int dx=0, dy=0;
			
			if(code == KeyEvent.VK_UP)
				dy -= 1;
			else if(code == KeyEvent.VK_DOWN)
				dy += 1;
			else if(code == KeyEvent.VK_LEFT)
				dx -= 1;
			else if(code == KeyEvent.VK_RIGHT)
				dx += 1;
			
			if(game.canMove(dx, dy)) {
				game.move(dx, dy);
			}
			else {
				display = "不能移动！前方有障碍物或墙壁!";
			}
			if(game.getStatus() == 100)
				display = "游戏结束，恭喜通关！";
			
			repaint();
		}
		super.processKeyEvent(e);
	}
	
	//计算与测量：得到网络数据
	private void calculate() {
		int width = getWidth();
		int height = getHeight();
		int NN = game.NN;
		grid = new Rectangle[NN][NN];
		
		//中间取一个正方形
		int unit = 20;
		int size = unit * NN;
		int centerX = (width - size) / 2;
		int centerY = (height - size) / 2;
		area = new Rectangle(centerX, centerY, size, size);
		
		//计算出每个单元格
		for(int row = 0; row<NN; row++) {
			for(int col = 0; col<NN; col++) {
				int x = (int)(area.x+col*unit);
				int y = (int)(area.y+row*unit);
				grid[row][col] = new Rectangle(x, y, unit, unit);
			}
		}
	}
	
	public void setBgImage(Image image) {
		this.bgImage = image;
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D)g;
		g2d.clearRect(0, 0, width, height);
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//绘制背景
		if(bgImage != null) {
			g2d.drawImage(bgImage, 0, 0, width, height, null);
			g2d.setPaint(new Color(255, 255, 255, 150));//半透明
			g2d.fillRect(0, 0, width, height);
		}
		
		calculate();
		
		//两种颜色
		Color focusColor = new Color(0xC1CDCD);
		Color darkColor = new Color(0xAAAAAA);
		
		//绘制场地
		g2d.setPaint(Color.WHITE);
		g2d.fill(area);
		
		if(this.hasFocus())
			g2d.setPaint(focusColor);
		else
			g2d.setPaint(darkColor);
		
		//绘制场景
		int NN = game.NN;
		for(int i=0; i<NN; i++) {
			for(int k=0; k<NN; k++) {
				Rectangle r = grid[i][k];
				Game.Cell cell = game.getCell(i, k);
				
				//绘制障碍
				if(cell.type == Game.Role.STONE) {
					Rectangle r2 = new Rectangle(r);
					r2.grow(-1, -1);
					g2d.fill(r2);
				}
				
				//绘制角色图标
				Image icon = null;
				if(cell.type == Game.Role.CHILD)
					icon = icChild;
				if(cell.type == Game.Role.BOX)
					icon = icBox;
				if(cell.type == Game.Role.TARGET)
					icon = icTarger;
				if(icon != null)
					g2d.drawImage(icon, r.x, r.y, r.width, r.height, null);
			}
		}
		
		//状态提示
		if(display != null) {
			g2d.setFont(g2d.getFont().deriveFont(20.0f));
			FontMetrics fm = g2d.getFontMetrics(g2d.getFont());
			int fontSize = fm.getHeight();
			int textWidth = fm.stringWidth(display);
			
			g2d.setPaint(new Color(0x333333));
			g2d.drawString(display, (width - textWidth)/2, fontSize + 50);
			display="";
		}
		
	}

}
