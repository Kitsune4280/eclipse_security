package com.edu.security.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.security.model.Intern;

@RestController
@RequestMapping("/api/intern")
public class InternController {
	private List<Intern> interns = new ArrayList<>(
			Arrays.asList(
					new Intern(1, "Lobanov"),
					new Intern(2, "Levin")
					)
			);
	
	@GetMapping("/get/list")
	List<Intern> getAll(){
		return interns;
	}
	
	@GetMapping("/get/{id}")
	Intern show(@PathVariable String id) {
		return interns.get(0);
	}
	
	@DeleteMapping("/{id}")
	Intern delete(@PathVariable String id) {
		System.out.print("Intern deleted");
		return interns.get(0);
	}
}
