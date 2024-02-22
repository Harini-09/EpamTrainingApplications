package CollectionsHomeTask2;

public class Collections {

	public static void main(String [] args)
	{
		Solutions solutions = new Solutions();
		solutions.addEmployeeDetails();
		
		//1.How many male and female employees are there in the organization
		solutions.calcuateMaleAndFemaleCount() ;
		
		//2.Print the name of all the departments in the organization
		solutions.nameAllDepartments();
		
		//3.What is the average age of male and female employees
		solutions.calculateMaleAndFemaleAverageAges();
		
		//4.Get the details of highest paid employee in the organization
		solutions.getHighestPaidEmployeeDetails();
		
		//5.Get the names of all employees who have joined after 2015
		solutions.getEmployeesJoinedAfter2015();
		
		//6.Count the no of employees in each department
		solutions.calculateEmployeesInEachDepartment();
		
		//7.Average salary of each department
		solutions.calculateEachDepartmentAverageSalary();
		
		//8. Get the details of youngest male employee in the product development department
		solutions.getDetailsOfYoungestMaleEmplooyeeInProductDevelopment();
		
		//9. Who has the most working experience in the organization
		solutions.getHighestWorkingExperienceEmployee();
		
		//10. How many male and female employees are there in the sales and marketing team
		solutions.calculateMaleAndFemaleCountInSalesAndMarketing();
		
		//11. What is the average salary of males and females employees
		solutions.calculateMaleAndFemaleAverageSalary();
		
		//12. List down the names of all employees in each department
		solutions.getEmployeesInEachDepartment();
	
		//13. What is the average salary and total salary of the whole organization
		solutions.calculateTotalOrganizationAverageAndTotalSalary();
		
		//14. Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years
		solutions.getEmployeesSeperatingYoungerAndOlder();
		
		//15. Who is the oldest employee in the organization? What is his age and which department he belongs to?
		solutions.getDetailsOfOldestEmployee();
	}
}

