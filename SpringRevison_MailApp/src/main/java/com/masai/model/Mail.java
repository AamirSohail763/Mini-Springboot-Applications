package com.masai.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mailId;
	
	@NotBlank(message = "Title can't be empty..")
	private String title;
	
	@NotBlank(message = "Description can't be empty..")
	private String description;
	
	private LocalDate createdAt;
	
	private Boolean starred;
	
	@ManyToOne
	private User user;

}
