package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.StudentRegistration;

public interface IStudentRegisterService {

	public StudentRegistration addRegisteredStudent(StudentRegistration student) throws Exception;
	public Optional<StudentRegistration> searchStudent(String studentId) throws Exception;
	public void deleteStudent(String studentId) throws Exception;
	public StudentRegistration updateStudent(StudentRegistration student) throws Exception;
}
