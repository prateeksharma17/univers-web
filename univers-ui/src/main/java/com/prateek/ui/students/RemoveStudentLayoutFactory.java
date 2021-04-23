package com.prateek.ui.students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.prateek.model.entity.Student;
import com.prateek.service.removestudent.RemoveStudentService;
import com.prateek.service.showallstudents.ShowAllStudentsService;
import com.prateek.ui.commons.UniversMainUI;
import com.prateek.utils.NotificationMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name=RemoveStudentLayoutFactory.NAME, ui=UniversMainUI.class)
public class RemoveStudentLayoutFactory extends VerticalLayout implements View, Button.ClickListener{
	
	public static final String NAME = "removestudent";
	private Grid removeStudentTable;
	private Button removeStudentsButton;
	private List<Student> students;
	
	@Autowired
	private ShowAllStudentsService allStudentsService;
	
	@Autowired
	private RemoveStudentService removeStudentService;
	
	private void addLayout() {
		removeStudentsButton = new Button(NotificationMessages.STUDENT_REMOVE_BUTTON.getString());
		setMargin(true);
		BeanItemContainer<Student> container = new BeanItemContainer<>(Student.class, students);
		removeStudentTable = new Grid(container);
		removeStudentTable.setColumnOrder("firstName","lastName","age","gender");
		removeStudentTable.removeColumn("id");
		removeStudentTable.setImmediate(true);
		removeStudentTable.setSelectionMode(SelectionMode.MULTI);
		
		removeStudentsButton.addClickListener(this);
		removeStudentsButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		addComponent(removeStudentsButton);
		addComponent(removeStudentTable);
		
	}
	
	
	private void loadStudents() {
		students = allStudentsService.getAllStudents();
	}
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		MultiSelectionModel selectionModel = (MultiSelectionModel) removeStudentTable.getSelectionModel();
		
		for(Object selectedItem : selectionModel.getSelectedRows()) {
			Student student = (Student) selectedItem;
			removeStudentTable.getContainerDataSource().removeItem(student);
			removeStudentService.removeStudent(student);
		}
		
		Notification.show(NotificationMessages.STUDENT_REMOVE_SUCCESS_TITIE.getString(),
				NotificationMessages.STUDENT_REMOVE_SUCCESS_DESCRIPTION.getString(),
				Type.WARNING_MESSAGE);
		
		removeStudentTable.getSelectionModel().reset();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		if(removeStudentTable!=null) return;
		
		loadStudents();
		addLayout();
		
	}

	

}
