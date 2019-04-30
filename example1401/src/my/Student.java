package my;

public class Student
{
	private int id;
	private String name;
	private String phone;
	
	public Student() {
		
	}
	public Student(int id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
	}
	
	@Override
	public String toString()
	{
		return "("+id+","+name+","+phone+")";
	}
	
	@Override
	public boolean equals(Object obj)
	{
		//与一个Student对象比较
		if(this.getClass().isInstance(obj)) {
			Student other = (Student) obj;
			return other.id == this.id;
		}
		
		//与一个String对象比较
		if(String.class.isInstance(obj)) {
			String other = (String) obj;
			return other.equals(this.name);
		}
		
		//与一个Integer对象比较
		if(Integer.class.isInstance(obj)){
			Integer other = (Integer) obj;
			return other == this.id;
		}
		
		return false;
	}
	
	
}
