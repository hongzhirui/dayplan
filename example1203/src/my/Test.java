package my;

import java.io.File;

import javax.xml.crypto.Data;

public class Test
{
	public static void main(String[] args) {
		//完全路径 = 当前路劲+相对路径
//		. 表示本目录，..表示父目录
//		./data		表示当前目录下的data
//		../data		表示父目录下的data
//		../../data	表示父目录的父目录下的data
//		./src/.../data 表示在本目录下的src目录，再往找src的父目录下的data
		
		//相对目录
		File f = new File("src/my/Test.java");
		System.out.println(f.getAbsolutePath());
		
		String workDir = System.getProperty("user.dir");
		//修改工作目录
		System.setProperty("user.dir", "c:");
		File f1 = new File("src/my/Test.java");
		System.out.println(f1.getAbsolutePath());
	}

}
