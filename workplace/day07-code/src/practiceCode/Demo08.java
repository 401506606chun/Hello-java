package practiceCode;

import java.util.Arrays;

//用arrays.sort实现数组倒序排列
public class Demo08 {
	public static void main(String[] args) {
		Integer[] nums = { 1, 2, 3, 4, 5, 6 };
		
		Arrays.sort(nums);
		
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[nums.length-i-1]);
		}
	}

	
}


