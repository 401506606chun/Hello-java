import java.util.ArrayList;
import java.util.List;

/**
 * list比较数值大小
 * 
 * java foreach循环遍历List 比较List里面值的大小(值是int)
 * @author chengxuehong
 *
 */
public class ListSort4 {
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
        list.add(13);
        list.add(5);
        list.add(13);
        
        //查询最小值
        Integer min = list.get(0);
        for(Integer item: list){
        	if(item.intValue() < min){
        		min = item;
        	}
        }

        System.out.println("Min is: " + min.intValue());
        
        //判断值是否相等
        Integer min1 = list.get(0);
        for(Integer item: list){
        	if(item.intValue() == min1){
        		min = item;
        	}
        }

        System.out.println("equal is: " + min.intValue());
	}
	
}