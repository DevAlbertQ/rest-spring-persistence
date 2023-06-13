package br.com.albert.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.albert.mocks.MockBook;
import br.com.albert.model.Books;
import br.com.albert.repositories.BooksRepository;

class BooksServiceTest {

	MockBook input;
	
	@InjectMocks
	private BooksService service;
	
	@Mock
	private BooksRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Books> list = input.mockBooksList(14);
		when(repository.findAll()).thenReturn(list);
		 Books book1 = list.get(0);
		 Books book7 = list.get(6);
		 Books book14 = list.get(13);
		
		 assertEquals("Author 1", book1.getAuthor());
		 assertEquals("Title of book 1", book1.getTitle());
		 assertEquals(Double.valueOf(1 * 3.12), book1.getPrice());
		 assertEquals(Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()), book1.getLaunchDate());
		 assertEquals(1, book1.getId());
		 
		 assertEquals("Author 7", book7.getAuthor());
		 assertEquals("Title of book 7", book7.getTitle());
		 assertEquals(Double.valueOf(7 * 3.12), book7.getPrice());
		 assertEquals(Date.from(LocalDate.now().minusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()), book7.getLaunchDate());
		 assertEquals(7, book7.getId());
		 
		 assertEquals("Author 14", book14.getAuthor());
		 assertEquals("Title of book 14", book14.getTitle());
		 assertEquals(Double.valueOf(14 * 3.12), book14.getPrice());
		 assertEquals(Date.from(LocalDate.now().minusDays(14).atStartOfDay(ZoneId.systemDefault()).toInstant()), book14.getLaunchDate());
		 assertEquals(14, book14.getId());
		 
	}

}
