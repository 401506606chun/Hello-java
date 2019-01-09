package practiceCode;

import java.util.Scanner;

/*4、完成成绩等级输出程序
 * 
 * 如果用户输入的分数正确（0-100），则根据表-1中的规则计算该分数所对应的的级别，并计算结果。
 * 
 * 用户输入分数 要求0-100
 * 
 * 判断级别 
 * >=90 A
 * 	>=80 B
 * >=60 C
 * 其他 D
 * 
 */
public class Demo04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入分数：");
		int score = sc.nextInt();
		sc.close();
		if (score > 0 && score <= 100) {
			if (score >= 90) {
				System.out.println("您的级别是A");
			} else if (score >= 80) {
				System.out.println("您的级别是B");

			} else if (score >= 60) {
				System.out.println("您的级别是C");

			} else {
				System.out.println("您的级别是D");
			}
		} else {
			System.out.println("分数格式错误");
		}
	}
}
