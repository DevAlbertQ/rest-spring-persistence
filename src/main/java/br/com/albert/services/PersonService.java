
package br.com.albert.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.albert.controllers.PersonController;
import br.com.albert.dto.PersonDTO;
import br.com.albert.exceptions.RequiredObjectIsNullException;
import br.com.albert.exceptions.ResourceNotFoundException;
import br.com.albert.mapper.Mapper;
import br.com.albert.model.Person;
import br.com.albert.repositories.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonService {
	
	private Logger log = Logger.getLogger(PersonService.class.getName());

	@Autowired
    PersonRepository repository;


    public List<PersonDTO> findAll() {
        log.info("Finding all people!");
        List<PersonDTO> listPersonDTO = Mapper.parseToListPersonDTO(repository.findAll());
        listPersonDTO.stream()
        	.forEach(p -> {
					p.add(
							linkTo(
									methodOn(PersonController.class).findById(p.getKey())
									).withSelfRel());
			});
		return listPersonDTO;
    }

    public PersonDTO findById(Long id){
        log.info("Finding one person!");
        var person = repository.findById(id)
        		.orElseThrow(() -> {
        			log.severe("No records found for this ID!");
        			return new ResourceNotFoundException("No records found for this ID!");
        		});
        PersonDTO personDTO = Mapper.parsePersonToDTO(person);
		return personDTO.add(
				linkTo(methodOn(PersonController.class)
						.delete(personDTO.getKey())).withRel("Delete"))
				.add(
						linkTo(methodOn(PersonController.class)
						.update(personDTO)).withRel("Update"))
				.add(linkTo(methodOn(PersonController.class)
						.findById(personDTO.getKey())).withSelfRel());
    }

    @Transactional
    public PersonDTO create(PersonDTO personDTO) {
    	if(personDTO == null) throw new RequiredObjectIsNullException();
    	Person person = Mapper.parseToPerson(personDTO);
        log.info("Creating one person");
        
        PersonDTO result = Mapper.parsePersonToDTO(repository.save(person));
		return result.add(
				linkTo(methodOn(PersonController.class)
						.findById(personDTO.getKey())).withSelfRel());
    }

    @Transactional
    public PersonDTO update(PersonDTO personDTO) {
        log.info("Updating one person!");
        var entity = repository.findById(personDTO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setName(personDTO.getName());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());
        entity.setBirthday(personDTO.getBirthday());

        return Mapper.parsePersonToDTO(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting a person by id");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }
	
}
