package my;

import java.io.File;
import java.io.FileFilter;

public class Test
{
	//递归得到所有层级文件
	public static void getFile(File file) {
		File[] f = file.listFiles();
		for(File a : f) {
			if(a.isDirectory()) {
				getFile(a);
			}
			else {
				System.out.println("扫描到文件："+a);
			}
		}
	}

	public static void main(String[] args)
	{
		
//		File d = new File("C:/Users/11629/Desktop/git/a/ab");
//		
//		//是否存在此路径
//		if(d.exists()) {
//			System.out.println("存在此路径");
//		}
//		if(d.isDirectory()) {
//			System.out.println("存在此路径");
//		}
		
//		d.mkdirs();//创建层级目录
		
		File d = new File("C:/Users/11629");
		//过滤器，后缀名、文件大小等
		FileFilter filter = new FileFilter()
		{
			
			@Override
			public boolean accept(File pathname)
			{
				if(pathname.getAbsolutePath().endsWith(".pptx"))
					return true;
				return false;
			}
		};
		
//		File[] sbuFiles = d.listFiles();
//		File[] sbuFiles = d.listFiles(filter);//只会找到.pptx文件
//		//使用listFiles()可以遍历目录下的子目录/文件，一级目录
//		for(File f : sbuFiles) {
//			if(f.isDirectory()) {
//				System.out.println("扫描到目录：" + f);
//			}
//			else {
//				System.out.println("扫描到文件：" + f);
//			}
//		}
		
		File[] sbuFiles = d.listFiles();
		for(File f : sbuFiles) {
			if(f.isDirectory()) {
				
			}
			else {
				System.out.println("扫描到文件：" + f);
			}
		}
		
		File e = new File("C:/c/practice");
		getFile(e);
		System.out.println("exit");
		
		//创建一个新文件，先创建父目录，再在父目录下创建文件
		File homeDir = new File("C:/");
		File f = new File(homeDir,"some.txt");
	}

}
