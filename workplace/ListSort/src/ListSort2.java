import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * List 元素排序 
 * Collections.sort
 * 
 * 一个Map<String,Object> 中建 key有name,age 字段，name为名字，age为年龄，现在根据年龄排序代码如下：
 * 
 * @author chengxuehong
 *
 */
public class ListSort2 {
	
	public static void main(String[] args){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "张三");//名字
		map1.put("age", 22);    //年龄
		list.add(map1);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "王五");
		map3.put("age", 38);
		list.add(map3);
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("name", "谢七");
		map5.put("age", 20);
		list.add(map5);
		Map<String, Object> map6 = new HashMap<String, Object>();
		map6.put("name", "张三");
		map6.put("age", 22);
		list.add(map6);
		
		//匿名实现Comparator接口进行排序
		Collections.sort(list, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				//java.math.BigDecimal 转 java.lang.Integer
				Object ob1 = o1.get("age");
				Integer a1 = Integer.parseInt(ob1.toString());
				Object ob2 = o2.get("age");
				Integer a2 = Integer.parseInt(ob2.toString());
				//end
				return a2.compareTo(a1);//进行判断 - 倒序
//				return a1.compareTo(a2);//进行判断 - 升序
			}
		});
		//打印值 倒序
		for(Map<String,Object> m:list){
			System.out.println("Map[name="+m.get("name")+"age="+m.get("age")+"]");
		}
	}

}