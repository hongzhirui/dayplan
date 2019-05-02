package my;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test2
{
	//加载一个class文件
	public static Class loadClass() throws Exception{
		//加载my/Student.class
		Class cls = Class.forName("my.Student");
		
		//获取函数列表
		Method[] methods = cls.getMethods();
		
		//获取成员列表
		Field[] fields = cls.getFields();
		
		for(Method m : methods) {
			System.out.println("函数："+ m.toString());
		}
		
		return cls;
	}
	
	//从Classh中获取Method:按名称查找（假设没有重名的函数）
	public static void findMethod(Class cls) throws Exception{
		String methodName = "setId";
		
		//获取所有Method列表，顺序比对
		Method[] methods = cls.getMethods();
		for(Method m : methods) {
			if(m.getName().equals(methodName)) {
				break;
			}
		}
	}

	
	//从Class中获取Method : 已知函数和参数类型
	public static void findMethod2(Class cls) throws Exception{
		String methodName = "setId";
		Class[] parameterTypes = {int.class};//参数类型
		Method m = cls.getMethod(methodName, parameterTypes);
		
		System.out.println("got the method");
	}
	
	public static void main(String[] args) throws Exception
	{
//		
		
		try {
			Class cls = loadClass();
			
			findMethod2(cls);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
