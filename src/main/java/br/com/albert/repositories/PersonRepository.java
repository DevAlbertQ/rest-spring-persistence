package br.com.albert.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.albert.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
}
