package my;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test
{

	public static void main(String[] args)
	{
		
		//计算俩时间的差值
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		//注意：月份是0,1,2...,11
		c1.set(2018, 1, 10);//2018年2月10日
		c2.set(2018, 2, 10);//2018年3月10日
		
		long delta = c2.getTimeInMillis() - c1.getTimeInMillis();
		long days = delta/(1000*60*60*24);
		
		System.out.println("相差"+days+"天");
		
		
		//计算一段时间之前
		//2018年3月2日15天之前的日期
		//法1
		Calendar c3 = Calendar.getInstance();
		c3.set(2018, 2, 2);
		long l1=c3.getTimeInMillis()-15*1000*60*60*24;
//		c3.setTimeInMillis(l1);
		
		//法2
		c3.add(Calendar.DAY_OF_MONTH, -15);//第一个参数表示要改变哪个字段，第二个参数表示差值
		System.out.printf("结果：%d-%d-%d\n",c3.get(Calendar.YEAR),c3.get(Calendar.MONTH)+1,c3.get(Calendar.DAY_OF_MONTH));
		
		
		//时间的格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date now = new Date();
		String str = sdf.format(now);
		System.out.println("日期："+str);
		//字符串时间格式 -> Date
		try {
			String s1 = "2018-3-2 11:29:00";
			Date t1 = sdf.parse(s1);
			System.out.println(t1);
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}

}
