package cn.itcast.day09.demo02;

public class Demo01Calc {
	public static void main(String[] args) {
		Calculator cal = new CalculatorImpl();
		method(cal);
	}
	// 参数是接口类型：计算器接口
	public static void method(Calculator calculator){
		int a =10;
		int b= 20;
		int c = calculator.sum(a, b);
		System.out.println(a+"+"+b+"="+c);
	}
	
}
