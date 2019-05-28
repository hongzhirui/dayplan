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
		CopyTask th = new CopyTask();
		th.execute(srcFile, dstFile);
		
		// 提示等待对话框
		waitDialog = new WaitDialog(this);
		waitDialog.exec();
	}
	
	private class CopyTask extends HzrShortTask{
		
		@Override
		protected void doInBackground() throws Exception{
			File srcFile = (File)this.args[0];
			File dstFile = (File)this.args[1];
			MyFileUtils.copyFile(srcFile, dstFile);
		}
		
		@Override
		protected void done() {
			//关闭对话框
			if(waitDialog != null) {
				waitDialog.setVisible(false);
				waitDialog = null;
			}
		}
	}

}
