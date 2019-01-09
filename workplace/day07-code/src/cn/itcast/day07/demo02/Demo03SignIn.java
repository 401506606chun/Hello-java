package cn.itcast.day07.demo02;

import java.util.Scanner;

/*
 * 使用equals/equalsIgnoreCase两个方法模拟简单登录场景
 * 
 * 分析如下：
 * 1.登录需要用户名、密码两个信息，也就是字符串
 * 2.用户名和密码应该用键盘输入（用到scanner）
 * 3.用户名和密码都正确才算成功
 * 4.判断输入的用户名和密码 与正确的用户名/密码完全一致
 *      用户名正确
 *      密码正确
 * 5.判断是否登录成功用if
 * 6.登录成功展示欢迎信息；
 *    登录失败报错，退出系统 
 *    
 *    
 *    输入用户名
 *    验证是否正确
 *    正确再输入密码
 *    
 *    重复逻辑用循环，次数确定用for
 *
 */
public class Demo03SignIn {
	public static void main(String[] args) {
		// 定义两个变量 存放用户名和密码
		String userName = "zhangxiaochun";
		String userPassword = "a1234567";

		// 键盘输入用户名和密码
		Scanner inString = new Scanner(System.in);
		System.out.println("请输入用户名：");
		String name = inString.next();

		/*
		 * 验证能否成功登录 即：验证用户名是否正确，正确的情况下 再验证密码，密码也正确才能登录
		 */
		 if (name.equals(userName)) {
		 for(int j = 1; j<=3; j++){
		 System.out.println("请输入密码：");
		 String password = inString.next();
		 if (password.equals(userPassword)) {
		 System.out.println("欢迎你，登录成功");
		 break;
		 } else {
		 System.out.println("密码错误"+j+"次");
		 }
		 }
		 }
		 else{
		 System.out.println("用户不存在");
		 }
//		for (int i = 1; i <= 3; i++) {
//			System.out.println("请输入密码：");
//			String password = inString.next();
//			if (name.equalsIgnoreCase(userName)
//					&& password.equals(userPassword)) {
//				System.out.println("欢迎你");// 登录成功应该退出循环
//				break;
//			} else {
//				if (i != 3) {
//					System.out.println("登录失败，剩余次数" + (3 - i) + "次");
//				} else {
//					System.out.println("失败次数超限");
//				}
//
//			}
//		}

	}
}
