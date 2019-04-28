package main;

public class Employee {
	private String no;
	private String name;
	private double money;
	private double growth;
	
	public Employee(String no, String name, double money, double growth){
		this.no = no;
		this.name = name;
		this.money = money;
		this.growth = growth;
	}

	public String getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public double getMoney() {
		return money;
	}

	public double getGrowth() {
		return growth;
	}
	
	public void growthMoney(){
		this.money += this.growth;
	}
}
