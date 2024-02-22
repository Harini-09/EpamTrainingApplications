import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Solutions {
	
	List<Employee> employeeList = new ArrayList<Employee>();
	public void addEmployeeDetails()
	{
    
		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
	}
	
	
	public void query1() 
	{
		int females=0,males=0;
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).getGender()=="Female")
				females++;
			else
				males++;
		}
		System.out.println("1. No of male employees = "+males+", No of female employees = "+females+"\n");
	}
	
	public void query2()
	{
		System.out.println("2. The names of all the departments in the organizations are :");
		for(Employee employee:employeeList)
		{
			System.out.print(employee.getDepartment()+", ");
		}
		System.out.println("\n");
	}
	
	public void query3()
	{
		int avg_age_female=0,avg_age_male=0,females=0,males=0;
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).getGender()=="Female")
			{
				avg_age_female+=employeeList.get(i).getAge();
				females+=1;
			}
			else
			{
				avg_age_male += employeeList.get(i).getAge();
				males+=1;
			}
		}
		avg_age_female=avg_age_female/females;
		avg_age_male=avg_age_male/males;
		System.out.println("3. Average age of male employees = "+avg_age_male+", Average age of female employees = "+avg_age_female+"\n");
	}
	
	
	public void query4()
	{
		System.out.println("4. The details of the highest paid employee in the organization :");
		int highest_pay=Integer.MIN_VALUE;
		int high_paid_employee_details=0;
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).getSalary()>highest_pay)
				high_paid_employee_details=i;
		}
		System.out.println(employeeList.get(high_paid_employee_details)+"\n");
	}
	
	
	public void query5()
	{
		System.out.println("5. The names of all employees who have joined after 2015 :");
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).getYearOfJoining()>2015)
				System.out.print(employeeList.get(i).getName()+", ");
		}
		System.out.println("\n");
	}
	
	
	public void query6()
	{
		System.out.println("6. The no of employees in each department :");
		Map<String,Integer> map = new HashMap<>();
		for(int i=0;i<employeeList.size();i++)
		{
			if(map.containsKey(employeeList.get(i).department))
				map.put(employeeList.get(i).getDepartment(), map.get(employeeList.get(i).getDepartment())+1);
			else
				map.put(employeeList.get(i).department,1);
		}
		System.out.println(map+"\n");
	}
	
	
	public void query7()
	{
		System.out.println("6. The average salary of each department :");
		Map<String,Double> map = new HashMap<>();
		for(int i=0;i<employeeList.size();i++)
		{
			if(map.containsKey(employeeList.get(i).salary))
				map.put(employeeList.get(i).department, (map.get(employeeList.get(i).department)+(employeeList.get(i).salary))/2);
			else
				map.put(employeeList.get(i).department,employeeList.get(i).salary);
		}
	}
	
	
	public void query8()
	{
		int min=Integer.MAX_VALUE;
		int youngest_emp=0;
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).department == "Product Development" && employeeList.get(i).age<min)
			{
				min=employeeList.get(i).age;
				youngest_emp=i;
			}
		}
		System.out.println("\n7. The youngest male employee in the product development departement is : "+employeeList.get(youngest_emp).getName());
	}
	
	
	public void query9()
	{
		int mini=Integer.MAX_VALUE;
		int empl=0;
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).getYearOfJoining()<mini)
			{
			mini=employeeList.get(i).getYearOfJoining();
			empl=i;
			}
		}
		System.out.println("\n8. The employee with the most working experience in the organization is : "+employeeList.get(empl).name);
	}
	
	
	public void query10()
	{
		int fn1=0;
		int mn1=0;
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).department=="Sales And Marketing")
			{
				if(employeeList.get(i).gender=="Female")
					fn1++;
				else
					mn1++;
			}
		}
		System.out.println("\n9. The no of male and female employees in the sales and the marketing are : Males="+mn1+", Females="+fn1);
	}
	
	
	public void query11()
	{
		int fsal=0,msal=0,fn2=0,mn2=0;
		for(Employee e : employeeList)
		{
			if(e.gender=="Female")
			{
				fsal+=e.salary;
				fn2+=1;
			}
			else
			{
				msal+=e.salary;
				mn2+=1;
			}
		}
		System.out.println("\n10. The average salary of male and female employees is : Male="+msal/mn2+", Female="+fsal/fn2);
	}
	
	
	public void query12()
	{
		System.out.println("\n12. The names of all employees in each department: ");
		Set<String> set = new HashSet<>();
		for(Employee e : employeeList)
			set.add(e.department);
		for(String s : set)
		{
			System.out.print(s+" = {");
			for(Employee e : employeeList)
			{
				if(s==e.department)
					System.out.print(e.name+", ");
			}
			System.out.print("}\n");
		}
	}

	
	public void query13()
	{
		int sum=0,n=0;
		for(Employee e:employeeList)
		{
			sum+=e.salary;
			n+=1;
		}
		System.out.println("\nThe Average salary = "+sum/n+", Total salary = "+sum);
	}
	
	
	public void query14()
	{
		List<String> l1 = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		for(Employee e:employeeList)
		{
			if(e.age<=25)
				l1.add(e.name);
			else
				l2.add(e.name);
		}
		System.out.println("\n14. The employees who are younger or equal to 25 years = "+l1);
		System.out.println("    The employee who are older than 25 years = "+l2);
	}
	
	
	public void query15()
	{
		int max=Integer.MIN_VALUE,em=0;
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).age>max)
			{
				max=employeeList.get(i).age;
				em=i;
			}
		}
		System.out.println("\n15. The oldest employee in the organization is : Name = "+employeeList.get(em).name+", Age = "+employeeList.get(em).age+", Departement = "+employeeList.get(em).department);
	}
}


