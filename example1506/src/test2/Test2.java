/*
 * 通知机制
 */
package test2;

import java.util.ArrayList;
import java.util.Random;

public class Test2
{
	//鸡蛋
	class Egg{
		int id;
		public Egg(int id) {
			this.id = id;
		}
		public String toString() {
			return "Egg("+id+")";
		}
	}
		
		//母鸡（生产者）
	class Hen extends Thread{
			
		ArrayList<Egg> basket;
		
		public Hen(ArrayList<Egg> basket) {
			this.basket = basket;
		}
		
		public void run() {
			Random r = new Random();
			int count = 0;
				
			while(true) {
				//生产一个蛋，放在篮子里
				Egg egg = new Egg(++count);
				synchronized (basket)
				{
					basket.add(egg);
					basket.notify();//发出通知
				}
				System.out.println("产出："+egg);
					
				//休息一段时间：5~10秒
				int interval = 5+r.nextInt(5);
				try{
					Thread.sleep(interval*1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
	//小孩（消费者）
	class Boy extends Thread{
		ArrayList<Egg> basket;
		
		public Boy(ArrayList<Egg> basket) {
			this.basket = basket;
		}
			
		public void run() {
			while(true) {
				//查看篮子里有没有鸡蛋
				Egg egg = null;
				synchronized (basket)
				{
					try {
						basket.wait();//等待通知，只有调用notify()才会运行
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					
					if(basket.size() > 0) {
						//从篮子里取出鸡蛋
						egg = basket.remove(0);
					}
				}
					
				if(egg != null) {
					//吃掉鸡蛋
					System.out.println("   吃掉："+egg);
				}
					
				//每隔1秒钟检测一次
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
	public void test() {
		ArrayList<Egg> basket = new ArrayList<Egg>();
		Hen xiaoji = new Hen(basket);
		Boy xiaoming = new Boy(basket);
			
		xiaoji.start();
		xiaoming.start();
	}
	public static void main(String[] args)
	{
		Test2 t = new Test2();
		t.test();
	}

}
