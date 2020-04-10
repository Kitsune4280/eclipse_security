package com.edu.security.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.security.model.Person;

@RequestMapping("/api/person")
@RestController
public class PersonController {
	private List<Person> people = new ArrayList<>(
			Arrays.asList(
					new Person("1", "Ringo Starr", 42),
					new Person("2", "George Harrisson", 40)
					)
			);
	
	@GetMapping("/get/list")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_INTERN')")
	List<Person> showAll() {
		return people;
	}
	
	
	@GetMapping("/get/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_INTERN')")
	Person show(@PathVariable String id) {
		return people.get(0);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
	Person delete(@PathVariable String id) {
		System.out.print("Person deleted");
		return people.get(0);
	}
	
	@PostMapping("/create")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_INTERN')")
	Person create(@RequestBody Person person) {
		System.out.print("Person created");
		people.add(person);
		return person;
	}

}
