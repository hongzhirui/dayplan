package my;

public class Confucian implements Runnable
{

	@Override
	public void run()
	{
		for(int i=1; i<500; i++) {
			System.out.println("人之初，性本善..."+i);
			try
			{
				Thread.sleep(100);//放弃CPU/休息100毫秒
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
