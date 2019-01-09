package cn.itcast.day07.demo02;

import java.util.ArrayList;

/*
 * ArrayList类
 * java.util.ArrayList
 * 
 * ArrayList<范型> list = new ArrayList<>();
 *范型：就是集合当中放的全部都是统一的什么类型
 *注意：范型只能是引用类型不能用基本类型 
 * 
 * 已有集合操作：
 * 1 添加
 * public boolean add(e) 对于ArrayList来讲返回值 一定是true
 * 2 获取元素
 * public e get(int index)
 * 3 获取长度
 * public int size()
 */
public class Demo05ArrayList {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		System.out.println(list);

		// 添加元素
		list.add("鹿晗");
		list.add("张艺兴");
		list.add("张靓颖");
		list.add("谢娜");
		
		System.out.println(list);
		System.out.println(list.get(3));//从0开始
		System.out.println("现在有几个人？"+list.size());
		
		list.add("迪丽热巴");
		list.add("古力娜扎");
		
		System.out.println("加了两个人之后是几个人？"+list.size());
		
		int j = list.size();
		for(int i=0; i<j; i++){
			System.out.println("第"+i+"个元素是"+list.get(i));
		}

	}

}
