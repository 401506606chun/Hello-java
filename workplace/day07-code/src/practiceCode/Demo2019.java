package practiceCode;

import java.util.Scanner;

public class Demo2019 {

	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入a");
		int a = sc.nextInt();
		System.out.println("请输入b");
		int b = sc.nextInt();
		sc.close();

		int[] array = { a, b };
		printArray(array);
	}

}
