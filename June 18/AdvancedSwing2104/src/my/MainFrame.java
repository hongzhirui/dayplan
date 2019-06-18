package my;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{
	JLabel label = new JLabel();
	public MainFrame(String title) {
		super(title);
		JPanel root = new JPanel();
		this.setContentPane(root);
		
		this.add(label);
		label.setFont(new Font("宋体", Font.PLAIN, 24));
		
		//创建拖放目标
		DropTarget dropTarget = new DropTarget(root, new MyDropTargetListener());
	}
	
	//dragEnter:拖拽物进入
	//dragExit:拖拽物离开
	//dragOver:拖拽进行中
	//drop:拖拽物被丢进来
	private class MyDropTargetListener implements DropTargetListener{

		//在Transferable里检查有没有想要的类型
		private DataFlavor check(Transferable transfer) {
			DataFlavor[] flavors = transfer.getTransferDataFlavors();
			for(DataFlavor flav : flavors) {
				String mimeType = flav.getMimeType();
				System.out.println("mimeType: " + mimeType);
				if(mimeType.startsWith("text/plain; class=java.lang.String;")) {
					return flav;
				}
			}
			return null;
		}
		
		@Override
		public void dragEnter(DropTargetDragEvent dtde)
		{
			//检查拖放进来的数据的类型
			//如果没有想要的类型，则调用dtde.rejectDrag()拒绝进入
			Transferable transfer = dtde.getTransferable();
			DataFlavor flav = check(transfer);
			if(flav == null) {
				dtde.rejectDrag();//没有需要的类型，拒绝进入
			}
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
			if( flav != null) {
				//必须先调用acceptDrop(),然后才能getTransferData()
				dtde.acceptDrop(DnDConstants.ACTION_COPY);
				try {
					//调用getTransferData()来取得数据
					String text = (String) transfer.getTransferData(flav);
					label.setText(text);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
