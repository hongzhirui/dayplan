package my;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ImageTransfer implements Transferable
{
	DataFlavor[] supportedFlavors = { DataFlavor.imageFlavor };
	Image image;//要传递的图片
	
	public ImageTransfer(Image image) {
		this.image = image;
	}

	//这个拖拽里包含了哪些数据类型（一个Transferable里通常含有很多条记录）
	@Override
	public DataFlavor[] getTransferDataFlavors()
	{
		// TODO Auto-generated method stub
		return supportedFlavors;
	}

	//判断是否支持某种类型
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor)
	{
		for(DataFlavor f : supportedFlavors) {
			if(f.equals(flavor)) {
				return true;
			}
		}
		return false;
	}

	//从拖拽物里，按类型获取一条记录
	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
	{
		if(flavor.equals(DataFlavor.imageFlavor)) {
			return image;
		}
		return null;
	}

}
