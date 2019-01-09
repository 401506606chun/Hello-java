package cn.itcast.day07.demo01;

/*字符串池：
1 位于堆内存中的一小块空间，用来保险若干个字符串的地址值。
2 字符串当中不会出现重复的字符串对应的地址，保证字符串不重复；
3 凡是直接双引号的字符串默认都在池中；new出来的字符串默认不在池中；

字符串的内容不可改变，每当你觉得字符串的值变了的时候  一定是创建了新的字符串


*/


public class Demo03StringPool {
	public static void main(String[] args) {
		String str1 = "hello";
		
		char[] array = {'h','e','l','l','o'};
		String str2 = new String(array);  
		String str3 = new String(array);
		
		System.out.println(str1==str2);
		System.out.println(str3==str2);
		System.out.println(str1.equals(str2));
	}
}
