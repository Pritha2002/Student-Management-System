package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StudentRegistration;

@Repository
public interface RegistrationDao extends CrudRepository<StudentRegistration, String> {

}
