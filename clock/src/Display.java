
public class Display {
	private int value=0;
	private int limit=0;
	public Display(int limit){
		this.limit = limit;
	}
	
	public void increase(){
		value++;
		value%=limit;
	}
	
	public int getValue(){
		return value;
	}
	
	public static void main(String[] args) {
		Display h = new Display(24);
		Display m = new Display(60);
		while(true){
			h.increase();
			System.out.println(h.getValue());
		}
	}

}
