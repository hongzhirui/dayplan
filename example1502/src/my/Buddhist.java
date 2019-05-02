package my;

//实现Runnable接口，重写run方法
//不需要继承，如果此类已经继承别的类，又要作为线程来运行，就可以实现Runnable接口这种方式
public class Buddhist implements Runnable
{

	@Override
	public void run()
	{
		for(int i=1; i<500; i++) {
			System.out.println("ma mi ma mi hong..."+i);
			try
			{
				//经常使用sleep，尽可能少的占用CPU，让别的线程也有机会运行
				Thread.sleep(100);//100毫秒后再运行
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
