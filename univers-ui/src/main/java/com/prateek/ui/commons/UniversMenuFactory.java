package com.prateek.ui.commons;

import org.springframework.security.core.context.SecurityContextHolder;

import com.prateek.navigator.UniversNavigator;
import com.prateek.utils.StringUtils;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class UniversMenuFactory implements UIComponentBuilder{
	
	private class UniversMenu extends VerticalLayout implements Property.ValueChangeListener{
		private Tree mainMenu;
		
		public UniversMenu init() {
			mainMenu = new Tree();
			mainMenu.addValueChangeListener(this);
			return this;
		}
		
		
		public UniversMenu layout() {
			setWidth("100%");
			setHeightUndefined();
			
			mainMenu.addItem(StringUtils.MENU_STUDENT.getString());
			mainMenu.addItem(StringUtils.MENU_UNIVERSITY.getString());
			mainMenu.addItem("LOGOUT");
			
			mainMenu.expandItem(StringUtils.MENU_STUDENT.getString());
			mainMenu.expandItem(StringUtils.MENU_UNIVERSITY.getString());
			mainMenu.expandItem("LOGOUT");
			
			mainMenu.addItem(StringUtils.MENU_ADD_STUDENT.getString());
			mainMenu.addItem(StringUtils.MENU_REMOVE_STUDENT.getString());
			mainMenu.setChildrenAllowed(StringUtils.MENU_ADD_STUDENT.getString(), false);
			mainMenu.setChildrenAllowed(StringUtils.MENU_REMOVE_STUDENT.getString(), false);
			mainMenu.setParent(StringUtils.MENU_ADD_STUDENT.getString(), StringUtils.MENU_STUDENT.getString());
			mainMenu.setParent(StringUtils.MENU_REMOVE_STUDENT.getString(), StringUtils.MENU_STUDENT.getString());
			
			mainMenu.addItem("Logout");
			mainMenu.setChildrenAllowed("Logout", false);
			mainMenu.setParent("Logout", "LOGOUT");
			
			mainMenu.addItem(StringUtils.MENU_OPERATIONS.getString());
			mainMenu.setChildrenAllowed(StringUtils.MENU_OPERATIONS.getString(), false);
			mainMenu.setParent(StringUtils.MENU_OPERATIONS.getString(), StringUtils.MENU_UNIVERSITY.getString());
			
			addComponent(mainMenu);
			
			return this;
		}


		@Override
		public void valueChange(ValueChangeEvent event) {
			String selectedItemPath = (String) event.getProperty().getValue();
			if(selectedItemPath==null) return;
			
			if(selectedItemPath.equals("Logout")) {
				SecurityContextHolder.clearContext();
				UI.getCurrent().getPage().setLocation("/univers-web/login");
			}
			
			String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
			UniversNavigator.navigate(path);
		}
	}
	

	@Override
	public Component createComponent() {
		return new UniversMenu().init().layout();
	}

}
