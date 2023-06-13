package br.com.albert.dto;

import org.modelmapper.ModelMapper;

import br.com.albert.mapper.MappingUtils;

public interface DTO {

	default ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils) {
		return mapper;
	}
}
