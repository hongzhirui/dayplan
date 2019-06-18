package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
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
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hzr.swing.HzrImageView;
import hzr.swing.HzrToaster;

public class MainFrame extends JFrame
{
	HzrImageView imageView = new HzrImageView();
	
	public MainFrame(String title) {
		super(title);
		JPanel root = new JPanel();
		this.setContentPane(root);
		this.setLayout(new BorderLayout());
		
		this.add(imageView, BorderLayout.CENTER);
		imageView.setScaleType(HzrImageView.FIT_CENTER_INSIDE);
		
		//创建拖放目标
		DropTarget dropTarget = new DropTarget(root, new MyDropTargetListener());
	}
	
	public String getFileSuffix(String fileName) {
		int p = fileName.lastIndexOf('.');
		if(p >= 0) {
			return fileName.substring(p+1);
		}
		return "";
	}
	
	private void loadImageFile(File file){
		//获取文件的后缀名，并统一转成小写，以方便比较
		String suffix = getFileSuffix(file.getName()).toLowerCase();
		
		//检查后缀
		if( !suffix.equals("jpg") && !suffix.equals("jpeg") && !suffix.equals("png")) {
			HzrToaster.show(this, HzrToaster.ERROR, "不支持的文件格式：" + suffix);
			return ;
		}
		
		//加载文件并显示
		try {
			Image image = ImageIO.read(file);
			imageView.setImage(image);
		}catch(Exception e) {
			e.printStackTrace();
			HzrToaster.show(this, HzrToaster.ERROR, e.getMessage());
		}
	}
	
	
	//dragEnter:拖拽物进入
	//dragExit:拖拽物离开
	//dragOver:拖拽进行中
	//drop:拖拽物被丢进来
	private class MyDropTargetListener implements DropTargetListener{

		//在Transferable里检查有没有想要的类型 MIME类型：application/x-java-file-list; class=java.util.List
		private DataFlavor check(Transferable transfer) {
			DataFlavor flav = DataFlavor.javaFileListFlavor;
			//或者写出flav = new DataFlavor("application/x-java-file-list; class=java.util.List");
			if(transfer.isDataFlavorSupported(flav)) {
				return flav;
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
					List<File> files = (List)transfer.getTransferData(flav);
					loadImageFile(files.get(0));
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
