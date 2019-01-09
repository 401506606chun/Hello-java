package cn.itcast.day07.demo02;

import java.util.ArrayList;
/*
 * ArrayList<E>中e为范型，只能使用引用类型；
 * 如果一定存储基本类型，就要使用基本类型对应的"包装类"（位于java.lang包下）
 * 
 * 基本类型	对应的包装类
 * byte			Byte
 * short		Short
 * int			Integer
 * long			Long
 * float			Float
 * double		Double
 * char			Character
 * boolean	Boolean
 * 
 * 只有int和char比较特殊
 * jdk1.5以上，基本类型和包装类可以进行自动装箱拆箱；
 * 装箱：基本类型-->包装类
 * 拆箱：包装类-->基本类型
*/
public class Demo06ArrayListWrapper {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int num = 100;
		list.add(num);
		
		list.get(0);
		System.out.println("list的内容是"+list.get(0));
		
		char ch = 'a';
		ArrayList<Character> list2 =new ArrayList<Character>();
		list2.add(ch);
		System.out.println("list2的长度是"+list2.size());
		
	}

}