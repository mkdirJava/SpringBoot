package com.wilson.feelings.service.feeling;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.wilson.feelings.entities.Feeling;
import com.wilson.feelings.entities.dto.FeelingDTO;
import com.wilson.feelings.repositories.FeelingRepository;
import com.wilson.feelings.service.feeling.exception.FeelingNotFoundException;

public class FeelingServiceImpl implements FeelingService {
	@Autowired
	private FeelingRepository feelingRepository;
	
	@Override
	public FeelingDTO createFeeling(FeelingDTO feelingDTO)  {
		feelingRepository.saveAndFlush(feelingDTO.getFeeling());
		return feelingDTO;
	}

	@Override
	public FeelingDTO getFeelingById(Long id) throws FeelingNotFoundException {
		Feeling feeling = null;
		Optional<Feeling> feelingOptional = feelingRepository.findById(id);
		if(feelingOptional.isPresent()) {
			feeling = feelingOptional.get();
		}else {
			throw new FeelingNotFoundException();
		}
		FeelingDTO feelingDTO = new FeelingDTO();
		feelingDTO.setFeeling(feeling);
		return feelingDTO;
	}

	@Override
	public FeelingDTO updateFeeling(FeelingDTO feelingDTO)  {
		Feeling feeling = feelingRepository.saveAndFlush(feelingDTO.getFeeling());
		feelingDTO.setFeeling(feeling);
		return feelingDTO;
	}

	@Override
	public void deleteFeelingById(Long id)  {
		feelingRepository.deleteById(id);
		
	}

}
