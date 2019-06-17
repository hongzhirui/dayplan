package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.w3c.dom.views.AbstractView;
import org.w3c.dom.views.DocumentView;

import hzr.swing.HzrToaster;

public class MainFrame extends JFrame
{
	JDesktopPane desktop = new JDesktopPane();
	
	public MainFrame(String title) {
		super(title);
		this.setContentPane(desktop);
		
		initMenuBar();
		
		desktop.setOpaque(true);
		desktop.setBackground(new Color(0x606060));
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
	
	public void openDocument() {
		//选择文件
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("图片文件", "jpg", "jpeg", "png");
		chooser.setFileFilter(filter);
		
		//显示对话框
		int ret = chooser.showOpenDialog(this);
		if(ret == JFileChooser.APPROVE_OPTION) {
			//结果为：已经存在的一个文件
			File file = chooser.getSelectedFile();
			openDocuement(file);
		}
	}
	
	public void openDocuement(File file)
	{
		DocumentFrame frame = new DocumentFrame(file);
		try{
			frame.load();						
		    desktop.add(frame); // 将子窗口添加到容器   
		   
		    frame.setClosable(true); // 可关闭的
		    frame.setMaximizable(true);// 可最大化的	  	   
		    frame.setTitle(file.getName()); // 子窗口标题
			
		     
		    frame.setSelected(true);
		    frame.setSize(400, 300);	
		    frame.setResizable(true); // 可改变大小的
		    
		    frame.setVisible(true);
		    frame.setMaximum(true); // 设置为最大化
		    
		} catch (Exception e)
		{
			HzrToaster.show(this, HzrToaster.WARN, "出错 :" + e.getMessage());
		}
	}
	
	

}
