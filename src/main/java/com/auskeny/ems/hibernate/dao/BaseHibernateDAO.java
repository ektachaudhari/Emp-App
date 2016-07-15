package com.auskeny.ems.hibernate.dao;

import org.hibernate.Session;

import com.auskeny.ems.hibernate.util.HibernateSessionFactory;



public class BaseHibernateDAO implements IBaseHibernateDAO {

	@Override
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

}
