package com.auskeny.ems.application;

import java.util.Set;

import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("rest")

public class EmployeeApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();

		resources.add(com.auskeny.ems.controller.EmployeeController.class);
		return resources;
	}
}
