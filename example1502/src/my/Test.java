package my;

public class Test
{

	public static void main(String[] args)
	{
		Confucian e1 = new Confucian();
		Thread d = new Thread(e1);
		d.start();
		
		Buddhist e2 = new Buddhist();
		Thread t = new Thread(e2);
		t.start();
	}

}
