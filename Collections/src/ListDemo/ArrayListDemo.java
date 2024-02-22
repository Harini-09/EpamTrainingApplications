package ListDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo {
	public static void main(String arg[]) {
	
	ArrayList<String> arraylist = new ArrayList<>();
	arraylist.add("Annie");
	arraylist.add("Kriti");
	arraylist.add("Vidhya");
	arraylist.add("Jones");
	System.out.println(arraylist);
	
	arraylist.remove(2);
	System.out.println(arraylist);
	System.out.println(arraylist.get(1));
	
	//for each loop or enhanced loop
	for(String employee : arraylist)
	{
		System.out.print(employee+" ");
		System.out.println(arraylist.indexOf(employee));
	}
	
	List<String> arraylist2 = Arrays.asList("Situ","Gitu");
	
	arraylist.addAll(arraylist2);
	System.out.println(arraylist);
	
//	arraylist.removeAll(arraylist2);
//	System.out.println(arraylist);
	
	Iterator iterator = arraylist.iterator();
	while(iterator.hasNext())
	{
		System.out.println(iterator.next());
	}
	
	for(String employee : arraylist)
	{
		if(!arraylist2.contains(employee))
			arraylist2.add(employee);
	}
	System.out.println(arraylist2);
	
	arraylist2.set(3, "Harry");
	System.out.println(arraylist2);
	
	arraylist2.toArray();
	System.out.println(arraylist2);
	
	ArrayList<String> arraylist3 = (ArrayList<String>)arraylist.clone();
	System.out.println();
	
	
	}

}
