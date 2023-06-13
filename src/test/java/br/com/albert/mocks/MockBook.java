package br.com.albert.mocks;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.albert.dto.BooksDTO;
import br.com.albert.model.Books;

public class MockBook {
	
	public Books mockEntity(Integer id) {
		Books book = new Books();
		book.setAuthor("Author "+id);
		book.setId(id);
		book.setLaunchDate(Date.from(LocalDate.now().minusDays(id).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		book.setPrice(Double.valueOf(id.intValue() * 3.12));
		book.setTitle("Title of book "+id);
		return book;
	}

	public BooksDTO mockDTO(Integer id) {
		BooksDTO book = new BooksDTO();
		book.setAuthor("Author "+id);
		book.setKey(id);
		book.setLaunchDate(Date.from(LocalDate.now().minusDays(id).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		book.setPrice(Double.valueOf(id.intValue() * 3.12));
		book.setTitle("Title of book "+id);
		return book;
	}
	
	public List<Books> mockBooksList(int qtt){
		List<Books> books = new ArrayList<Books>();
		for(int i = 1; i <= qtt; i++) {
			books.add(mockEntity(i));
		}
		return books;
	}
	
	public List<BooksDTO> mockDTOList(int qtt){
		List<BooksDTO> books = new ArrayList<BooksDTO>();
		for(int i = 1; i <= qtt; i++) {
			books.add(mockDTO(i));
		}
		return books;
	}
}
