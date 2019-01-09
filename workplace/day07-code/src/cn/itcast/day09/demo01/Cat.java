package cn.itcast.day09.demo01;

/*
 * 要想使用定义好的接口，必须有一个接口的“实现类”
 * 定义实现类格式为：
 * 
 * public class 实现类名 implements 接口名称{
 *		//一定要覆盖重写所有的抽象方法 
 * }
 * 
 * 覆盖重写
 * 1 将接口的抽象方法抄写过来
 * 2 去掉abstract
 * 3 写上大括号方法体
 * 
 * Cat就是Animal接口的实现类，Cat实现了Animal的接口
 */

public class Cat implements Animal {
	public void eat() {
		System.out.println("cat eat fish");
	}

	public void sleep() {
		System.out.println("cat sleeps");
	}

	// 再定义一个自有方法
	public void catchMouse() {
		System.out.println("cat can catch mouse");
	}

}
