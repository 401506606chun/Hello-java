package practiceCode;

import java.util.Scanner;

/*
 * 5、完成命令解析程序
 *   问题：有一个命令解析程序，该程序提供三个功能选型供用户选择，用户选择某功能后，程序在界面上输出用户所选择的的功能名称。
 * 1. 显示全部记录
 * 2.查询登录记录
 * 0.退出
 * 
 * 分析：
 * 输入数字 输出对应功能
 * 输入数字的要求是 0，1，2
*/
public class Demo05 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请选择功能：1. 显示全部记录 2.查询登录记录 0.退出");
		System.out.println("您的选择是：");
		int num = sc.nextInt();
		sc.close();
		
		switch (num) {
		case 1:
			System.out.println("1. 显示全部记录");
			break;
		case 2:
			System.out.println("2.查询登录记录");
			break;
		case 0:
			System.out.println("0.退出");
			break;

		default:
			System.out.println("输入错误");
			break;
		}
		
	}

}
