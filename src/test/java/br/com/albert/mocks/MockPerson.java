package br.com.albert.mocks;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.albert.dto.PersonDTO;
import br.com.albert.model.Person;

public class MockPerson {

    public Person mockEntity(long id) {
    	Integer i = Long.valueOf(id).intValue();
        return mockEntity(i);
    }
    
    public PersonDTO mockVO() {
        return mockVO(0);
    }
    
    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonDTO> mockVOList() {
        List<PersonDTO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }
    
    public Person mockEntity(Integer number) {
        Person person = new Person();
        person.setAddress("Addres Test" + number);
        person.setName("Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setBirthday(Timestamp.valueOf("1995-01-05 00:00:00"));
        return person;
    }

    public PersonDTO mockVO(Integer number) {
        PersonDTO person = new PersonDTO();
        person.setAddress("Addres Test" + number);
        person.setName("Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setKey(number.longValue());
        person.setBirthday(Timestamp.valueOf("1995-01-05 00:00:00"));
        return person;
    }

}
