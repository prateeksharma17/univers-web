package com.prateek.service.removestudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prateek.model.entity.Student;
import com.prateek.repository.student.StudentRepository;

@Service
@Transactional(readOnly=true)
public class RemoveStudentServiceImpl implements RemoveStudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional
	public void removeStudent(Student student) {
		studentRepository.delete(student);		
	}

}
