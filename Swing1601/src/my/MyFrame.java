package my;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import org.omg.CORBA.INITIALIZE;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		//准备要显示的数据   Model存储数据的模型
		DefaultListModel<String> listModel = new DefaultListModel<>();
		listModel.addElement("东伯雪鹰");
		listModel.addElement("余靖秋");
		listModel.addElement("东伯烈");
		listModel.addElement("墨阳瑜");
		listModel.addElement("铜三");
		listModel.addElement("宗凌");
		listModel.addElement("东伯青");
		listModel.addElement("东伯玉");
		listModel.addElement("墨阳瑜");
		listModel.addElement("池丘白");
		listModel.addElement("贺山主");
		listModel.addElement("司空阳");	
				
		//创建列表
		JList<String> nameList = new JList<>();
		nameList.setModel(listModel);
		nameList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				
		//把列表放在Scroll Pane里面  滚动条
		JScrollPane listScrollPane = new JScrollPane(nameList);
		
		//设置单元格绘制器
		ListCellRenderer renderer = new MyListCellRenderer();
		nameList.setCellRenderer(renderer);
		//添加到主界面
		root.add(listScrollPane, BorderLayout.CENTER);
	}
	
	private static class MyListCellRenderer implements ListCellRenderer{

		JLabel label = new JLabel();
		
		//list：列表控件
		//value：列表项的值
		//index：索引
		//isSelected：该项是否被选中
		//cellHasFocus：该项是否为焦点行（多选模式下，有的选项被选中，但不是焦点行）
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus)
		{
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setText(value.toString());
			label.setBorder(BorderFactory.createEmptyBorder(10, 4, 10, 4));
			
			label.setOpaque(true);
			
			if(isSelected) {
				label.setBackground(list.getSelectionBackground());
				label.setForeground(list.getSelectionForeground());
			}else {
				label.setBackground(list.getBackground());
				label.setForeground(list.getForeground());
			}
			
			return label;
		}
		
	}

}
