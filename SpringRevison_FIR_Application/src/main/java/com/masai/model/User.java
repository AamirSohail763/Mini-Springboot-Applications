package com.masai.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="users")
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	@NotNull
	private String userName;
	
	@NotNull(message = "First name can't be Null..")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "First Name should only contain alphabetical characters..")
	private String firstName;
	
	@NotNull(message = "Last name can't be Null.." )
	@Pattern(regexp = "^[a-zA-Z]*$" , message = "Last Name should only contain alphabetical characters..")
	private String lastName;

	
	@NotNull
	@Pattern(regexp="[0-9]{10}", message = "Mobile number must be of 10 digits")
	private String mobileNo;
	
	@NotNull(message = "Address can't be null..")
	@NotEmpty(message = "Address can't be empty..")
	private String address;
	
	@Min(value = 8, message = "Age must not be less than 8 years")
	private Integer age;
	
	@NotNull(message = "Gender can't be null..")
	private Gender gender;
	
	
	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,12}$",
	message = "Password should be alphanumeric and must contain 6-12 characters having at least one special character, one upper case, one lowercase, and one digit.")
	private String password;
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<FIR> FIRs = new ArrayList<>();

}



enum Gender{
	Male,
	Female,
	Transgender,
	male,
	female,
	transgender
}
