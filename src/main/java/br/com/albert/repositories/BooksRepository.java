package br.com.albert.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.albert.model.Books;

public interface BooksRepository extends JpaRepository<Books, Integer>{

}
