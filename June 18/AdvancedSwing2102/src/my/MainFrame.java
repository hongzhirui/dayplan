package my;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{
	public MainFrame(String title) {
		super(title);
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("hongzhirui.cn");
		root.add(label);
		label.setOpaque(true);
		label.setBackground(Color.GRAY);
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//创建拖放源
		DragSource dragSource = DragSource.getDefaultDragSource();
		
		//第一个参数：源窗口
		//第二个参数：允许的操作类型ACTION_COPY / ACTION_MOVE
		//第三个参数：监听器
		dragSource.createDefaultDragGestureRecognizer(label, DnDConstants.ACTION_COPY, new MyDragGestureListener(label.getText()));
	}
	
	private class MyDragGestureListener implements DragGestureListener{
		String test;
		
		public MyDragGestureListener(String test) {
			this.test = test;
		}
		
		@Override
		public void dragGestureRecognized(DragGestureEvent dge)
		{
			//定义要拖放的数据，StringSelection表示文本数据
			Transferable transfer = new StringSelection(test);//拖放的文件内容
			dge.startDrag(DragSource.DefaultCopyDrop, transfer);//开启拖放
			//拖放过程中按Esc键可以取消拖放
		}
		
	}

}
