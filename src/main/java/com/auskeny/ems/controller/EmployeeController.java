package com.auskeny.ems.controller;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.auskeny.ems.bdo.EmployeeBdo;
import com.auskeny.ems.hibernate.pojo.Employee;
import com.auskeny.ems.print.EmployeeList;
import com.auskeny.ems.print.PdfCreator;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

@Path("employee")
// @Produces(MediaType.APPLICATION_JSON)
// @Consumes(MediaType.APPLICATION_JSON)

public class EmployeeController {

	EmployeeBdo empbdo = new EmployeeBdo();
	String output = null;
	private @Context HttpServletRequest httpRequest;

	// rest service for getting all employee List
	@GET
	@Path("/empList/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getEmployeeList() {
		System.out.println("Inside getListOfEmployee() service...");
		List<Employee> empList = new ArrayList<Employee>();
		empList=empbdo.getAllEmployee();
		String OutputList= "EmpId\tEmpName\t\tEmpEmail\n";
		for(Employee emp:empList){
			OutputList=OutputList+ emp.getEmpIdPk()+"\t"+emp.getEmpName()+"\t"+emp.getEmpEmail()+"\n";
		}
		return Response.ok(OutputList,
				MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/emplistnew/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getEmployeeListNew() {
		System.out.println("Inside getListOfEmployee() service...");
		List<Employee> empList = new ArrayList<Employee>();
		empList=empbdo.getAllEmployee();
		String OutputList= "EmpId\tEmpName\t\tEmpEmail\n";
		for(Employee emp:empList){
			OutputList=OutputList+ emp.getEmpIdPk()+"\t"+emp.getEmpName()+"\t"+emp.getEmpEmail()+"\n";
		}
		return Response.ok(OutputList,
				MediaType.APPLICATION_JSON).build();
	}

	// rest service for getting employee details using employee Id
	@GET
	@Path("/emp/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getEmployee(@QueryParam("empId") int empId) {
		System.out.println("Inside getEmployee() service...with request empId :" + empId);
		 com.auskeny.ems.hibernate.pojo.Employee emp = empbdo.getEmployee(empId);
		 if(emp != null){
		   return Response.ok(emp.getEmpIdPk()+"   "+emp.getEmpName()+"   "+emp.getEmpEmail(), MediaType.APPLICATION_JSON).build();
		}
		 else {
			 return Response.ok("No Record Found", MediaType.APPLICATION_JSON).build();	 
		 }
		
	}

	// rest service for Adding employee details
	@POST
	@Path("/addEmp/")
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public Response addEmployee(@FormParam("empName") String empName,
			@FormParam("empEmail") String empEmail) {
		System.err.println("Inside addEmployee() service...");
		Employee emp = new Employee(0, empName, empEmail);
		int i = empbdo.addEmployee(emp);
		if (i > 1) {
			output = "Employee Details Added Successfully....\n Hello " + empName;
		} else {
			output = "Employee all ready exit";

		}
		return Response.ok(output, MediaType.APPLICATION_JSON).build();
	}

	// rest service for updating employee details
	@POST
	@Path("/updateEmp/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmployee(@FormParam("empId") int empId, @FormParam("empName") String empName,
			@FormParam("empEmail") String empEmail) {
		System.out.println("Inside updateEmployee() service...");
		Employee emp = new Employee(empId, empName, empEmail);
		int i = empbdo.updateEmployee(emp);
		if (i == 1) {
			output = "update Success ";
		} else {
			output = "update fail";
		}
		// return output;
		return Response.ok(output, MediaType.APPLICATION_JSON).build();
	}

	// rest service for deleting employee details using
	// @DELETE
	@GET
	@Path("/deleteEmp/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteEmployee(@QueryParam("empId") int empId) {
		System.out.println("Inside deleteEmployee() service....with request empId :" + empId);
		int i = empbdo.deleteEmployee(empId);
		if (i == 1) {
			output = "delete Success ";
		} else {
			output = "delete fail";
		}
		// return output;
		return Response.ok(output, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/printEmpList/")
	public void printEmpList(){
//		PdfCreator pdf = new PdfCreator();
//		try {
//		      Document document = new Document();
//		      PdfWriter.getInstance(document, new FileOutputStream(pdf.getFile()));
//		      document.open();
//		      pdf.addMetaData(document);
//		      pdf.addTitlePage(document);
//		      pdf.addContent(document);
//		      document.close();
//		    } catch (Exception e) {
//		      e.printStackTrace();
//		    }
		
	
		EmployeeList empls= new EmployeeList();
		try {
		      Document document = new Document();
		      PdfWriter.getInstance(document, new FileOutputStream("c:/temp/FirstPdf.pdf"));
		      document.open();
		      empls.addMetaData(document);
//		      pdf.addTitlePage(document);
		      empls.addContent(document);
		      document.close();
		      
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		}
	
}
