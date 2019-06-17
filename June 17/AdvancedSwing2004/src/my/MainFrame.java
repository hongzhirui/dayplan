package my;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
	my.DocumentView docView = new my.DocumentView();
	File file ;
	
	static int instanceCount = 0;
	
	public MainFrame(String title) {
		super(title);
		this.setContentPane(docView);		
		initMenuBar();
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// 窗口事件监听器
		this.addWindowListener( new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e)
			{
				instanceCount ++; // 窗口实例个数加1
			}
			
			@Override
			public void windowIconified(WindowEvent e)
			{
			}
			
			@Override
			public void windowDeiconified(WindowEvent e)
			{
			}
			
			@Override
			public void windowDeactivated(WindowEvent e)
			{
			}
			
			@Override
			public void windowClosing(WindowEvent e)
			{
			}
			
			@Override
			public void windowClosed(WindowEvent e)
			{	
				instanceCount --; // 窗口实例个数
				if (instanceCount == 0)
				{
					System.out.println("实例个数为0，退出程序");
					
					
					System.exit(0);
				}
			}
			
			@Override
			public void windowActivated(WindowEvent e)
			{				
			}
		});
	}
	
	private void initMenuBar(){
		// 菜单栏
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		
		// 菜单
		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);
		
		// 菜单项
		JMenuItem openFileCmd = new JMenuItem("Open");
		fileMenu.add(openFileCmd);
		openFileCmd.addActionListener((e)->{
			openDocument();
		});		
		
		JMenuItem exitCmd = new JMenuItem("Exit");		
		fileMenu.add(exitCmd);		
	}
	


	public void openDocument(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("图片文件", "jpg", "jpeg", "png");
		chooser.setFileFilter(filter);
		
		// 显示对话框
		int ret = chooser.showOpenDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION){
			// 结果为：已经存在的一个文件
			File file = chooser.getSelectedFile();
			openDocuement(file);
		}
	}
	public void openDocuement(File file)
	{		
		try{
			if(this.file == null){
				// 如果本窗口空闲，则在本窗口内打开
				this.file = file;
				docView.load( file );
			}
			else{
				// 创建一个新的 MainFrame 实例，在新的窗口里打开这个文件
				MainFrame frame = new MainFrame(file.getName());
				frame.setSize(400,300);
				frame.setVisible(true);
				
				frame.openDocuement( file );
			}
			
		} catch (Exception e){
			HzrToaster.show(this, HzrToaster.WARN, "出错 :" + e.getMessage());
		}
	}
}
