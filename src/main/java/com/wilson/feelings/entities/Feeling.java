package com.wilson.feelings.entities;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wilson.feelings.entities.exception.FeelingException;

@Entity	
public class Feeling {
	
	@Id
	@GeneratedValue
	private int id;
	private int feelingRating;
	@Temporal(TemporalType.DATE)
	private Date dateTime;
	@ManyToOne
	@JoinColumn(name="PersonId")
	private Person person;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Date getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	public int getFeelingRating() {
		return feelingRating;
	}
	
	public void setFeelingRating(int feelingRating) throws FeelingException {
		if(validateFeeling(feelingRating))
		this.feelingRating = feelingRating;
	}
	
	private boolean validateFeeling(int feeling) throws FeelingException{
		if(feeling < 0 || feeling > 10) {
			throw new FeelingException("Feeling Rating must be between 0 and 10");
		}
		if(this.feelingRating>10) {
			throw new FeelingException("You can't be happier than 10 ");
		}
		if(this.feelingRating < 0) {
			throw new FeelingException("You can't be happier less than 0 ");
		}
		return true;
	}
	
	

}
