package test3;

public class Test3
{

	public static void main(String[] args)
	{
		Buddhist e3 = new Buddhist();
		e3.start();
		
		//如果不等待则主线程会比e3更早退出
		//等待e3退出
		try {
			//join()函数会阻塞住当前线程，等待目标线程退出后，再继续执行
			//join()函数只起等待作用
			e3.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<3; i++) {
			System.out.println(i);
		}
		System.out.println("program exit.");
	}

}
