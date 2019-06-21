package my;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class MyDropTargetListener implements DropTargetListener 
{
	ImageBox target;
	
	public MyDropTargetListener(ImageBox target)
	{
		this.target = target;		
	}
	
	private DataFlavor check( Transferable transfer )
	{
		DataFlavor flav = DataFlavor.stringFlavor;						
		if(transfer.isDataFlavorSupported(flav))
		{
			return flav;
		}			
		return null;
	}
	
	@Override
	public void dragEnter(DropTargetDragEvent dtde)
	{
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde)
	{	
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde)
	{
	}

	@Override
	public void dragExit(DropTargetEvent dte)
	{			
	}

	@Override
	public void drop(DropTargetDropEvent dtde)
	{				
		Transferable transfer = dtde.getTransferable();
		DataFlavor flav = check( transfer );
		if( flav == null) return;
		
		dtde.acceptDrop( DnDConstants.ACTION_MOVE);	

		try
		{
			// 计算目标单元格的索引
			int targetIndex = target.locationToIndex( dtde.getLocation());
			if(targetIndex< 0)
			{
				dtde.dropComplete(false);
				return;
			}
			// 判断目标单元格内是否已经存在Slice，如果已存在，则拖放失败
			ImageSlice targetSlice = target.getSlice(targetIndex);
			if(targetSlice != null)				
			{
				System.out.println("目标单元格" + targetIndex + "不为空!");
				dtde.dropComplete(false);
				return;
			}
			// 调用 getTransferData() 来取得数据
			String data = (String) transfer.getTransferData(flav);
			int id = Integer.valueOf(data);
			
			target.setSlice(targetIndex, new ImageSlice(id));
			dtde.dropComplete(true);
			
			//调用自定义事件监听器
			if(target.stateListener != null) {
				target.stateListener.stateChanged(target);
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}				

	}		
}
