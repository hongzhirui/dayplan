package my;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class MyDragSourceListener implements DragSourceListener
{
	ImageBox source;
	int selectedIndex;
	
	public MyDragSourceListener(ImageBox source, int selectedIndex)
	{
		this.source = source;
		this.selectedIndex = selectedIndex;
	}
	
	@Override
	public void dragEnter(DragSourceDragEvent dsde)
	{
	}

	@Override
	public void dragOver(DragSourceDragEvent dsde)
	{
	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde)
	{		
	}

	@Override
	public void dragExit(DragSourceEvent dse)
	{		
	}

	@Override
	public void dragDropEnd(DragSourceDropEvent dsde)
	{
		// 先判断拖放操作是否成功完成 
		// 注：要在DropTargetListener.drop()里调用 dropComplete(true)
		if(dsde.getDropSuccess()) 
		{
			// 判断操作类型是 MOVE清空是COPY
			//if( dsde.getDropAction() == DnDConstants.ACTION_MOVE)
			{
				System.out.println("Remove After Drag: " + selectedIndex);
				
				source.setSlice(selectedIndex, null);
			}
		}
	}

}
