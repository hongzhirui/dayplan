package my;

public class Key
{
	private byte[] data = new byte[16];
	
	public Key() {
		update((byte)0);
	}
	
	//更新密钥
	public void update(byte seed) {
		for(int i=0; i<data.length; i++) {
			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data[i] = seed;
		}
	}
	
	//获取密钥
	public byte[] get() {
		return data;
	}

}
