package my;

/* 图像小块
 * 用一个编号代表一个小块图像，编号范围 0 1 2 ... 8
 */
public class ImageSlice
{
	public int id;
	
	public ImageSlice()
	{		
	}
	
	public ImageSlice(int id)
	{
		this.id = id;
	}
}
