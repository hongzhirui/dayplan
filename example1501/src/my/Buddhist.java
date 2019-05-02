package my;

//继承Thread类，重写run方法
public class Buddhist extends Thread
{

	@Override
	public void run()
	{
		for(int i=1; i<500; i++) {
			System.out.println("ma mi ma mi hong..."+i);
			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
