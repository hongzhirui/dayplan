package my;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.w3c.dom.views.AbstractView;
import org.w3c.dom.views.DocumentView;

import hzr.swing.HzrToaster;

public class MainFrame extends JFrame
{
	JTabbedPane container = new JTabbedPane();
	
	public MainFrame(String title) {
		super(title);
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		initMenuBar();
		
		initContainer();
	}
	
	private void initMenuBar() {
		//菜单栏
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		
		//菜单
		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);
		
		//菜单项
		JMenuItem openFileCmd = new JMenuItem("Open");
		fileMenu.add(openFileCmd);
		openFileCmd.addActionListener( (e)->{
			openDocument();
		});
		
		JMenuItem exitCmd = new JMenuItem("Exit");
		fileMenu.add(exitCmd);
	}
	
	private void initContainer() {
		getContentPane().add(container, BorderLayout.CENTER);
	}
	
	public void openDocument() {
		//选择文件
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("图片文件", "jpg", "jpeg", "png");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(this);
		if(ret == JFileChooser.APPROVE_OPTION) {
			//结果为：已经存在的一个文件
			File file = chooser.getSelectedFile();
			openDocuement(file);
		}
	}
	
	public void openDocuement(File file) {
		//打开文件
		my.DocumentView view = new my.DocumentView();
		try{
			//添加选项卡
			view.load(file);
			container.addTab(view.getDocumentTitle(), view);//添加
			container.setSelectedComponent(view);//设为当前选项卡
			
			//设置自定义的选项卡标签
			int index = container.indexOfComponent(view);
			container.setTabComponentAt(index, new TabHeader(view, view.getDocumentTitle()));
		}catch(Exception e) {
			HzrToaster.show(this, HzrToaster.WARN, "打开出错:"+e.getMessage());
		}
	}
	
	//自定义选项卡标签
	private class TabHeader extends JPanel{
		Component tab;
		JLabel label = new JLabel();
		
		public TabHeader(Component tab, String title) {
			this.tab = tab;
			this.setPreferredSize(new Dimension(100, 20));
			this.add(label);
			label.setPreferredSize(new Dimension(100,20));
			label.setText(title);
			
			//鼠标点击事件
			this.addMouseListener(new MouseAdapter()
			{

				@Override
				public void mousePressed(MouseEvent e)
				{
					//左键：选中该选项卡
					if(e.getButton() == MouseEvent.BUTTON1) {
						container.setSelectedComponent(tab);
					}
					//右键：关闭该选项卡
					if(e.getButton() == MouseEvent.BUTTON3) {
						container.remove(tab);
					}
				}
			});
		}
		
	}

}
