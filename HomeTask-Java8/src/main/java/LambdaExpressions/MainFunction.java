package LambdaExpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFunction {

	public static void main(String[] args) {

		List<Employee> employeelist = new ArrayList<>();
		employeelist.add(new Employee(101, "Vidhi"));
		employeelist.add(new Employee(102, "Aneesha"));
		employeelist.add(new Employee(103, "Chitra"));
		employeelist.add(new Employee(104, "Saavi"));

		List<Integer> arraylist = Arrays.asList(1, 2, 3, 4, 5);

		Tasks tasks = new Tasks();
		tasks.checkPalindrome();
		tasks.findSecondBiggestNumber();
		tasks.checkIfStringsAreRotations();
		tasks.printNumbersUsingThread();
		tasks.sortInReverseOrder(arraylist);
		tasks.sortEmployeesInAlphabeticalNameOrder(employeelist);
		tasks.sortNumbersUsingTreeSetInReverseOrder();
		tasks.sortEmployeesUsingTreeSet(employeelist);
		tasks.sortValuesUsingTreeMapInReverseOrder();
		tasks.sortEmployeesUsingTreeMap();
		tasks.sortEmployeesUsingComparator(employeelist);

	}

}
