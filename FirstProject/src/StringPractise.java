import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringPractise {
	
	public static void main(String[] args) {
		
	String s = "Adhi Sita Vimal Kriti Sita Adhi Nidhi Kriti Tina Kriti Kriti Adha Gitu";
	
	//1. Find the Names and their count how much times they are repeated
	Map<String,Integer> map = new LinkedHashMap<>();
	String names[] = s.split(" ");
	for(String name:names) {
		Integer count = map.get(name);
		if(count==null)
			map.put(name, 1);
		else
			map.put(name, count+1);
			
	}
	System.out.println(map);
	System.out.println();
	
	//2. Find Duplicate names
	Set<String> numberset = map.keySet();
	System.out.println(numberset);
	
	List<String> duplicatenames = new ArrayList<>();
	for(String name:numberset) {
		if(map.get(name)>1)
			duplicatenames.add(name);
			
	}
	System.out.println(duplicatenames);
	System.out.println();

	
	//3. Find Unique names
	List<String> uniquenames = new ArrayList<>();
	for(String name:numberset) {
		if(map.get(name)==1)
			uniquenames.add(name);
	}
	System.out.println(uniquenames);
	System.out.println();

	
	//4. Find elements which are repeated 2 times
	List<String> twicenames = new ArrayList<>();
	for(String name:numberset) {
		if(map.get(name)==2)
			twicenames.add(name);
			
	}
	System.out.println(twicenames);
	System.out.println();

	//5
	
	//6. Find the first repeated name from the given names
	System.out.println(duplicatenames.get(0));
	System.out.println();

	
	//7. Find the first non-repeated name from the given names
	System.out.println(uniquenames.get(0));
	System.out.println();

	//8
	
	}
}
