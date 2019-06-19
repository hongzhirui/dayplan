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
	JList<String> source;
	int selectedIndex;

	public MyDragSourceListener(JList<String> source, int selectedIndex){
		this.source = source;
		this.selectedIndex = selectedIndex;
	}

	@Override
	public void dragEnter(DragSourceDragEvent dsde)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragOver(DragSourceDragEvent dsde)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragExit(DragSourceEvent dse)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragDropEnd(DragSourceDropEvent dsde)
	{
		// 先判断拖放操作是否成功完成 
		// 注：要在DropTargetListener.drop()里调用 dropComplete(true)
		if(dsde.getDropSuccess()) {
			// 判断操作类型是 MOVE清空是COPY
			if( dsde.getDropAction() == DnDConstants.ACTION_MOVE){
				System.out.println("Remove After Drag: " + selectedIndex);
						
				// 从源窗口里删除被拖拽走的项
				DefaultListModel model = (DefaultListModel)source.getModel();
				model.removeElementAt(selectedIndex  );
			}
		}
	}

	

}
