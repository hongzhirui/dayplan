package my;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyFrame extends JFrame
{
	JList<String> nameList = new JList<>();
	DefaultListModel<String> nameListModel = new DefaultListModel<>();
	
	public MyFrame(String title)
	{
		super(title);

		// Content Pane
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());

		// 初始化列表控件
		JComponent listPane = initListView();
		
		// 添加到主界面
		root.add(listPane, BorderLayout.CENTER);
	}

	private JComponent initListView()
	{
		nameListModel.addElement("a");
		nameListModel.addElement("b");
		nameListModel.addElement("c");
		nameListModel.addElement("d");
		nameListModel.addElement("e");
		nameListModel.addElement("f");
		nameListModel.addElement("g");
		nameListModel.addElement("h");
		nameListModel.addElement("i");

		nameList.setModel(nameListModel);
		nameList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	
		// 列表项选中事件/鼠标左键
		nameList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				// getValueIsAdjusting() 为true时，表示变化尚未结束
				if(! e.getValueIsAdjusting())
				{
					selectChanged();
				}				
			}
		});
		// 鼠标右键事件
		nameList.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(e.getButton() == MouseEvent.BUTTON3)
					showContextMenu (e);
			}			
		});
		
		JScrollPane listScrollPane = new JScrollPane(nameList);
		return listScrollPane;
	}
	
	private void selectChanged()
	{
		String item = nameList.getSelectedValue();
		System.out.println("选中了: " + item);		
	}
	
	private void showContextMenu(MouseEvent e)
	{
		// 先检查点中的是哪一项
		// 根据鼠标点中的位置e.getPoint()，计算出被点中的是哪一项
		int index = nameList.locationToIndex(e.getPoint());
		if(index < 0) return;
		
		// 选中该项 （ 默认的，右键不会选中，所以这里要手工选中一下 )
		nameList.setSelectedIndex(index);
		
		// 弹出右键菜单
		JPopupMenu menu = new JPopupMenu();
		JMenuItem detailMenuCmd = new JMenuItem("查看详情");
		JMenuItem favorMenuCmd = new JMenuItem("关注");	
		menu.add(detailMenuCmd);
		menu.add(favorMenuCmd);
		
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
	
}
