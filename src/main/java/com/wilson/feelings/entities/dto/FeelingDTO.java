package com.wilson.feelings.entities.dto;

import com.wilson.feelings.entities.Feeling;

public class FeelingDTO {

	private Feeling feeling;
	
	public Feeling getFeeling() {
		return feeling;
	}
	public void setFeeling(Feeling feeling) {
		this.feeling = feeling;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feeling == null) ? 0 : feeling.hashCode());
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
		FeelingDTO other = (FeelingDTO) obj;
		if (feeling == null) {
			if (other.feeling != null)
				return false;
		} else if (!feeling.equals(other.feeling))
			return false;
		return true;
	}
	
}
