package com.prateek.service.addstudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prateek.model.entity.Student;
import com.prateek.repository.student.StudentRepository;

@Service
@Transactional(readOnly=true)
public class AddStudentServiceImpl implements AddStudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional
	public void saveStudent(Student studentDAO) {
		Student student = new Student();
		
		student.setFirstName(studentDAO.getFirstName());
		student.setLastName(studentDAO.getLastName());
		student.setAge(studentDAO.getAge());
		student.setGender(studentDAO.getGender());
		student.setUniversity(studentDAO.getUniversity());
		
		studentRepository.save(student);		
	}

}
