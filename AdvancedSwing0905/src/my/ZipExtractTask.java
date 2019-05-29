package my;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.SwingUtilities;

public class ZipExtractTask extends Thread
{
	MyFrame ui; // 窗口界面

	File srcFile; // *.zip 文件
	File dstDir; // 解压缩的目标目录
	ZipInfo zipInfo;
	
	public ZipExtractTask(MyFrame ui)
	{
		this.ui = ui;
	}

	public void execute(File srcFile, File dstDir, ZipInfo zipInfo)
	{
		this.srcFile = srcFile;
		this.dstDir = dstDir;
		this.zipInfo = zipInfo;
		this.start();
	}

	@Override
	public void run()
	{
		dstDir.mkdirs();
		
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(srcFile, Charset.forName("GBK"));
			extract (zipFile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try{ zipFile.close();}catch(Exception e) {}
		}
				
		// 最后要记得关闭文件
		SwingUtilities.invokeLater( ()->{
			done();
		});
	}
	
	private void extract(ZipFile zipFile) throws Exception
	{
		long lastTime = System.currentTimeMillis();
		int count = 0;
		
		// 遍历每一条
		Enumeration<?> entries = zipFile.entries();
		while (entries.hasMoreElements())
		{
			ZipEntry entry = (ZipEntry) entries.nextElement();
			if (entry.isDirectory())continue;
	
			System.out.println("处理文件：" + entry.getName());
	
			File dstFile = new File(dstDir, entry.getName());
			dstFile.getParentFile().mkdirs(); // 创建所在的子目录
	
			// 从zip文件中解出数据
			InputStream inputStream = zipFile.getInputStream(entry);
			OutputStream outputStream = new FileOutputStream(dstFile);
			try
			{
				byte[] buf = new byte[4096];
				while (true)
				{
					int n = inputStream.read(buf);
					if (n <= 0)	break;
					outputStream.write(buf, 0, n);
					
					// 为了增强演示效果，使之变慢一些
					Thread.sleep(500);
					
					// 每0.5秒更新一次界面
					count += 1;
					long now = System.currentTimeMillis();
					if(now - lastTime > 500)
					{
						lastTime = now;
						showProgress( count , zipInfo.fileCount);
						Thread.sleep(50);
					}
				}
			} finally
			{
				// 确保文件被关闭
				try	{ inputStream.close();} catch (Exception e){}
				try	{ outputStream.close();	} catch (Exception e){}
			}
		}
	}
	
	// 显示进度
	private void showProgress(int count, int total)
	{
		final int progress = 100 *count / total;
		SwingUtilities.invokeLater( ()->{
			ui.waitDialog.setProgress( progress );
		});
	}
	
	// 任务完成后的处理
	private void done()
	{
		if(ui.waitDialog != null)
		{
			ui.waitDialog.setVisible(false);
			ui.waitDialog = null;
		}		
	}

}
