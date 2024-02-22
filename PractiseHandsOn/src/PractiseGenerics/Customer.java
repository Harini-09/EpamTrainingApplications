package PractiseGenerics;

public class Customer implements Comparable<Customer> {

	private String customerName;
	private double customerSalary;

	public Customer(String customerName, double customerSalary) {
		super();
		this.customerName = customerName;
		this.customerSalary = customerSalary;
	}

	public String getCustomerName() {
		return customerName;
	}

	public double getCustomerSalary() {
		return customerSalary;
	}
	
	
	
	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", customerSalary=" + customerSalary + "]";
	}

	public int compareTo(Customer c) {
		if(this.getCustomerSalary()<c.getCustomerSalary())
			return 1;
		return -1;
	}

}

