package com.masai.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
	
	@Id
	private String email;
	
	@NotNull(message = "First name can't be empty..")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "First Name should only contain alphabetical characters..")
	private String firstName;
	
	@NotNull(message = "Last name can't be empty.." )
	@Pattern(regexp = "^[a-zA-Z]*$" , message = "Last Name should only contain alphabetical characters..")
	private String lastName;
	
	@Pattern(regexp = "[0-9]{10}" , message = "Mobile Number should contain 10 digit only..")
	private String mobileNumber;
	
	@Past(message = "Date of birth must be in past..")
	private LocalDate dateOfBirth;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Event> events = new ArrayList<>();

}
