package com.auskeny.ems.hibernate.pojo;

import java.io.Serializable;

public class Employee implements Serializable {

	// field

	private int empIdPk;
	private String empName;
	private String empEmail;

	// Constructor

	public Employee() {
	}

	public Employee(int empIdPk, String empName, String empEmail) {
		super();
		this.empIdPk = empIdPk;
		this.empName = empName;
		this.empEmail = empEmail;
	}

	public int getEmpIdPk() {
		return empIdPk;
	}

	public void setEmpIdPk(int empIdPk) {
		this.empIdPk = empIdPk;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empEmail == null) ? 0 : empEmail.hashCode());
		result = prime * result + empIdPk;
		result = prime * result + ((empName == null) ? 0 : empName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (empEmail == null) {
			if (other.empEmail != null)
				return false;
		} else if (!empEmail.equals(other.empEmail))
			return false;
		if (empIdPk != other.empIdPk)
			return false;
		if (empName == null) {
			if (other.empName != null)
				return false;
		} else if (!empName.equals(other.empName))
			return false;
		return true;
	}

}
