package my;

public class Key2
{
	//在类的内部添加同步机制
	private byte[] data = new byte[16];
	
	public Key2() {
		update((byte)0);
	}
	
	//更新密钥
	public void update(byte seed) {
		synchronized (data)//可以直接使用this
		{
			for(int i=0; i<data.length; i++) {
				data[i] = seed;
			}
		}
	}
	//也可以直接将synchronized写在函数返回值类型前面
//	public synchronized void update(byte seed) {
//		for(int i=0; i<data.length; i++) {
//			data[i] = seed;
//		}
//	}
	
	//获取密钥
	public byte[] get() {
		synchronized(data) {
			//复制一份传出去
			byte[] copy = new byte[data.length];
			System.arraycopy(data, 0, copy, 0, data.length);
			return copy;
		}
	}

}
