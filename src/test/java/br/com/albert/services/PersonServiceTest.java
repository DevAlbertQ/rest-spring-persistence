package br.com.albert.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.albert.dto.PersonDTO;
import br.com.albert.exceptions.RequiredObjectIsNullException;
import br.com.albert.mocks.MockPerson;
import br.com.albert.model.Person;
import br.com.albert.repositories.PersonRepository;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
	
	MockPerson input;
	
	@InjectMocks
	private PersonService services;
	
	@Mock
	PersonRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		
		List<Person> list = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(list);
		var people = services.findAll();
		assertNotNull(people);
		assertEquals(14, people.size());
		
		var p1 = people.get(1);
		var p5 = people.get(5);
		var p10 = people.get(10);
		assertEquals("Addres Test1", p1.getAddress());
		assertEquals("Name Test1", p1.getName());
		assertEquals(Timestamp.valueOf("1995-01-05 00:00:00"), p1.getBirthday());
		assertEquals("Female", p1.getGender());
		
		assertEquals("Addres Test5", p5.getAddress());
		assertEquals("Name Test5", p5.getName());
		assertEquals(Timestamp.valueOf("1995-01-05 00:00:00"), p5.getBirthday());
		assertEquals("Female", p5.getGender());
		
		assertEquals("Addres Test10", p10.getAddress());
		assertEquals("Name Test10", p10.getName());
		assertEquals(Timestamp.valueOf("1995-01-05 00:00:00"), p10.getBirthday());
		assertEquals("Male", p10.getGender());
	}

	@Test
	void testFindById() {
		Person person = input.mockEntity(1L);
		person.setId(1l);
		when(repository.findById(1l)).thenReturn(Optional.of(person));
		var result = services.findById(1l);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</person/1>;rel=\"Delete\", </person>;rel=\"Update\", </person/1>;rel=\"self\"]"));
	}

	@Test
	void testCreate() {
		Person person = input.mockEntity(1L);
		
		Person persisted = person;
		persisted.setId(1L);
		
		PersonDTO dto = input.mockVO(1);
		dto.setKey(1L);
		
		when(repository.save(person)).thenReturn(persisted);
		
		var result = services.create(dto);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));

	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}
	
	@Test
	void createWithNullPerson() {
		Exception ex = assertThrows(RequiredObjectIsNullException.class, 
				() -> {
					services.create(null);
				});
		
		assertTrue(ex.getMessage().contains("It is not allowed to persist a null object"));
	}

}
