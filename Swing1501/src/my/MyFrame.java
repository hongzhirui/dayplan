package my;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		//准备要显示的数据   Model存储数据的模型
		DefaultListModel<String> listModel = new DefaultListModel<>();
		listModel.addElement("a");
		listModel.addElement("b");
		listModel.addElement("c");
		listModel.addElement("d");
		listModel.addElement("e");
		listModel.addElement("f");
		listModel.addElement("g");
		listModel.addElement("h");
		listModel.addElement("i");
		
		//创建列表
		JList<String> nameList = new JList<>();
		nameList.setModel(listModel);
		nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//把列表放在Scroll Pane里面  滚动条
		JScrollPane listScrollPane = new JScrollPane(nameList);
		
		//添加到主界面
		root.add(listScrollPane, BorderLayout.CENTER);
	}

}
