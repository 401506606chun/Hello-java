package cn.itcast.day07.demo02;

/*
 * split切分方法
 * split(regex) regex为分割标记。
 * 不建议使用英文句点进行分割，一定要用 就用//. 如： split(//. )
*/public class Demo04StringSplit {
public static void main(String[] args) {
	String str1 = "aaa,bbb,ccc";
	String[] array1 = str1.split(",");
	System.out.println("数组长度为"+array1.length);
	
	for(int i=0; i<array1.length; i++){
		System.out.println(array1[i]);
	}
}
}
