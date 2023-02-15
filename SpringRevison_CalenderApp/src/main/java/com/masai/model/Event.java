package com.masai.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer eventId;
	
	@NotBlank(message = "Event title can't be empty..")
	private String eventTitle;
	
	private String eventInfo;
	
	@FutureOrPresent(message= "Start time can't be in Past..")
	private LocalDate startAt;
	
	@Future(message= "End time must be in Future..")
	private LocalDate endAt;
	
	private boolean recurringEvent;
	
	@ManyToOne
	private User user;

}
