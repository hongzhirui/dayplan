package test2;

public class Buddhist extends Thread
{
	public boolean quitflag = false;

	@Override
	public void run()
	{
		for(int i=1; i<10; i++) {
			
			if(quitflag) break;
			
			System.out.println("ma mi ma mi hong..."+i);
			
			try {
				Thread.sleep(1000*5);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Buddhist exit.");
	}
	

}
