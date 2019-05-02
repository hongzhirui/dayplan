package my;

public class Test
{

	public static void main(String[] args)
	{
		//启动一个线程呢个start()
		//线程主函数run()：描述了这个线程的工作任务
		Buddhist buddhist = new Buddhist();
		buddhist.start();
		Confucian confucian = new Confucian();
		confucian.start();
	}

}
