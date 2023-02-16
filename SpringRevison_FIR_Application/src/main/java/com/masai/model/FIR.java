package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class FIR {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer firId;
	
	@NotNull(message = "Crime detail field can't be null..")
	private String crimeDetail;
	
	@PastOrPresent(message = "Report time can't be in future..")
	private LocalDateTime timeStamp;
	
	
	private Integer[] accusedId;
	
	@NotNull(message = "Police station info can't be null..")
	private String policeStation;
	
	
	@ManyToOne
	private User user;

}
