package com.prateek.ui.universities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.prateek.model.entity.University;
import com.prateek.service.showalluniversities.ShowAllUniversitiesService;
import com.prateek.ui.commons.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class ShowUniversitiesLayoutFactory implements UIComponentBuilder{
	
	private List<University> universities;
	private BeanItemContainer<University> container;
	
	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;
	
	private class ShowUniversititesLayout extends VerticalLayout{
		
		private Grid universityTable;

		public ShowUniversititesLayout load() {
			universities = showAllUniversitiesService.getAllUniversities();
			return this;
		}
		 
		public ShowUniversititesLayout init() {
			setMargin(true);
			container = new BeanItemContainer<>(University.class, universities);
			universityTable = new Grid(container);
			universityTable.setColumnOrder("universityName","universityCountry","universityCity");
			universityTable.removeColumn("id");
			universityTable.setImmediate(true);
			
			return this;
		}
		
		public ShowUniversititesLayout layout() {
			addComponent(universityTable);
			return this;
		}
	}

	
	public void refreshTable() {
		universities = showAllUniversitiesService.getAllUniversities();
		container.removeAllItems();
		container.addAll(universities);		
	}	
	
	@Override
	public Component createComponent() {
		return new ShowUniversititesLayout().load().init().layout();
	}
	
}
