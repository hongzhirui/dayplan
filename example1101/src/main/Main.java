package main;

public class Main {
	public static void main(String[] args) {
//		Employee employee = new Employee("201901","张三",4000,1000);
//		System.out.println("员工的编号："+employee.getNo());
//		System.out.println("员工的姓名："+employee.getName());
//		System.out.println("员工的基本工资："+employee.getMoney());
//		System.out.println("员工的增长额："+employee.getGrowth());
//		employee.growthMoney();
//		System.out.println("员工的增长后的工资："+employee.getMoney());
		
		Clock BeiJing = new Clock();
		while(true){
			BeiJing.increase();
			System.out.println(BeiJing.getHour()+":"+BeiJing.getMinute());
		}
	}

}
