package my;

import java.util.Calendar;
import java.util.Date;

public class Test1 {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);	//基数为0，month需加一
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		long ms = cal.getTimeInMillis();	//Calendar -> long
		cal.setTimeInMillis(ms);			//long -> Calendar
		
		Date d = cal.getTime();	//Calendar -> Date
		cal.setTime(d);			//Date -> Calendar
	}

}
