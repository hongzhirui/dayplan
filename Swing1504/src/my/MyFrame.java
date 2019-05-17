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
		nameListModel.addElement("东伯雪鹰");
		nameListModel.addElement("余靖秋");
		nameListModel.addElement("东伯烈");
		nameListModel.addElement("墨阳瑜");
		nameListModel.addElement("铜三");
		nameListModel.addElement("宗凌");
		nameListModel.addElement("东伯青");
		nameListModel.addElement("东伯玉");
		nameListModel.addElement("墨阳瑜");
		nameListModel.addElement("池丘白");
		nameListModel.addElement("贺山主");
		nameListModel.addElement("司空阳");		
		nameListModel.addElement("晁青");
		nameListModel.addElement("陈宫主");
		nameListModel.addElement("红尘圣主");
		nameListModel.addElement("血刃神帝");
		nameListModel.addElement("元初主人");
		nameListModel.addElement("魔祖");
		nameListModel.addElement("剑主");
		nameListModel.addElement("天愚");
		nameListModel.addElement("应山氏");
		nameListModel.addElement("南云");
		nameListModel.addElement("夏家");
		nameListModel.addElement("樊家");
		nameListModel.addElement("罗城主");
		nameListModel.addElement("元");

		nameList.setModel(nameListModel);
		nameList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		nameList.setVisibleRowCount(-1); // 不可缺少
		nameList.setLayoutOrientation(JList.VERTICAL_WRAP);//一列一列地排序
//		nameList.setLayoutOrientation(JList.HORIZONTAL_WRAP);//一行一行地排序
//		nameList.setLayoutOrientation(JList.VERTICAL);//排一列
		
		// 列表项选中事件
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
		
		
		JScrollPane listScrollPane = new JScrollPane(nameList);
		return listScrollPane;
	}
	
	private void selectChanged()
	{
		String item = nameList.getSelectedValue();
		System.out.println("选中了: " + item);		
	}
	
	
	
}
