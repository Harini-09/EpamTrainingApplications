package MapDemo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BasicMapDemo {
	public static void main(String[] args) {
		//put,putAll,putIfAbsent,get,getOrDefault,remove,containsKey,containsValue,replace,
		//keySet,Values,EntrySet
		
		Map<String,Integer> map = new HashMap<>();
		
		map.put("Jones", 78);
		map.put("Richards", 86);
		map.put("Mark", 99);
		map.put("Nick", 82);
		map.put("Bill", 89);
		map.putIfAbsent("Bill", 86);
		
		System.out.println(map);

		Set<String> keys =map.keySet();
		Collection<Integer> values =map.values();
		Set<Map.Entry<String, Integer>> set =map.entrySet();
		for(Map.Entry<String, Integer> element:set) {
			System.out.println(element.getKey());
			System.out.println(element.getValue());
			System.out.println(element+"\n");
		}
		System.out.println(keys+"\n"+values+"\n"+set);
		
//		Map<String,Integer> map2 = new HashMap<>();
//		map2.put("Zenith", 75);
//		map2.put("Vernon", 96);
//		map2.putIfAbsent("Zenith", 92);
//		map.putAll(map2);
//		System.out.println(map);
//		
//		System.out.println(map.getOrDefault("John",65));
//		
//		map2.remove("Vernon");
//		System.out.println(map2);
//		
//		map2.put("Zenith",62);
//		System.out.println(map2);
//		
//		System.out.println(map.containsKey("Zenith"));
//		System.out.println(map.containsValue(85));
//
//		map.replace("Richards",82,89);
//		System.out.println(map);
//		
//		System.out.println(map.keySet());
//		System.out.println(map.values());
//		System.out.println(map.entrySet());
		
	}

}
