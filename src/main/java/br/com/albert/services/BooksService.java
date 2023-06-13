package br.com.albert.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.albert.controllers.BooksController;
import br.com.albert.dto.BooksDTO;
import br.com.albert.exceptions.RequiredObjectIsNullException;
import br.com.albert.exceptions.ResourceNotFoundException;
import br.com.albert.mapper.Mapper;
import br.com.albert.model.Books;
import br.com.albert.repositories.BooksRepository;

@Service
public class BooksService {
	
	private Logger log = Logger.getLogger(BooksService.class.getName());

	@Autowired
	private BooksRepository repository;
	
	public List<BooksDTO> listAll(){
		List<BooksDTO> booksDTO = Mapper.parseToListBooksDTO(repository.findAll());
		booksDTO.stream().forEach(b -> {
			b.add(linkTo(
						methodOn(BooksController.class)
						.findById(b.getKey())
						).withSelfRel());
					});
		return booksDTO;
	}
	
	public BooksDTO findById(Integer id) {
		var book = repository.findById(id).orElseThrow(() -> {
					log.severe("No book found for this Id");
					return new ResourceNotFoundException("No records found for this ID!");
		});
		return Mapper.parseBookToDTO(book);
	}
	
	public BooksDTO create(BooksDTO bookDTO) {
		if(bookDTO == null) throw new RequiredObjectIsNullException(); 
		var book = Mapper.parseToBook(bookDTO);
		BooksDTO dto = Mapper.parseBookToDTO(repository.save(book));
		return dto.add(linkTo(
					methodOn(BooksController.class)
					.findById(dto.getKey())
				).withSelfRel());
	}
	
	public BooksDTO update(BooksDTO bookDTO) {
		if(bookDTO == null) throw new RequiredObjectIsNullException();
		Books book = repository.findById(bookDTO.getKey())
				.orElseThrow(() -> {
					log.severe("No book found for this Id");
					return new ResourceNotFoundException("No records found for this ID!");
				});
		book.setAuthor(bookDTO.getAuthor());
		book.setId(bookDTO.getKey());
		book.setLaunchDate(bookDTO.getLaunchDate());
		book.setPrice(bookDTO.getPrice());
		book.setTitle(bookDTO.getTitle());
		BooksDTO dto = Mapper.parseBookToDTO(
				repository.save(book)
				);
		return dto.add(linkTo(
					methodOn(BooksController.class)
					.findById(dto.getKey())
					).withSelfRel());
	}
	
	public void delete(Integer id) {
		if(id == null) throw new RequiredObjectIsNullException();
		var book = repository.findById(id).orElseThrow(() -> {
			log.severe("No book found for this Id");
			return new ResourceNotFoundException("No records found for this ID");
		});
		repository.delete(book);
	}

}
