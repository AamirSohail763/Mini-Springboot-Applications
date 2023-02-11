package com.masai.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Size(min = 2,message = "Title should have atleast 2 characters..")
	private String title;
	@Size(min = 10, message = "Description should have atleast 10 characters..")
	private String description;
	@NotNull(message = "Name can't be empty..")
	private String author;
	@NotNull(message = "Email can't be empty..")
	private String email;
	
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Comment> comments = new ArrayList<>();
	
	
	
	
	

}
