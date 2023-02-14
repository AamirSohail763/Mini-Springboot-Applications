package com.masai.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;
	
	@NotNull(message = "Name can't be Null..")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Name should only contain alphabetical characters..")
	private String driverName;
	
	@Column(unique = true)
	@NotNull(message = "Please enter License number..")
	private String licenseNumber;
	
	@NotEmpty(message = "Location can't be empty..")
	@NotNull(message = "Location can't be Null..")
	private Integer[] currentLocation;
	
	@NotNull(message = "Please enter your Cab type..")
	private String cabType;
	
	@Column(columnDefinition = "boolean default true")
	private Boolean cabAvailable;
	

}
