package main;

public class Clock {
	private int hour=0;
	private int minute=0;
	
	public Clock(){
		
	}
	
	public void increase(){
		minute++;
		if(minute==60){
			hour++;
			minute = 0;
		}
		if(hour==24){
			hour=0;
		}
	}
	
	public int getHour(){
		return hour;
	}
	
	public int getMinute(){
		return minute;
	}
	
}
