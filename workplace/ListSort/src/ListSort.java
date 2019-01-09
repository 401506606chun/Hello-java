import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * List 元素排序 
 * Collections.sort
 * 
 * 对中文的排序规则为：首个汉字——拼音——首字母，遵循26个英文字母的顺序排列
 * 
 * @author chengxuehong
 *
 */
public class ListSort {
	
	 public static void main(String[] args)  {  
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();  
        Map<String, Object> map=new HashMap<String, Object>();  
        map.put("name", "中国");  
        Map<String, Object> map1=new HashMap<String, Object>();  
        map1.put("name", "美国");  
        Map<String, Object> map2=new HashMap<String, Object>();  
        map2.put("name", "俄罗斯");  
        Map<String, Object> map3=new HashMap<String, Object>();  
        map3.put("name", "英国");  
          
        list.add(map);  
        list.add(map1);  
        list.add(map2);  
        list.add(map3);  
        System.out.println("排序前："+list);  
        listSort(list);  
        System.out.println("排序后："+list);  
    }  
    
	//排序
    public static void listSort(List<Map<String, Object>> resultList) {  
        Collections.sort(resultList, new Comparator<Map<String, Object>>() {  
        	//根据首字母排序
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {  
                Collator instance = Collator.getInstance(Locale.CHINA);  
                return instance.compare(o1.get("name"), o2.get("name"));  
            }  
        });  
    }  
	    
}