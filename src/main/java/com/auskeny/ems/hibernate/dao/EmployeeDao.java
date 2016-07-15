package com.auskeny.ems.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.auskeny.ems.hibernate.pojo.Employee;
import com.auskeny.ems.hibernate.util.HibernateSessionFactory;

public class EmployeeDao  {
	
	HibernateSessionFactory hsf =null;

	public int save(Employee transientInstance) {
		System.err.println("inside hibernate save method.....");
		Session session =hsf.getSession();
		Transaction trx = session.beginTransaction();
		int res = (int) session.save(transientInstance);
		trx.commit();
		session.close();
		System.err.println("Result....." + res);
		return res;
	}

	public void delete(Employee persistentInstance) {

		System.err.println("inside hibernate delete method.....");
		Session session =hsf.getSession();
		Transaction trx = session.beginTransaction();
		session.delete(persistentInstance);
		trx.commit();
		session.close();
	}

	public void update(Employee persistentInstance) {

		System.err.println("inside hibernate update method.....");
		Session session =hsf.getSession();
		session.clear();
		Transaction trx = session.beginTransaction();
		session.saveOrUpdate(persistentInstance);
		trx.commit();
		session.close();
		
	}

	public Employee findById(java.lang.Integer id) {
		Session session =hsf.getSession();
		Employee instance = (Employee) session.get("com.auskeny.ems.hibernate.pojo.Employee", id);
		return instance;
	}

	public List<Employee> findAll() {
		Session session =hsf.getSession();
		String queryString = "from Employee";
		Query queryObject = session.createQuery(queryString);
		return queryObject.list();
	}

	public int getLastID() {
		Session session =hsf.getSession();
		Criteria criteria = session.createCriteria(Employee.class).setProjection(Projections.max("empIdPk"));
		int lastId = (Integer) criteria.uniqueResult();

		System.err.println("*****************Last ID ************-->" + lastId);
		return lastId;

	}
}
