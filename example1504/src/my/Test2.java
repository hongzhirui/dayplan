package my;

public class Test2
{

	//定期更新密钥
	private class KeyUpdater extends Thread{
		private Key2 key;
		
		public  KeyUpdater(Key2 k) {
			this.key = k;
		}
		
		public void run() {
			byte seed = 0;
			while(true) {
			
				//更新密钥，每次加一
				seed++;
				if(seed>100) seed = 0;
				key.update(seed);
					
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
		private Key2 key;
		
		public KeyPrinter(Key2 k) {
			this.key = k;
		}
		
		public void run() {
			while(true) {
				//得到key值并打印
				byte[] data = key.get();
				for(int i=0; i<data.length; i++) {
					System.out.print(String.format("%02X  ", data[i]));
				}
				System.out.println();
				
				try {
					Thread.sleep(5);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void test() {
		Key2 key = new Key2();
		KeyUpdater t1 = new KeyUpdater(key);
		KeyPrinter t2 = new KeyPrinter(key);
		
		t1.start();
		t2.start();
	}
	
	public static void main(String[] args)
	{
		Test2 t = new Test2();
		t.test();
	}

}
