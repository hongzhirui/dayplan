package my;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyFrame extends JFrame
{
	JButton okButton = new JButton("开始");
	WaitDialog waitDialog = null;
	
	public MyFrame(String title) {
		super(title);
		
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new FlowLayout());
		
		root.add(okButton);
		
		okButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e)
			{
				onMouseClicked();
			}
			
		});
	}
	
	//按钮点击事件处理
	private void onMouseClicked() {
		//创建任务
		//注意：在Win10下不允许直接在根目录下创建文件，所以建一个子目录来测试
		File srcFile = new File("c:/c/zbh.3gp");
		File dstFile = new File("c:/c/zbh2.3gp");
		LongTask th = new LongTask();
		th.execute(srcFile, dstFile);	
		
		// 提示等待对话框
		waitDialog = new WaitDialog(this);
		waitDialog.exec();
	}
	
	private class LongTask extends Thread{
		private File srcFile, dstFile;
		
		//传入参数，并启动线程
		public void execute(File srcFile, File dstFile) {
			this.srcFile = srcFile;
			this.dstFile = dstFile;
			start();//启动线程
		}
		
		@Override
		public void run() {
			//处理任务
			try {
				doInBackground();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			//歇息片刻
			try {sleep(100);}catch(Exception e){}
			
			//显示结果（跟新UI）
			SwingUtilities.invokeLater( ()->{
				done();
			});
		}
		
		//处理任务
		protected void doInBackground() throws Exception{
			InputStream inputStream = null;
			OutputStream outputStream = null;
			long lastTime = System.currentTimeMillis();
			long fileSize = srcFile.length();//文件大小
			long count = 0;//已经完成的字节数
			
			try {
				inputStream = new FileInputStream(srcFile);
				outputStream = new FileOutputStream(dstFile);
				byte[] buf = new byte[8192];//每次最多读取8KB
				while(true) {
					int n = inputStream.read(buf);//从源文件读取数量
					if(n <= 0) break;//已经读完
					outputStream.write(buf, 0, n);
					
					//计数及显示进度
					count += n; //已经拷贝完成的字节数
					long now = System.currentTimeMillis();
					if(now - lastTime > 500) {
						lastTime = now;
						updateProgress(count, fileSize);
						Thread.sleep(50);
					}
				}
			}
			finally {
				//出异常时，确保所有的文件句柄关闭
				try { inputStream.close();} catch(Exception e) {}
				try { outputStream.close();}catch(Exception e) {}
			}
		}
		
		//更新进度
		protected void updateProgress(long count, long total) {
			SwingUtilities.invokeLater( () -> {
				if(waitDialog != null) {
					//设置进度条的进度（0-100）
					int percent = (int)(100 * count / total);
					waitDialog.setProgress(percent);
				}
			});
		}
		
		//任务完成后，跟新界面显示
		protected void done() {
			//关闭对话框
			if(waitDialog != null) {
				waitDialog.setVisible(false);
				waitDialog = null;
			}
		}
	}

}
