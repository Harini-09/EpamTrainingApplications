package com.epam.dtos;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
	@Size(min=3,max=20,message="Id length should be between 3 and 20")
	private String id;
	@NotBlank(message="Password shouldn't be blank")
	private String password;
	private String type;
	public UserDto() {
		
	}
	public UserDto(String id, String password, String type) {
		super();
		this.id = id;
		this.password = password;
		this.type = type;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, password, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(type, other.type);
	}
	
}
