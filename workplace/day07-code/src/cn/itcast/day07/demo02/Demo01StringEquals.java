package cn.itcast.day07.demo02;

/*用==进行比较 实质上比较的是地址值，字符串比较要用 equals方法 进行内容比较。

使用equals时，如果是变量和常量比较，尽量将常量写在equals前面，如abc.equals(str) 避免str为null时程序崩溃 报错：NullPointerException
也可用equalsIgnoreCase()忽略大小写字母
*/

public class Demo01StringEquals {
	public static void main(String[] args) {
		String str1 = "hello";
		
		char[] array = {'h','e','l','l','o'};
		String str2 = new String(array);  
		
//		比较str1和str2内容是否相同
		System.out.println(str1.equals(str2));
		
		String str3 = null;
//		比较str3和str2
		System.out.println(str2.equals(str3));
//		System.out.println(str3.equals(str2)); str3为空，报：NullPointerException
		
		
	}

}
