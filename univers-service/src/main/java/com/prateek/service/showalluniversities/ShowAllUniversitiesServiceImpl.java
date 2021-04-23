package com.prateek.service.showalluniversities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prateek.model.entity.University;
import com.prateek.repository.university.UniversityRepository;

@Service
@Transactional(readOnly=true)
public class ShowAllUniversitiesServiceImpl implements ShowAllUniversitiesService{
	
	@Autowired
	private UniversityRepository universityRepository;

	@Override
	public List<University> getAllUniversities() {
		return universityRepository.getAllUniversities();
	}

}
