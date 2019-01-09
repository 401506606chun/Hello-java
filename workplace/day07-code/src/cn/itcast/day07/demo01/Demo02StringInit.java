package cn.itcast.day07.demo01;
/*字符串创建的常见的2+1种方式
 * 
 * 常用的两个构造方法
 * public String(char[] array):参数是一个字符数组 ，根据官也街数组的内容来创建一个字符串
 * public String(char[] array, int offset,int count):根据字符数组的一部分内容来创建字符串。
 * offset代表数组的起始索引
 * count代表字符个数
 * 还有一种是直接赋值 
*/
public class Demo02StringInit {
public static void main(String[] args) {
	char[] array = {'h','e','l','l','o'};//定义数据用：char[] 变量名
	//	根据字符数组来创建字符串
	//	类名称 对象名 = new 类名称（构造参数）；
	String str1 = new String(array);
	System.out.println(str1);
	
	//	根据字符数组的一部分来创建字符串
	String str2 = new String(array,0,4);
	System.out.println(str2);
	
	//直接赋值
	String str3 = "hello world";
	System.out.println(str3);
	
	
}
}
