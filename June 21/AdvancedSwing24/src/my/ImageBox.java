package my;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
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
		
		// 拖放支持
		initDragDrop();
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

	public void clear() {
		content = new ImageSlice[N * N];
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
	
	/////////// 拖放 ///////////////
	private void initDragDrop()
	{
		DragSource dragSource = DragSource.getDefaultDragSource(); 
		dragSource.createDefaultDragGestureRecognizer( 
				this, 
				DnDConstants.ACTION_COPY_OR_MOVE, 
				new MyDragGestureListener(this)   	
				);
  
		DropTarget dropTarget = new DropTarget(this	,
				new MyDropTargetListener(this));	
	}
	
	// 每个单元格城保存着小块图像的编号
	public ImageSlice getSlice(int index){
		return content[index];
	}
	
	public void setSlice(int index, ImageSlice slice){
		content[index] = slice;
		repaint();
	}
	
	// 拖放时，根据鼠标位置来确定单元格的索引
	public int locationToIndex(Point location){
		for(int i=0; i<cellArray.length; i++){
			if(cellArray[i].contains( location)){
				return i;
			}
		}
		return -1;
	}
	
	public boolean isComplete() {
		for(int i  = 0; i < N * N; i++) {
			ImageSlice slice = content[i];
			if(slice == null)
				return false;
			if(slice.id != i)
				return false;
		}
		return true;
	}
	
	///////////监听器支持///////////
	public interface StateListener{
		public void stateChanged(ImageBox box);
	}
	public StateListener stateListener;
	public void setStateListener(StateListener listener) {
		this.stateListener = listener;
	}
}
