package my;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Test
{

	public static void main(String[] args)
	{
		//win10不能在分区根目录下创建文件，但可以在根目录下先创建文件夹再创建文件
		//OutputStream  将数据输出到文件
		//InputStream 从文件中读取数据
		File dir1 = new File("C:/examples");
		dir1.mkdirs();//如果目录不存在，创建目录
		
		File f1 = new File(dir1,"123.txt");
		String text = "abcdefg";
		
		try {
			FileOutputStream outputStream = new FileOutputStream(f1);//文件输出流
			
			//data:UTF-8编码的文本数据
			byte[] data = text.getBytes("UTF-8");//文本 -> 字节数据
			outputStream.write(data);//将字节数据写入文件
			outputStream.close();//关闭文件
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		File dir2 = new File("C:/examples");
		File f2 = new File(dir2,"123.txt");
		try {
			FileInputStream inputStream = new FileInputStream(f2);//文件输入流
			int size = (int)f2.length();
			byte[] data = new byte[size];//创建一个足够大的缓冲区
			inputStream.read(data);//读取文件中data大的内容
			inputStream.close();
			
			//将读取来的数据转成String
			String text1 = new String(data, "UTF-8");
			System.out.println(text1);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
