package com.prateek.ui.students;

import com.prateek.utils.Gender;
import com.prateek.utils.NotificationMessages;
import com.prateek.utils.StudentsStringUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.prateek.model.entity.Student;
import com.prateek.model.entity.University;
import com.prateek.service.addstudent.AddStudentService;
import com.prateek.service.showalluniversities.ShowAllUniversitiesService;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class AddStudentMainLayoutFactory{
	
	private class AddStudentMainLayout extends VerticalLayout implements Button.ClickListener{
		
		private TextField firstName;
		private TextField lastName;
		private TextField age;
		private ComboBox gender;
		private ComboBox university;
		private Button saveButton;
		private Button clearButton;
		
		private BeanFieldGroup<Student> fieldGroup;
		private Student student;
		
		private StudentSavedListener studentSavedListener;
		
		public AddStudentMainLayout(StudentSavedListener studentSavedListener) {
			this.studentSavedListener = studentSavedListener;
		}
		
		public AddStudentMainLayout init() {
			
			fieldGroup = new BeanFieldGroup<>(Student.class);
			student = new Student();
			
			firstName = new TextField(StudentsStringUtils.FIRST_NAME.getString());
			lastName = new TextField(StudentsStringUtils.LAST_NAME.getString());
			age = new TextField(StudentsStringUtils.AGE.getString());
			
			gender = new ComboBox(StudentsStringUtils.GENDER.getString());
			gender.addItem(Gender.MALE.getString());
			gender.addItem(Gender.FEMALE.getString());
			
			saveButton = new Button(StudentsStringUtils.SAVE.getString());
			clearButton = new Button(StudentsStringUtils.CLEAR.getString());
			
			university = new ComboBox(StudentsStringUtils.UNIVERSITY.getString());
			university.setWidth("100%");
			
			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			clearButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
			
			saveButton.addClickListener(this);
			clearButton.addClickListener(this);			
			
			firstName.setNullRepresentation("");
			lastName.setNullRepresentation("");
			age.setNullRepresentation("");
						
			return this;
		}
		
		
		public AddStudentMainLayout load() {
			List<University> universities = showAllUniversitiesService.getAllUniversities();
			university.addItems(universities);
			
			return this;
		}
		
		public AddStudentMainLayout bind() {
			fieldGroup.bindMemberFields(this);
			fieldGroup.setItemDataSource(student);
			
			return this;
		}
		
		
		public Component layout() {
			setMargin(true);
			
			GridLayout gridLayout = new GridLayout(2,4);
			gridLayout.setSizeUndefined();
			gridLayout.setSpacing(true);
			
			gridLayout.addComponent(firstName, 0 , 0);
			gridLayout.addComponent(lastName, 1 , 0);
			
			gridLayout.addComponent(age, 0, 1);
			gridLayout.addComponent(gender, 1, 1);
			
			gridLayout.addComponent(university, 0, 2, 1, 2);
			
			gridLayout.addComponent(new HorizontalLayout(saveButton, clearButton), 0 , 3);
			
			return gridLayout;
		}


		@Override
		public void buttonClick(ClickEvent event) {
			if(event.getSource() == this.saveButton) {
				save();
			}else {
				clearField();
			}
			
		}
		
		private boolean isSaveOperationValid() {
			return showAllUniversitiesService.getAllUniversities().size()!=0;
		}


		private void save() {
			
			if(!isSaveOperationValid()) {
				Notification.show(NotificationMessages.STUDENT_SAVE_INVALID_TITLE.getString(),
						NotificationMessages.STUDENT_SAVE_INVALID_DESCRIPTION.getString(),
						Type.ERROR_MESSAGE);
				
				return;
			}
			
			try {
				fieldGroup.commit();
			} catch (CommitException e) {				
				Notification.show(NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_TITLE.getString(),
						NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(),
						Type.ERROR_MESSAGE);
				
				return;
			}	
			
			addStudentService.saveStudent(student);
			studentSavedListener.studentSaved();
			clearField();
			
			Notification.show(NotificationMessages.STUDENT_SAVE_SUCCESS_TITLE.getString(),
					NotificationMessages.STUDENT_SAVE_SUCCESS_DESCRIPTION.getString(),
					Type.HUMANIZED_MESSAGE);
		}


		private void clearField() {
			firstName.setValue(null);
			lastName.setValue(null);
			age.setValue(null);
			gender.setValue(null);
			university.clear();
		}		
		
	}
	
	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;

	@Autowired
	private AddStudentService addStudentService;
	
	public Component createComponent(StudentSavedListener studentSavedListener) {
		return new AddStudentMainLayout(studentSavedListener).init().load().bind().layout();
	}

}
