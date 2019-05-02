package test1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test1
{

	public static void main(String[] args)
	{
		Confucian e1 = new Confucian();
		e1.start();
		
		//按回车，终止线程，然后退出程序
		InputStreamReader m = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(m);
		try {
			reader.readLine();
			reader.close();
			
			e1.quitflag=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
