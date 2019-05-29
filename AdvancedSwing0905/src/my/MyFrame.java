package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import af.swing.thread.AfShortTask;


public class MyFrame extends JFrame
{
	JPanel root = new JPanel(); // Content Pane
	JTable table ; // 表格
	DefaultTableModel tableModel ; // 表格的Model
		
	WaitDialog waitDialog = null;
	File srcFile; // 选中的*.zip文件
	ZipInfo zipInfo; // zip文件的信息
	
	public MyFrame(String title)
	{
		super(title);

		// Content Pane		
		this.setContentPane(root);
		root.setLayout(new BorderLayout());

		// 初始化表格
		this.initTable();
		
		// 初始化工具栏
		this.initToolBar();
	}

	private void initTable()
	{
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel){
			@Override
			public boolean isCellEditable(int row, int column)
			{
				// 直接重写 isCellEditable()，设为不可编辑
				return false;
			}			
		};
		
		// 把 table 放在 Scroll Pane 以支持滚动条
		JScrollPane scrollPane = new JScrollPane(table);
		root.add(scrollPane, BorderLayout.CENTER);
		
		// 添加到主界面		
		table.setFillsViewportHeight(true);		
		table.setRowSelectionAllowed(true); // 整行选择
		table.setRowHeight(30);	
		
		// 列设置：添加5列
		tableModel.addColumn (" ");
		tableModel.addColumn ("名称");
		tableModel.addColumn ("大小");
		tableModel.addColumn ("修改时间");
		
		// 列设置：自定义绘制
		table.getColumnModel().getColumn(0).setCellRenderer(new IconColumnRenderer());
		table.getColumnModel().getColumn(0).setMaxWidth(40); // 该列的宽度		
		table.getColumnModel().getColumn(2).setMaxWidth(120); // 该列的宽度		
		table.getColumnModel().getColumn(3).setMaxWidth(160); // 该列的宽度		
		table.getColumnModel().getColumn(3).setMinWidth(160); // 该列的宽度		
	}
	
	private void initToolBar()
	{
		JToolBar toolBar = new JToolBar();
		root.add(toolBar, BorderLayout.PAGE_START);
		toolBar.setFloatable(false);
		
		// 按钮
		JButton b1 = new JButton("打开");
		b1.setFocusPainted(false);
		toolBar.add(b1);
		b1.addActionListener( (e)->{
			onFileOpen();
		});
		
		// 按钮
		JButton b2 = new JButton("解压缩");
		b1.setFocusPainted(false);
		toolBar.add(b2);
		b2.addActionListener( (e)->{
			onFileExtract();
		});
	}
	
	// '打开' 按钮
	private void onFileOpen()
	{
		// Swing入门篇 13.3讲
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("ZIP文件", "zip");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File(".")); // 指定初始显示目录为当前目录
		
		int ret = chooser.showOpenDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			this.srcFile = chooser.getSelectedFile();
			
			// 启动一个工作线程
			ZipInfoTask task = new ZipInfoTask();
			task.execute(srcFile);
			
			waitDialog = new WaitDialog(this);
			waitDialog.exec();
		}
	}

	// '解压缩' 按钮
	private void onFileExtract()
	{
		if(this.srcFile == null) return; // 尚未打开源文件
		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int ret = chooser.showOpenDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			// 结果为： 已经存在的一个目录
			File dir = chooser.getSelectedFile();
			ZipExtractTask task = new ZipExtractTask(this);
			task.execute(this.srcFile, dir, zipInfo);
			
			waitDialog = new WaitDialog(this);
			waitDialog.exec();
		}
	}
	
	// 显示所有的zip条目 
	private void showEntries(List<ZipEntry> entries)
	{
		tableModel.setNumRows(0); // 清空
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for(ZipEntry entry: entries)
		{
			Vector<Object> rowData = new Vector<>();
			rowData.add(entry.isDirectory() );
			rowData.add(entry.getName());
			rowData.add(entry.getSize());
			long lastModified = entry.getLastModifiedTime().toMillis();
			rowData.add( sdf.format( lastModified ));
			
			tableModel.addRow( rowData ); // 添加一行
		}				
	}
	
	// 短任务：负责解出zip文件中的条目信息
	private class ZipInfoTask extends AfShortTask
	{
		//ZipInfo zipInfo ; // MyFrame.zipInfo
		
		@Override
		protected void doInBackground() throws Exception
		{
			File file = (File)this.args[0]; // 取得execute()的参数
			ZipFile zipFile = new ZipFile(file, Charset.forName("GBK"));
			try {
				zipInfo = ZipInfo.from(zipFile);				
			}finally {
				zipFile.close(); // 记得关闭ZipFile
			}
			
			zipInfo.sort(); // 排序：显示的时候文件夹在前显示
		}

		@Override
		protected void done()
		{
			// 判断解压缩过程有没有出错
			if(this.err != null) // this.err是doInBackground()里抛出的异常
			{
				this.err.printStackTrace();
				return;
			}
			
			// 关闭对话框
			if(waitDialog!=null)
			{
				waitDialog.setVisible(false);
				waitDialog = null;
			}
			
			// 把条目显示到表格里
			showEntries( zipInfo.entries);
		}		
	}
	
	
}
