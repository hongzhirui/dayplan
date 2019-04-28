package my;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test
{

	public static void main(String[] args)
	{
		File f = new File("C:/Users/11629/Desktop/github.txt");//并不是创建一个文件，只是创建一个file对象
		if(f.exists()) { //exists()判断文件是否存在
			System.out.println("文件存在："+f);
		}
		else {
			System.out.println("文件不存在");
		}
		
		if(f.isFile()) {
			System.out.println("是文件");
		}
		
		long size = f.length();//文件大小
		long lastModified = f.lastModified();//最后一次修改文件的时候，返回毫秒
//		Calendar c = Calendar.getInstance();
//		c.setTimeInMillis(lastModified);
//		System.out.println(c.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String timestr = sdf.format(new Date(lastModified));
//		f.renameTo(C:/Users/11629/Desktop/a.txt);
		f.delete();
	}

}
