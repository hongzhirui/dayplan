package my;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class MyDropTargetListener implements DropTargetListener
{
	JList<String> target;
	
	public MyDropTargetListener(JList<String> target)
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragExit(DropTargetEvent dte)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(DropTargetDropEvent dtde)
	{
		Transferable transfer = dtde.getTransferable();
		DataFlavor flav = check(transfer);
		if(flav == null) return ;
		
		dtde.acceptDrop(DnDConstants.ACTION_MOVE);
		
		try {
			//调用getTransferData()来取得数据
			String data = (String) transfer.getTransferData(flav);
			DefaultListModel<String> model = (DefaultListModel)target.getModel();
			model.addElement(data);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		dtde.dropComplete(true);
	}

}
