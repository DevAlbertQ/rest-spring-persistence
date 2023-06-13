package br.com.albert.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.albert.dto.PersonDTO;
import br.com.albert.mocks.MockPerson;
import br.com.albert.model.Person;

public class ModelMapperConverterTest {

	private MockPerson inputObject;

	@BeforeEach
	public void setUp() {
		inputObject = new MockPerson();
	}
	
	@Test
	public void parseEntityToDTOTest() {
		PersonDTO output = Mapper.parsePersonToDTO(inputObject.mockEntity(0));
		assertEquals("Addres Test0", output.getAddress());
		assertEquals("Name Test0", output.getName());
		assertEquals("Male", output.getGender());
		assertEquals(Timestamp.valueOf("1995-01-05 00:00:00"), output.getBirthday());
		assertEquals(Long.valueOf(0L), output.getKey());
	}
	
	@Test
	public void parseDTOToEntityTest() {
		Person output = Mapper.parseToPerson(inputObject.mockVO());
		assertEquals("Addres Test0", output.getAddress());
		assertEquals("Name Test0", output.getName());
		assertEquals("Male", output.getGender());
		assertEquals(Timestamp.valueOf("1995-01-05 00:00:00"), output.getBirthday());
		assertEquals(Long.valueOf(0L), output.getId());
	}
	
	@Test
	public void parseEntityToDTOListTest() {
		List<PersonDTO> outputList = Mapper.parseToListPersonDTO(inputObject.mockEntityList());
		
		PersonDTO output0 = outputList.get(0);
		assertEquals("Addres Test0", output0.getAddress());
		assertEquals("Name Test0", output0.getName());
		assertEquals("Male", output0.getGender());
		assertEquals(Timestamp.valueOf("1995-01-05 00:00:00"), output0.getBirthday());
		assertEquals(Long.valueOf(0L), output0.getKey());
		
		PersonDTO output3 = outputList.get(3);
		assertEquals("Addres Test3", output3.getAddress());
		assertEquals("Name Test3", output3.getName());
		assertEquals("Female", output3.getGender());
		assertEquals(Timestamp.valueOf("1995-01-05 00:00:00"), output3.getBirthday());
		assertEquals(Long.valueOf(3L), output3.getKey());
		
		PersonDTO output7 = outputList.get(7);
		assertEquals("Addres Test7", output7.getAddress());
		assertEquals("Name Test7", output7.getName());
		assertEquals("Female", output7.getGender());
		assertEquals(Timestamp.valueOf("1995-01-05 00:00:00"), output7.getBirthday());
		assertEquals(Long.valueOf(7L), output7.getKey());
	}

	public void parseDTOToEntityListTest() {
		List<Person> outputList = Mapper.parseToListPerson(inputObject.mockVOList());
		
		Person output0 = outputList.get(0);
		assertEquals("Addres Test0", output0.getAddress());
		assertEquals("Name Test0", output0.getName());
		assertEquals("Male0", output0.getGender());
		assertEquals(Timestamp.valueOf("1995-01-05 00:00:00"), output0.getBirthday());
		assertEquals(Long.valueOf(0L), output0.getId());
		
		Person output3 = outputList.get(3);
		assertEquals("Addres Test0", output3.getAddress());
		assertEquals("Name Test0", output3.getName());
		assertEquals("Male0", output3.getGender());
		assertEquals(Timestamp.valueOf("1995-01-05 00:00:00"), output3.getBirthday());
		assertEquals(Long.valueOf(3L), output3.getId());
		
		Person output7 = outputList.get(7);
		assertEquals("Addres Test0", output7.getAddress());
		assertEquals("Name Test0", output7.getName());
		assertEquals("Male0", output7.getGender());
		assertEquals(Timestamp.valueOf("1995-01-05 00:00:00"), output7.getBirthday());
		assertEquals(Long.valueOf(7L), output7.getId());
	}
}
