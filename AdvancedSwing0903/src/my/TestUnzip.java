package my;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class TestUnzip
{

	public static void main(String[] args) throws Exception
	{
		File scrFile = new File("C:/c/practice.zip");
		
		//指定解压缩的目标位置
		File dstDir = new File("C:/c/example");
		dstDir.mkdirs();
		
		//在Windows指定GBK,在Linux一般为UTF-8
		//若字符集不匹配，则文件名和路径中的中文将是乱码
		ZipFile zipFile = new ZipFile(scrFile, Charset.forName("GBK"));
		
		//遍历每一条
		Enumeration<?> entries = zipFile.entries();
		while(entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry)entries.nextElement();
			
			if(entry.isDirectory()) {
				continue;
			}
			System.out.println("处理文件："+entry.getName());
			
			//entry.getName()获取目标文件的位置
			File dstFile = new File(dstDir, entry.getName());
			dstFile.getParentFile().mkdirs();//创建所在的子目录
			
			//从zip文件中解出数据
			InputStream inputStream = zipFile.getInputStream(entry);
			OutputStream outputStream = new FileOutputStream(dstFile);
			try {
				byte[] buf = new byte[4096];
				while(true) {
					int n = inputStream.read(buf);
					if(n<=0)break;
					outputStream.write(buf, 0, n);
				}
			}finally {
				try {inputStream.close();}catch(Exception e) {}
				try {outputStream.close();}catch(Exception e) {}
			}
			
		}
		//最后要记得关闭文件
		zipFile.close();
	}

}
