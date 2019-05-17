package my;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

public class MyFrame extends JFrame
{

	DefaultListModel<String> nameListModel = new DefaultListModel<>();
	JList<String> nameList = new JList<>();
	
	public MyFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		JComponent listPane = initListView();
		
		//工具栏
		JToolBar toolBar = new JToolBar();
		root.add(toolBar, BorderLayout.PAGE_START);
		toolBar.setFloatable(false);
		
		JButton showButton = new JButton("查看选中");
		toolBar.add(showButton);
		showButton.addActionListener( (e)->{
			showSelection();
		});
		
		JButton removeButton = new JButton("删除选中");
		toolBar.add(removeButton);
		removeButton.addActionListener( (e)->{
			removeSelection();
		});
		
		//添加到主界面
		root.add(listPane, BorderLayout.CENTER);
	}

	private JComponent initListView() {
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
		
		JScrollPane listScrollPane = new JScrollPane(nameList);
		return listScrollPane;
	}
	
	//查看选中的项
	private void showSelection() {
		int[] indices = nameList.getSelectedIndices();
		
		System.out.println("共选中："+indices.length+" 项");
		ListModel<String> model = nameList.getModel();
		for(int index : indices) {
			String item = model.getElementAt(index);
			System.out.println("选中了："+item);
		}
	}
	
	//删除选中的项
	private void removeSelection() {
		List<String> items = nameList.getSelectedValuesList();
		for(String item : items) {
			nameListModel.removeElement(item);
		}
	}
}
