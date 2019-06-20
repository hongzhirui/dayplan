package my;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageBox extends JPanel
{
	int N = 3;//规格3*3
	
	Rectangle[] sliceArray ;
	Rectangle[] cellArray ;
	
	BufferedImage srcImage;
	Rectangle visualRect; // 中间可见区域
	
	ImageSlice[] content; // 每一个Cell里的内容
	
	public ImageBox(){
		try {
			srcImage = ImageIO.read(new File("images/sample.png"));			
		}catch(Exception e){
			e.printStackTrace();
		}
		random();//随机打乱
	}
	
	public void random(){
		content = new ImageSlice[N * N];
		for(int i=0; i<content.length; i++)
		{
			// 0,1,2,...,8 顺序排列
			content[i] = new ImageSlice(i);
		}
		
		// 随机打乱
		ImageUtils.randomSort( content);
		repaint();
	}

	
	private void calculate(){
		// 当前控件不是正方形，因此需要截取中间的正方形区域用于绘制
		int width = getWidth();
		int height = getHeight();
		visualRect = ImageUtils.crop( new Rectangle(20,20,width-40, height-40) );
		cellArray = ImageUtils.split (visualRect, N, N);
				
		// 当前图片也可能不是正方形，为防止比例失调，截取中间的正方形图片
		int imgW = srcImage.getWidth(null);
		int imgH = srcImage.getHeight(null);
		Rectangle imgRect = ImageUtils.crop ( new Rectangle(imgW, imgH) );
		sliceArray = ImageUtils.split(imgRect, N, N);		
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int width = this.getWidth();
		int height = this.getHeight();
		Graphics2D g2d = (Graphics2D) g;
		Rectangle rect = new Rectangle(width, height);
		
		// 计算
		calculate();
		
		// 截取一个大正方形		
		g2d.setPaint( Color.WHITE);
		g2d.fill( visualRect );

		for(int i = 0; i < N*N; i++){
			Rectangle dst = cellArray[i];
			ImageSlice slice = content[i];
			if(slice != null){
				int sliceID = slice.id; //编号
				Rectangle src = sliceArray[sliceID];
				g2d.drawImage(srcImage, 
						dst.x, dst.y, dst.x + dst.width, dst.y + dst.height,
						src.x, src.y, src.x + src.width, src.y + src.height,
						null);			
			}
			g2d.setPaint(new Color(240,240,240,80));
			g2d.setStroke(new BasicStroke(1));
			g2d.draw( dst );
		}		
	}
}
