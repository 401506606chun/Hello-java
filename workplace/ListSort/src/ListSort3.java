import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 两个list集合，根据rn字段排序，其中一个list集合，合并到另一个list集合
 * @author chengxuehong
 *
 */
public class ListSort3 {
	
	public static void main(String[] args){
		//集合一
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("rn", 9);
		list1.add(map1);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("rn", 15);
		list1.add(map2);
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("rn", 23);
		list1.add(map3);
		
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("rn", 35);
		list1.add(map4);
		
		
		//集合二
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		Map<String, Object> mp1 = new HashMap<String, Object>();
		mp1.put("rn", 8);
		list2.add(mp1);
		
		Map<String, Object> mp2 = new HashMap<String, Object>();
		mp2.put("rn", 12);
		list2.add(mp2);
		
		Map<String, Object> mp3 = new HashMap<String, Object>();
		mp3.put("rn", 21);
		list2.add(mp3);

		
		//排序
		for (Map<String, Object> map : list2) {
			boolean isADDed = false;
			for (int i = 0, size = list1.size(); i < size; i++) {
				if ((int)map.get("rn") < (int)list1.get(i).get("rn")) {// 顺序号小的放在前面
					//lis2集合中元素挨个跟list1集合元素比较，小于list1集合的值添加到list1集合中，否则，添加到最后
					list1.add(i, map);
					isADDed = true;
					break;
				}
			}
			if (!isADDed) {// 未添加则加到最后
				list1.add(map);
			}
		}
		
		//输出
		for(Map<String,Object> m:list1){
			System.out.println("Map[rn="+m.get("rn")+"]");
		}
	}

}