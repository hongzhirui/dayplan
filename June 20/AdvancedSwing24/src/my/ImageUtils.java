package my;

import java.awt.Rectangle;
import java.util.Random;

public class ImageUtils
{
	//截取一个正方形
	public static Rectangle crop(Rectangle rect) {
		int width = rect.width;
		int height = rect.height;
		
		//从中截取一个正方形
		int boxSize = width < height ? width : height;
		Rectangle result = new Rectangle(0, 0, boxSize, boxSize);
		result.x = rect.x + (width - boxSize) / 2;
		result.y = rect.y + (height - boxSize) / 2;
		return result;
	}

	//按规格为M X N来划分一个矩形区域
	public static Rectangle[] split(Rectangle rect, int rows, int columns) {
		Rectangle[] rr = new Rectangle[rows * columns];
		int index = 0;
		int x_size = rect.width / columns;
		int y_size = rect.height / rows;
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				Rectangle cell = new Rectangle();
				cell.x = rect.x + x_size * col;
				cell.y = rect.y + y_size * row;
				cell.width = x_size;
				cell.height = y_size;
				
				rr[index] = cell;
				index++;
			}
		}
		return rr;
	}
	
	// 随机打乱排列
	public static void randomSort(Object[] array){
		int size = array.length;
		Object[] copy = new Object[size];
		
		Random rand = new Random();
		for(int i=0; i<size; i++){
			// 从剩下的几个里面挑一个
			int select = rand.nextInt( size - i );
			int s = 0;
			for(int k = 0; k < size; k++){
				// 判断 array[k] 是否已经被取走
				if(array[k] == null) continue;
				
				if(s == select){
					// 取走 array[k] ，放到 copy[i]
					copy[i] = array[k];
					array[k] = null;
					break;
				}
				else{
					s ++;
				}
			}
			}
			
		// 将 copy 中的内容才复制回 array
		for(int i = 0; i < size; i++){
			array[i] = copy[i];
		}
	}
}
