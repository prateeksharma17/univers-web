package com.prateek.service.adduniversity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prateek.model.entity.University;
import com.prateek.repository.university.UniversityRepository;

@Service
@Transactional(readOnly=true)
public class AddUniversityServiceImpl implements AddUniversityService{
	
	@Autowired
	private UniversityRepository universityRepository;

	@Override
	@Transactional
	public void addUniversity(University universityDAO) {
		University university = new University();
		university.setUniversityName(universityDAO.getUniversityName());
		university.setUniversityCountry(universityDAO.getUniversityCountry());
		university.setUniversityCity(universityDAO.getUniversityCity());
		
		universityRepository.save(university);		
	}

}
