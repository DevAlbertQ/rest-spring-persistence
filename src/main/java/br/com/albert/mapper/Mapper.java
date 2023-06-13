package br.com.albert.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import br.com.albert.dto.BooksDTO;
import br.com.albert.dto.PersonDTO;
import br.com.albert.model.Books;
import br.com.albert.model.Person;

public class Mapper {

	private static ModelMapper mapper = new ModelMapper();
	private static TypeMap<Person, PersonDTO> mapperPersonToDTO = new ModelMapper().createTypeMap(Person.class, PersonDTO.class);
	private static TypeMap<PersonDTO, Person> mapperDTOtoPerson = new ModelMapper().createTypeMap(PersonDTO.class, Person.class);
	private static TypeMap<Books, BooksDTO> mapperBooksToDTO = new ModelMapper().createTypeMap(Books.class, BooksDTO.class);
	private static TypeMap<BooksDTO, Books> mapperDTOtoBooks = new ModelMapper().createTypeMap(BooksDTO.class, Books.class); 
	
	public static <O, D> D parseObject(O origin, Class<D> destination){
		
		return mapper.map(origin, destination);
	}
	
	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination){
		
		return origin.stream()
				.map(o -> mapper.map(o, destination))
				.collect(Collectors.toList());
		
	}
	
	public static PersonDTO parsePersonToDTO(Person origin){
		return mapperPersonToDTO
				.addMapping(Person::getId, PersonDTO::setKey)
				.map(origin);
	}
	
	public static List<PersonDTO> parseToListPersonDTO(List<Person> origin){
		return origin.stream().map(o -> mapperPersonToDTO
				.addMapping(Person::getId, PersonDTO::setKey)
						.map(o))				
				.collect(Collectors.toList());
	}

	public static Person parseToPerson(PersonDTO origin){
		return mapperDTOtoPerson
				.addMapping(PersonDTO::getKey, Person::setId)
				.map(origin);
	}
	
	public static List<Person> parseToListPerson(List<PersonDTO> origin){
		return origin.stream().map(o -> mapperDTOtoPerson
				.addMapping(PersonDTO::getKey, Person::setId).map(o))
				.collect(Collectors.toList());
	}
	
	public static List<Books> parseToListBooks(List<BooksDTO> origin){
		return origin.stream().map(o -> mapperDTOtoBooks
							.addMapping(BooksDTO::getKey, Books::setId).map(o))
							.collect(Collectors.toList());
	}
	
	public static List<BooksDTO> parseToListBooksDTO(List<Books> origin){
		return origin.stream().map(o -> mapperBooksToDTO
						.addMapping(Books::getId, BooksDTO::setKey).map(o))
						.collect(Collectors.toList());
	}
	
	public static BooksDTO parseBookToDTO(Books origin) {
		return mapperBooksToDTO.addMapping(Books::getId, BooksDTO::setKey).map(origin);
	}
	
	public static Books parseToBook(BooksDTO origin) {
		return mapperDTOtoBooks.addMapping(BooksDTO::getKey, Books::setId).map(origin);
	}
}
