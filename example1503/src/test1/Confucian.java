package test1;

public class Confucian extends Thread
{

	public boolean quitflag = false;
	
	//run()函数结束，线程终止进入死亡状态
	@Override
	public void run()
	{
		for(int i=1; i<=10; i++) {
			
			if(quitflag) break;
			
			System.out.println("人之初，性本善..."+i);
			
			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Confucian exit.");
	}
	

}
