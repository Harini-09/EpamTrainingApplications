package ComparableAndComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Runner {

	public static void main(String[] args) {
		List<Laptop> laps = new ArrayList<>();
		laps.add(new Laptop("Dell",16,800));
		laps.add(new Laptop("Apple",8,1200));
		laps.add(new Laptop("Acer",12,700));
		
		Comparator<Laptop> com = new Comparator<>() {
			public int compare(Laptop lap1, Laptop lap2) {
				//integers and all 8 wrapper classes can be compared with > and <
//				if(lap1.getRam()>lap2.getRam())
//					return 1;
//				else if(lap1.getRam()<lap2.getRam())
//					return -1;
//				return 0;
				
			//strings cannot be compared with > and < so we call String's internal 
			//implemented method compareTo() which has it's own way of comparing the strings.
				return lap1.getBrand().compareTo(lap2.getBrand());
					
			}
		};
		
		Collections.sort(laps,com);
		
		for(Laptop lap : laps)
			System.out.println(lap);
		
	}

}
