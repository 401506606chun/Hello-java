package cn.itcast.day07.demo02;

import java.util.ArrayList;

public class Demo07ArrayListCustom {
	public static void main(String[] args) {
		Person one = new Person("小明",18);
		Person two = new Person("小花",17);
		Person three = new Person("小白",5);
		
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(one);
		list.add(two);
		list.add(three);
		
//		遍历list内容并打印
		int j = list.size();
		for(int i =0; i<j ; i++){
			System.out.println("姓名"+list.get(i).getName()+"年龄"+list.get(i).getAge());
		}
		
		
	}

}
