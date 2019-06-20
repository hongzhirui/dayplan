package my;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class MyLabel extends JLabel
{
	public MyLabel(String title, Color bgColor) {
		super(title);
		this.setOpaque(true);
		this.setBackground(bgColor);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		DragSource dragSource = DragSource.getDefaultDragSource();
		
		dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, new MyDragGestureListener());
	}
	
	public static BufferedImage capture(Component comp) {
		int imgW = comp.getWidth();
		int imgH = comp.getHeight();
		BufferedImage image = new BufferedImage(imgW, imgH, BufferedImage.TYPE_3BYTE_BGR);
		//绘制
		Graphics2D g2d = image.createGraphics();
		g2d.setBackground(comp.getBackground());
		g2d.setFont(comp.getFont());
		g2d.setPaint(comp.getForeground());
		comp.paint(g2d);
		g2d.dispose();
		return image;
	}

	private class MyDragGestureListener implements DragGestureListener{

		@Override
		public void dragGestureRecognized(DragGestureEvent dge)
		{
			//获取源窗口
			JLabel label = (JLabel) dge.getComponent();
			
			Image image = capture(label);
			Transferable transfer = new ImageTransfer(image);
			
			dge.startDrag(DragSource.DefaultCopyDrop, transfer);
		}
		
	}
}
