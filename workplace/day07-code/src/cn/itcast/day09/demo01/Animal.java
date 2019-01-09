package cn.itcast.day09.demo01;

/*
 * 定义一个接口
 * public Interface 接口名称{
 *			包含：抽象类
 *					 变量
 *					
 *					 静态方法
 *					私有方法
 *
 * }
 * 

 * 
 * 定义一个抽象方法
 * 
 * public abstract 返回值类型 方法名称（参数类型 参数名称）；
 * 
 * 抽象方法，修饰如果写  一定是abstract
 * 如果不写 可以省略，默认就是public abstract
 * 抽象方法只有方法头，没有方法体大括号
 * 
*/

public interface Animal {
//	定义一个抽象方法 吃
	public abstract void eat();
	
//	定义一个抽象方法 睡觉
//	省略修饰
	void sleep();
	

}

