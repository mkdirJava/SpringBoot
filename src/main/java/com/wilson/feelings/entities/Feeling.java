package com.wilson.feelings.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wilson.feelings.entities.exception.FeelingException;

@Entity
public class Feeling {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer feelingRating =0;
	@Temporal(TemporalType.DATE)
	private Date dateTime;
	@ManyToOne
	@JoinColumn(name = "PersonId")
	private Person person;

	public Long getId() {
		return id;
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

	public void setFeelingRating(Integer feelingRating) throws FeelingException {
		if (validateFeeling(feelingRating)) {
			this.feelingRating = feelingRating;
		}
	}

	private boolean validateFeeling(Integer feeling) throws FeelingException {
		if (feeling < 0 || feeling > 10) {
			throw new FeelingException("Feeling Rating must be between 0 and 10");
		}
		if (this.feelingRating > 10) {
			throw new FeelingException("You can't be happier than 10 ");
		}
		if (this.feelingRating < 0) {
			throw new FeelingException("You can't be happier less than 0 ");
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Feeling [id=" + id + ", feelingRating=" + feelingRating + ", dateTime=" + dateTime + ", person="
				+ person.getId() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((feelingRating == null) ? 0 : feelingRating.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feeling other = (Feeling) obj;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (feelingRating == null) {
			if (other.feelingRating != null)
				return false;
		} else if (!feelingRating.equals(other.feelingRating))
			return false;
		if (id != other.id)
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}

}
