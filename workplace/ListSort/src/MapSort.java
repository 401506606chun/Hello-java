import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapSort {
	
	public static void main(String[] args){
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("k1", "2");
		map1.put("v1", "3");
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("k2", "2");
		map2.put("v2", "5");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(map1.get("k1") == map2.get("k2")){
			if(map1.get("v1") == map2.get("v2")){
				list.add(map1);
				list.add(map2);
			}else{
				list.add(map2);
				list.add(map1);
			}
		}
		System.out.println(list);
	}

}
