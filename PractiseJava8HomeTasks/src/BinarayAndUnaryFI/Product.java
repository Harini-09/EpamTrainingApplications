package BinarayAndUnaryFI;

import java.util.Objects;

public class Product {
	
	private String name;
	private String category;
	private Integer price;
	private Integer grade;
	
	public Product() {
		
	}
	
	public Product(String category,String name,  Integer price, Integer grade) {
		super();
		this.name = name;
		this.category = category;
		this.price = price;
		this.grade = grade;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public Integer getGrade() {
		return grade;
	}


	public void setGrade(Integer grade) {
		this.grade = grade;
	}


	@Override
	public String toString() {
		return "Product [name=" + name + ", category=" + category + ", price=" + price + ", grade=" + grade + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, grade, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && Objects.equals(grade, other.grade)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price);
	}
	
	
	
	

}


