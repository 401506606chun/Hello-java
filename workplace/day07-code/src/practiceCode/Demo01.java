package practiceCode;

import java.util.Scanner;
//实现两个数最大
public class Demo01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入两个数字");

		int a = sc.nextInt();
		int b = sc.nextInt();
		sc.close();
		int max;
		if (a >= b) {
			max = a;
		} else {
			max = b;
		}
		System.out.println("两个数中更大的是"+max);
	}
	

}
