package com.javainuse.interceptor.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.Employee;

@RestController
public class LoggerController {
	
	static List<Employee> empList = getEmpList();
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/logger")
	public String executeLogger() {
		log.info("inside the executeLogger method");
		return "Hello World Logger Interceptor";
	}
	
	@RequestMapping("/getEmployee")
	public Employee getEmp() {
		return empList.get(0);
	}
	
	@PostMapping("/addEmployee")
	public List<Employee> addEmp(@RequestBody Employee emp) {
		empList.add(emp);
		return empList;
	}
	
	public static List<Employee> getEmpList() {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(1, "manas"));
		empList.add(new Employee(2, "Muna"));
		return empList;
	}
}