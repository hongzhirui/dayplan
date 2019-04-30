package my;

public class Test
{

	public static void test1(Object obj) {
		Class cls = Student.class;
		//判断obj是不是Student类型
		if(cls.isInstance(obj)) {
			System.out.println("is an instance of Student");
		}
	}
	
	public static void test2(Object obj) {
		String clsName = obj.getClass().getName();
		//判断字符串是不是my.Student
		if(clsName.equals("my.Studetn")) {
			System.out.println("is an instance of Student");
		}
	}
	
	public static void main(String[] args)
	{
		Class cls = Student.class;
		System.out.println("Name:"+cls.getName());
		
		Student obj = new Student();
		Class cls2 = obj.getClass();
		
		Object obj1 = new Student();
		test1(obj1);
		
		Student s1 = new Student(123,"hon","123413");
		Student s2 = new Student(124,"ho","123413");
		if(s1.equals(123)) {
			System.out.println("equal");
		}
		else {
			System.out.println("not equal");
		}
	}

}
