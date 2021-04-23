package com.prateek.ui.universities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.prateek.model.entity.University;
import com.prateek.service.showalluniversities.ShowAllUniversitiesService;
import com.prateek.service.universitystatistics.UniversityStatisticsService;
import com.prateek.ui.commons.UIComponentBuilder;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class StatisticsUniversityLayoutFactory implements UIComponentBuilder{
	
	private List<University> universities;
	private StatisticsUniversityLayout statisticsUniversityLayout;
	
	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;
	
	@Autowired
	private UniversityStatisticsService universityStatisticsService;
	
	private class StatisticsUniversityLayout extends VerticalLayout{

		public StatisticsUniversityLayout load() {
			universities = showAllUniversitiesService.getAllUniversities();
			return this;
		}

		public StatisticsUniversityLayout layout() {
			setMargin(true);
			for(University university : universities) {
				int numOfStudents = universityStatisticsService.getNumOfStudentsForUniversity(university.getId());
				Label label = new Label("<p><b>"+university.getUniversityName()+"</b>"+" - "+numOfStudents+" student(s)"+"</p>", ContentMode.HTML);
				addComponent(label);			
			}			
			return this;
		}
		
	}
	
	public void refresh() {
		if(statisticsUniversityLayout==null) return;
		statisticsUniversityLayout.removeAllComponents();
		statisticsUniversityLayout.load();
		statisticsUniversityLayout.layout();		
	}

	@Override
	public Component createComponent() {
		statisticsUniversityLayout = new StatisticsUniversityLayout();
		return statisticsUniversityLayout.load().layout();
	}	

}
