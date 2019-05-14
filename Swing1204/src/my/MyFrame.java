package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicMenuItemUI;

public class MyFrame extends JFrame
{
	JPopupMenu popup = new JPopupMenu();
	
	public MyFrame(String title)
	{
		super(title);

		// Content Pane
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		// 创建工具栏
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false); // 让工具栏固定、不允许浮动
		root.add(toolBar, BorderLayout.PAGE_START);
				
		// 向工具栏上添加按钮
		toolBar.add( toolButton("ic_open.png", "fileOpen","打开"));
		toolBar.add( toolButton("ic_save.png", "fileSave","保存"));
		toolBar.add( toolButton("ic_saveas.png","fileSaveAs","另存为"));
		toolBar.addSeparator();
		toolBar.add( toolButton("ic_help.png", "fileHelp","帮助"));

		// 右键菜单 
		popup.add(createMenuItem("打开", "fileOpen", "ic_open.png"));
		popup.add(createMenuItem("保存", "fileSave", "ic_save.png"));
		popup.add(createMenuItem("另存为", "fileSaveAs", null ));

		// 定制右键菜单的边框
		Border bd1 = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border bd2 = BorderFactory.createEmptyBorder(1,1,1,1);
		popup.setBorder(BorderFactory.createCompoundBorder(bd1, bd2));
		
		// 添加右键事件响应，点击右键时，弹出菜单
		root.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(e.getButton()==MouseEvent.BUTTON3) //
				{
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}			
		});
	}
	
	// 创建工具栏上的按钮
	protected JButton toolButton(String imageName, String action, String tooltip)
	{
		// 图标
		String imagePath = "/icons/" + imageName;
		URL imageURL = getClass().getResource(imagePath);

		// 创建按钮
		JButton button = new JButton();
		button.setActionCommand(action);
		button.setToolTipText(tooltip);//添加提示
		button.setIcon(new ImageIcon(imageURL));
				
		button.addActionListener( actionListener );
		return button;
	}	

	private JMenuItem createMenuItem(String text, String action, String iconName)
	{		
		JMenuItem item = new JMenuItem(text);
		item.setActionCommand(action);
		item.addActionListener(actionListener );
		if(iconName != null)
		{
			String imagePath = "/icons/" + iconName;
			URL imageURL = getClass().getResource(imagePath);
			item.setIcon(new ImageIcon(imageURL));
		}
		
		// 菜单项外观设置
		item.setUI(new MyMenuItemUI());
		item.setFont(new Font("宋体",0,12));
		item.setBorder(BorderFactory.createEmptyBorder(4,2,4,6));
		return item;
	}

	// 创建一个菜单项的 UI类，设定选中时的前景色和背景色
	private static class MyMenuItemUI extends BasicMenuItemUI
	{
		public MyMenuItemUI()
		{
			this.selectionForeground = new Color(0x666666);
			this.selectionBackground = new Color(0x91C9F7);
		}
	}
	
	
	// 创建一个事件监听器
	private ActionListener actionListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String action = e.getActionCommand();
			System.out.println("执行命令: " + action);
			
			if(action.equals("fileOpen"))
			{
				JOptionPane.showMessageDialog(MyFrame.this, action);
			}			
		}		
	};

}
