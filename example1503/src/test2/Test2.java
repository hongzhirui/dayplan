package test2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test2
{

	public static void main(String[] args)
	{
		Buddhist e1 = new Buddhist();
		e1.start();
		
		//按回车后，打断睡眠，终止线程，退出程序
		InputStreamReader m = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(m);
		try {
			reader.readLine();
			reader.close();
			
			e1.quitflag = true;
			e1.interrupt();//打断睡眠,如果没有这行代码，必须等sleep状态结束，run才会结束，有了这行代码，run会立即结束
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
