package br.com.albert.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import br.com.albert.dto.BooksDTO;
import br.com.albert.dto.PersonDTO;
import br.com.albert.model.Books;
import br.com.albert.model.Person;

@Configuration
public class ModelMapperConfig {

	
	public ModelMapper modelMapperPerson() {
		ModelMapper mapper = new ModelMapper(); 
		mapper.createTypeMap(Person.class, PersonDTO.class)
			.addMapping(Person::getId, PersonDTO::setKey);
		return mapper;
				
	}
	
	public ModelMapper modelMapperBooks() {
		ModelMapper mapper = new ModelMapper();
		mapper.createTypeMap(Books.class, BooksDTO.class)
			.addMapping(Books::getId, BooksDTO::setKey);
		return mapper;
	}
	
	
}
