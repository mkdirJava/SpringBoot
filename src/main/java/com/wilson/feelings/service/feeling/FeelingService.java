package com.wilson.feelings.service.feeling;

import com.wilson.feelings.entities.dto.FeelingDTO;
import com.wilson.feelings.service.feeling.exception.FeelingNotFoundException;

public interface FeelingService {
	
public FeelingDTO createFeeling(FeelingDTO feelingDTO) ;
	
	public FeelingDTO getFeelingById(Long id) throws FeelingNotFoundException;
	
	public FeelingDTO updateFeeling(FeelingDTO feelingDTO) ;
	
	public void deleteFeelingById(Long id) ;
	

}
