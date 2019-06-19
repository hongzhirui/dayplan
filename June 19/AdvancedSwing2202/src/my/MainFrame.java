package my;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame
{
	JList<String> list1 = new JList<>();//左侧列表：源窗口
	JList<String> list2 = new JList<>();//右侧列表：目标窗口
	
	public MainFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		//JSplitPane用来分割窗口
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		root.add(split, BorderLayout.CENTER);
		split.setLeftComponent(list1);
		split.setRightComponent(list2);
		split.setResizeWeight(0.5);//分割比例
		
		initDragSource();
		
		initDropTarget();
	}
	
	//设置拖放源窗口
	private void initDropTarget()
	{
		DefaultListModel<String> model = new DefaultListModel<>();
		model.addElement("林一");
		model.addElement("林二");
		model.addElement("林三");
		model.addElement("林四");
		model.addElement("林五");
		model.addElement("林六");
		model.addElement("林七");
		model.addElement("林八");
		list1.setModel(model);
		
		DragSource dragSource = DragSource.getDefaultDragSource();
		dragSource.createDefaultDragGestureRecognizer(list1, DnDConstants.ACTION_COPY_OR_MOVE, new MyDragGestureListener(list1));
	}
	
	//设置拖放目标窗口
	private void initDragSource()
	{
		DefaultListModel<String> model = new DefaultListModel<>();
		list2.setModel(model);
		
		DropTarget dropTarget = new DropTarget(list2, new MyDropTargetListener(list2));
		
	}
	
}
