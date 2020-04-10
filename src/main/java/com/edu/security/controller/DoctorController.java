package com.edu.security.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.security.model.Doctor;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

	private List<Doctor> doctors = new ArrayList<>(
			Arrays.asList(
					new Doctor(1, "Watson"),
					new Doctor(2, "Pilulkin")
					)
			);
	
	@GetMapping("/get/list")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	List<Doctor> getAll(){
		return doctors;
	}
	
	@GetMapping("/get/{id}")
	Doctor show(@PathVariable String id) {
		return doctors.get(0);
	}
	
	@DeleteMapping("/{id}")
	Doctor delete(@PathVariable String id) {
		System.out.print(id);
		return doctors.get(0);
	}
}
