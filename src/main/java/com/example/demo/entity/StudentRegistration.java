package com.example.demo.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "student_register")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class StudentRegistration {

	@Id
	@GenericGenerator(name = "student_id_gen" ,strategy = "com.example.demo.seq.StudentIdSeqGen")
	@GeneratedValue(generator = "student_id_gen")
	private String studentId;
	private String studentFirstName;
	private String studentLastName;
	private String studentGender;
	private String studentQualification;
	private Long stduentContactNumber;
	
	@Override
	public String toString() {
		return "StudentRegistration [studentId=" + studentId + ", studentFirstName=" + studentFirstName
				+ ", studentLastName=" + studentLastName + ", studentGender=" + studentGender
				+ ", studentQualification=" + studentQualification + ", stduentContactNumber=" + stduentContactNumber
				+ "]";
	}

	
	
	
}
