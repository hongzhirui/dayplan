package my;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.image.BufferedImage;

import javax.swing.JList;

public class MyDragGestureListener implements DragGestureListener
{ 
	ImageBox source;
	
	public MyDragGestureListener(ImageBox source){
		this.source = source;
	}
	
	@Override
	public void dragGestureRecognized(DragGestureEvent dge)
	{
		Point pos = dge.getDragOrigin(); // 拖拽的起点
		int index = source.locationToIndex(pos);
		if(index < 0) return;
		
		ImageSlice slice = source.getSlice(index);
		if(slice == null) return;// 单元格里没有图像
		
		String value = String.valueOf(slice.id);
		Transferable transfer = new StringSelection(value);
		
		dge.startDrag(DragSource.DefaultMoveDrop,
				transfer,
				new MyDragSourceListener(source, index));
		
//		BufferedImage image = source.getContentImage(slice);
//		int imgW = image.getWidth();
//		int imgH = image.getHeight();
//		dge.startDrag(DragSource.DefaultMoveDrop
//				, image
//				, new Point(0,0)
//				, transfer
//				, new MyDragSourceListener(source, index));
		
	}		
}
