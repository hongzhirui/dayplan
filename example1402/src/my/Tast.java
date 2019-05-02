package my;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Tast
{

	//加载一个class文件
	public static Class loadClass() throws Exception
	{
		//加载my/Student.class
		Class cls = Class.forName("my.Student");
		
		//获取函数列表
		Method[] methods = cls.getMethods();
		
		//获取成员变量表
		Field[] fields = cls.getFields();
		
		for(Method m : methods) {
			System.out.println("函数："+m.toString());
		}
		
		return cls;
	}
	
	//从Class中获取Method：按名称查找（假设没有重名的函数）
	public static void findMethod(Class cls)  throws Exception{
		String methodName = "setId";
		
		//获取所有Method列表，顺序比对
		Method[] methods = cls.getMethods();
		for(Method m : methods) {
			if(m.getName().equals(methodName)) {
				break;
			}
		}
	}
	
	//从Class中获取Method：已知函数和参数类型
	public static void findMethod2(Class cls) throws Exception{
		String methodName = "setId";
		Class[] parameterTypes = {int.class};//int类型
		Method m = cls.getMethod(methodName, parameterTypes);
	}
	
	//一个完整的reflection测试
	public static void test1() throws Exception{
		//加载my/Student.class
		Class cls = Class.forName("my.Student");
		
		//创建一个实例，要求有一个不带参数的构造函数
		Object obj = cls.newInstance();
		
		//找到method
		Class[] parameterTypes = {int.class};
		Method m1 = cls.getMethod("setId",parameterTypes);
		
		//调用method
		Object[] parameters = {123};
		m1.invoke(obj, parameters);//第一个调用对象，第二个调用参数
		
		//显示结果
		System.out.println("result:"+obj.toString());
	}
	
	//一个完整的reflection测试
	public static void test2() throws Exception{
		//加载my/Student.class
		Class cls = Class.forName("my.Student");
		
		//创建一个实例，要求有一个不带参数的构造函数
		Object obj = cls.newInstance();
		
		//找到method
		Class[] parameterTypes = {String.class};
		Method m1 = cls.getMethod("setPhone",parameterTypes);
		
		//调用method
		Object[] parameters = {"12334556"};
		m1.invoke(obj, parameters);
		
		//显示结果
		System.out.println("result:"+obj.toString());
	}
	
	public static void main(String[] args)
	{
		try {
			//得到Class
			Class cls = loadClass();
			test2();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
