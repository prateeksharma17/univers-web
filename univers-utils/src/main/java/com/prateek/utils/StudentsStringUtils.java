package com.prateek.utils;

public enum StudentsStringUtils {
	
	MAIN_MENU("MAIN MENU"),
	SHOW_ALL_STUDENTS("SHOW ALL STUDENTS"),
	FIRST_NAME("First Name"),
	LAST_NAME("Last Name"),
	AGE("Age"),
	GENDER("Gender"),
	SAVE("Save"),
	CLEAR("Clear"),
	UNIVERSITY("University");
	
	private final String string;
	
	private StudentsStringUtils(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;		
	}

}
