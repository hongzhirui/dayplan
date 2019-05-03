package my;

public class Test
{

	//定期更新密钥
	private class KeyUpdater extends Thread{
		private Key key;
		
		public  KeyUpdater(Key k) {
			this.key = k;
		}
		
		public void run() {
			byte seed = 0;
			while(true) {
			
				//锁定临界资源
				synchronized(key) {
					//更新密钥，每次加一
					seed++;
					if(seed>100) seed = 0;
					key.update(seed);
				}
					
				try {
					Thread.sleep(5);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//读取密钥
	private class KeyPrinter extends Thread{
		private Key key;
		
		public KeyPrinter(Key k) {
			this.key = k;
		}
		
		public void run() {
			while(true) {
				//得到key值并打印
				
				//锁定临界资源
				synchronized(key) {
					byte[] data = key.get();
					for(int i=0; i<data.length; i++) {
						System.out.print(String.format("%02X  ", data[i]));
					}
					System.out.println();
				}
				
				try {
					Thread.sleep(5);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void test() {
		Key key = new Key();
		KeyUpdater t1 = new KeyUpdater(key);
		KeyPrinter t2 = new KeyPrinter(key);
		
		t1.start();
		t2.start();
	}
	
	public static void main(String[] args)
	{
		Test t = new Test();
		t.test();
	}

}
