package my;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MyFileUtils
{
	public static void copyFile(File srcFile, File dstFile) throws Exception{
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(srcFile);
			outputStream = new FileOutputStream(dstFile);
			byte[] buf = new byte[8192]; //每次最多读取8KB
			while(true) {
				int n = inputStream.read(buf);//从源文件读取数据
				if(n <= 0) break;//已经读完
				outputStream.write(buf, 0, n);//写入目标文件
			}
		}
		finally {
			//出异常时，确保所有的文件句柄被关闭
			try { inputStream.close(); }catch(Exception e) {}
			try { outputStream.close(); }catch(Exception e) {}
		}
	}

}
