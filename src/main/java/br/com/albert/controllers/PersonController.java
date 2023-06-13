package br.com.albert.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.albert.dto.PersonDTO;
import br.com.albert.mapper.Mapper;
import br.com.albert.services.PersonService;
import br.com.albert.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for managing people")
public class PersonController {
	
	@Autowired
	private PersonService service;

	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all Peolple", description = "Finds all people",
				tags = {"People"},
				responses = {
						@ApiResponse(description = "Success", responseCode="200", content = {
								@Content(
										mediaType="application/json",
										array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
										)
						}),
						@ApiResponse(description = "Bad Request", responseCode="400", content = @Content),
						@ApiResponse(description = "Unauthorized", responseCode="401", content = @Content),
						@ApiResponse(description = "Not found", responseCode="404", content = @Content),
						@ApiResponse(description = "Internal Error", responseCode="500", content = @Content)
				})
	public List<PersonDTO> listAll(){
		
		return Mapper.parseListObjects(service.findAll(), PersonDTO.class);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
	@Operation(summary = "Finds all Peolple", description = "Finds all people",
		tags = {"People"},
		responses = {
				@ApiResponse(description = "Success", responseCode="200", content = {
						@Content(
								mediaType="application/json",
								array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
								)
				}),
				@ApiResponse(description = "Bad Request", responseCode="400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode="401", content = @Content),
				@ApiResponse(description = "Not found", responseCode="404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode="500", content = @Content)
		})

	public PersonDTO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON, 
			produces = MediaType.APPLICATION_JSON)
	public PersonDTO create(@RequestBody PersonDTO person) throws Throwable {
		return service.create(person);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON, 
			produces = MediaType.APPLICATION_JSON)
	public PersonDTO update(@RequestBody PersonDTO person) {
		return service.update(person);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
