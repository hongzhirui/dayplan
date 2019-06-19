package my;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import javax.swing.JList;

public class MyDragGestureListener implements DragGestureListener
{
	JList<String> source = new JList<>();


	public MyDragGestureListener(JList<String> source) {
		this.source = source;
	}
	
	//根据拖放的起始位置，找到被拖放的项
	public int getLocationIndex(JList list, Point pos) {
		//locationToIndex()只是返回最近的索引，需要校正一下
		int index = list.locationToIndex(pos);
		if(index >= 0) {
			//进一步确认
			//getCellBounds()返回相应项的位置
			Rectangle rect = list.getCellBounds(index, index);
			if(rect.contains(pos)) {
				return index;
			}
		}
		return -1;//未点中有效的索引
	}
	
	@Override
	public void dragGestureRecognized(DragGestureEvent dge)
	{
		Point pos = dge.getDragOrigin();//拖拽的起点
		int index = getLocationIndex(source, pos);
		if(index < 0) return ;
		
		String value = source.getSelectedValue();
		Transferable transfer = new StringSelection(value);
		dge.startDrag(DragSource.DefaultMoveDrop, transfer, new MyDragSourceListener(source, index));
	}

}
