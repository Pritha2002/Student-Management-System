package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.StudentRegistration;
import com.example.demo.service.IStudentRegisterService;

@Controller
public class RegistrationController {

	@Autowired
	private IStudentRegisterService service;

	@Autowired
	private StudentRegistration sr;

	@GetMapping("/register")
	public String registeredStudent(@RequestParam(name = "fname") String firstName,
			@RequestParam(name = "lname") String lastName, @RequestParam(name = "gender") String gender,
			@RequestParam(name = "quali") String qualification, @RequestParam(name = "contact") Long contactNum,
			Model model) throws Exception {

		sr.setStudentFirstName(firstName);
		sr.setStudentLastName(lastName);
		sr.setStudentGender(gender);
		sr.setStudentQualification(qualification);
		sr.setStduentContactNumber(contactNum);

		StudentRegistration student = service.addRegisteredStudent(sr);
		model.addAttribute("studentId", student.getStudentId());
		System.out.println(student);

		return "add_result";
	}

	@GetMapping("/search")
	public String retrieveStudent(@RequestParam(name = "studentId") String sId, Model model) throws Exception {

		Optional<StudentRegistration> searchStudent = service.searchStudent(sId);
		if (searchStudent.isPresent()) {
			StudentRegistration studentRegistration = searchStudent.get();
			model.addAttribute("firstName", studentRegistration.getStudentFirstName());
			model.addAttribute("lastName", studentRegistration.getStudentLastName());
			model.addAttribute("gender", studentRegistration.getStudentGender());
			model.addAttribute("quali", studentRegistration.getStudentQualification());
			model.addAttribute("contact", studentRegistration.getStduentContactNumber());
		}
		return "retrieve_result";
	}

	@GetMapping("/update")
	public String updateStudent(@RequestParam(name = "studentId") String sId,
			@RequestParam(name = "contact") Long contactNumber, Model model) throws Exception {

		Optional<StudentRegistration> searchStudent = service.searchStudent(sId);
		if (searchStudent.isPresent()) {
			sr = searchStudent.get();
			sr.setStudentId(sId);
			sr.setStduentContactNumber(contactNumber);
			sr.setStudentFirstName(sr.getStudentFirstName());
			sr.setStudentLastName(sr.getStudentLastName());
			sr.setStudentGender(sr.getStudentGender());
			sr.setStudentQualification(sr.getStudentQualification());

		}

		StudentRegistration updateStudent = service.updateStudent(sr);

		model.addAttribute("firstName", updateStudent.getStudentFirstName());
		return "update_result";
	}

	@GetMapping("/delete")
	public String deleteStudent(@RequestParam(name = "studentId") String sid, Model model) throws Exception {
		service.deleteStudent(sid);
		return "delete_result";
	}
}
