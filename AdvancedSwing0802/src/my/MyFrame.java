package my;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

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
		ShortTask th = new ShortTask();
		
		// 提示等待对话框
		waitDialog = new WaitDialog(this);
		waitDialog.exec();
	}
	
	private class ShortTask extends Thread{
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
			MyFileUtils.copyFile(srcFile, dstFile);
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
