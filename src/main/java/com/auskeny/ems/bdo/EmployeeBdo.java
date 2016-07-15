package com.auskeny.ems.bdo;

import java.util.List;

import com.auskeny.ems.hibernate.dao.EmployeeDao;
import com.auskeny.ems.pojo.Employee;

public class EmployeeBdo {

	EmployeeDao empDao = new EmployeeDao();

	// getting list of all employee
	public List<com.auskeny.ems.hibernate.pojo.Employee> getAllEmployee() {
		System.err.println(" inside getAllEmployee BDO method");
		return empDao.findAll();
	}

	// getting employee details using employee id
	public com.auskeny.ems.hibernate.pojo.Employee getEmployee(int id) {

		com.auskeny.ems.hibernate.pojo.Employee emp = null;
		System.out.println("Inside getEmployee Method...");
		System.out.println("Employee Id :" + id);
		return emp = empDao.findById(id);
	}

	// add employee details
	public int addEmployee(com.auskeny.ems.hibernate.pojo.Employee emp2) {
		 System.err.println(" Inside AddEmployee BDO Method");
		emp2.setEmpIdPk(genarateNewId());
		System.err.println(" ***********-->" + emp2.getEmpIdPk() + " " + emp2.getEmpName() + " " + emp2.getEmpEmail());

		List<com.auskeny.ems.hibernate.pojo.Employee> empList = getAllEmployee();
		boolean empExists = false;
		for (com.auskeny.ems.hibernate.pojo.Employee emp : empList) {
			if (emp.getEmpEmail().equals(emp2.getEmpEmail())) {
				empExists = true;
				 System.err.println("---------> Duplicate Records...");
				break;
			}
		}
		if (!empExists) {
			return empDao.save(emp2);
		}
		else
		   return 0;
	}

	// generate new employeeId
	public int genarateNewId() {
		int newID = empDao.getLastID() + 1;
		System.err.println("*****************New ID ************-->" + newID);
		return newID;
	}

	// update employee details
	public int updateEmployee(com.auskeny.ems.hibernate.pojo.Employee emp1) {
		System.out.println("Inside updateEmployee BDO Method...");
		System.out.println("Employee Id :" + emp1.getEmpIdPk());

		// code for updating employee details if already exit

		List<com.auskeny.ems.hibernate.pojo.Employee> empList = getAllEmployee();
		boolean empExists = false;
		for (com.auskeny.ems.hibernate.pojo.Employee emp : empList) {
			if (emp.getEmpIdPk() == emp1.getEmpIdPk()) {
				empExists = true;
				break;
			}
		}
		if (empExists) {
			empDao.update(emp1);
			return 1;
		}
		return 0;
	}

	// delete employee details
	public int deleteEmployee(int empId) {
		System.out.println("Inside deleteEmployee Method...");
		System.out.println("Employee Id :" + empId);
		com.auskeny.ems.hibernate.pojo.Employee emp1 = null;
		List<com.auskeny.ems.hibernate.pojo.Employee> empList = getAllEmployee();
		boolean empExists = false;
		for (com.auskeny.ems.hibernate.pojo.Employee emp : empList) {
			if (emp.getEmpIdPk() == empId) {
				empExists = true;
				emp1 = emp;
				break;
			}
		}
		if (empExists) {
			empDao.delete(emp1);
			return 1;
		}
		return 0;
	}

}
