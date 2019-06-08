package my;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import hzr.swing.HzrPanel;
import leftmenu.LeftMenuCellRenderer;
import leftmenu.LeftMenuItem;
import views.ManageView;
import views.SystemView;
import views.UserView;


public class MainFrame extends JFrame
{
	JList<LeftMenuItem> leftMenu = new JList<>();
	JPanel container = new JPanel();	
	
	public MainFrame(String title)
	{
		super(title);

		// Content Pane	
		HzrPanel root = new HzrPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());	
		
		
		initLeftMenu();	
		initContainer();
		
		// 点击左侧菜单项时，右侧切换显示相应的View
		leftMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				int index = leftMenu.locationToIndex(e.getPoint());
				if(index >=0) selectView(index);
			}			
		});
	}
	
	private void initLeftMenu()
	{
		DefaultListModel<LeftMenuItem> listModel = new DefaultListModel<>();
		listModel.addElement(new LeftMenuItem("内容管理"));
		listModel.addElement(new LeftMenuItem("系统设定"));
		listModel.addElement(new LeftMenuItem("个人设定"));
		
		leftMenu.setModel(listModel);
		leftMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		leftMenu.setCellRenderer(new LeftMenuCellRenderer());
		// 
		leftMenu.setOpaque(true);
		leftMenu.setBackground(new Color(0x2F4056));
	
		// 3 把列表放在 Scroll Pane里 
		JScrollPane listScrollPane = new JScrollPane(leftMenu);
		this.getContentPane().add(listScrollPane, BorderLayout.LINE_START);
	}
	
	private void initContainer()
	{		
		// 设置为卡片布局
		CardLayout cardLayout = new CardLayout();
		container.setLayout(cardLayout);		
		container.setOpaque(true);
		container.setBackground(new Color(0xF4F4F4));
		container.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, new Color(0xCCCCCC)));

		this.getContentPane().add(container, BorderLayout.CENTER);	//AfRowLayout
				
		// 添加多个卡片
		container.add(new ManageView(), "view0");
		container.add(new SystemView(), "view1");
		container.add(new UserView(), "view2");
		
		// 默认显示第一张卡片	
		selectView(0);		
	}
	
	// 切换卡片 
	public void selectView (int index )
	{
		String name = "view" + index; // 卡片的名称 view0, view1, ...
		
		CardLayout cardLayout = (CardLayout)container.getLayout();
		cardLayout.show(container, name); // 根据名字来显示相应的卡片
		
		if(leftMenu.getSelectedIndex() != index)
		{
			leftMenu.setSelectedIndex(index);
		}
	}

}
