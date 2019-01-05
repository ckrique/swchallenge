package br.com.swchallenge.api.service;

import br.com.swchallenge.api.DTO.BaseDTO;
import br.com.swchallenge.api.model.BaseEntity;

public class BaseService {
	
	public BaseEntity extractEntityFromDTO(BaseDTO baseDto) {
		BaseEntity baseEntity = new BaseEntity();
		
		baseEntity.setId(baseDto.getId());
		baseEntity.setName(baseDto.getName());
		
		return baseEntity;
	}
	
}
