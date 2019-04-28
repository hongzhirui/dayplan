package my;

import java.util.Date;

public class Test {
	public void someBusyWork(){
		long start = System.currentTimeMillis();
		
		for(int i=0; i<1000000; i++){
			double a = Math.sin(i);
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println("时间花费："+duration);
	}
	public static void main(String[] args) {
		long now1 = System.currentTimeMillis();//从1970-1-1 00：00：00到现在过了多少毫秒
		System.out.println(now1);
		Date now2 = new Date();//Date会得到年月日时分秒
		System.out.println(now2);
		//Date与long可相互转换
		long mm = now2.getTime();//Data -> long
		System.out.println(mm);
		Date now3 = new Date();
		now3.setTime(now1);//long -> Data
		System.out.println(now3);
		Test t = new Test();
		t.someBusyWork();
	}

}
