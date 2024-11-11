package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RegistrationDao;
import com.example.demo.entity.StudentRegistration;

@Service
public class StudentRegisterServiceImpl implements IStudentRegisterService {

	@Autowired
	private RegistrationDao dao;

	@Override
	public StudentRegistration addRegisteredStudent(StudentRegistration student) throws Exception {
		return dao.save(student);
	}

	@Override
	public Optional<StudentRegistration> searchStudent(String studentId) throws Exception {
		return dao.findById(studentId);
		
	}

	@Override
	public void deleteStudent(String studentId) throws Exception {
		dao.deleteById(studentId);
	}

	@Override
	public StudentRegistration updateStudent(StudentRegistration student) throws Exception {
		return dao.save(student);
	}

	
}
