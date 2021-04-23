package com.prateek.utils;

public enum NotificationMessages {
	
	STUDENT_SAVE_VALIDATION_ERROR_TITLE("ERROR"), 
	STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION("Fields must be filled!"), 
	
	STUDENT_SAVE_SUCCESS_TITLE("SAVE"), 
	STUDENT_SAVE_SUCCESS_DESCRIPTION("Student has been saved!"),
	
	STUDENT_REMOVE_SUCCESS_TITIE("REMOVE"), 
	STUDENT_REMOVE_SUCCESS_DESCRIPTION("Student(s) successfully removed!"), 
	STUDENT_REMOVE_BUTTON("Remove"),
	
	UNIVERSITY_SAVED_VALIDATION_ERROR_TITLE("ERROR"),
	UNIVERSITY_SAVED_VALIDATION_ERROR_DESCRIPTION("Fields must be filled!"),
	
	UNIVERSITY_SAVE_SUCCESS_TITLE("SAVE"), 
	UNIVERSITY_SAVE_SUCCESS_DESCRIPTION("University has been saved!"), 
	STUDENT_SAVE_INVALID_TITLE("ERROR"),
	STUDENT_SAVE_INVALID_DESCRIPTION("Must have atleast one university!");
	
	private final String string;
	
	private NotificationMessages(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}

}
