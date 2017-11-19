package com.westos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.westos.dao.IRolesDao;
import com.westos.domain.Roles;
@Repository

public class RolesDaoImpl implements IRolesDao {

	@Autowired
	private SessionFactory sf;
	
	private Session getSession(){
		return sf.getCurrentSession();
	}
	
	public void delete(Integer rid) {
		Roles roles=(Roles) getSession().get(Roles.class, rid);
		getSession().delete(roles);
	}

	public List<Roles> find() {
		return getSession().createQuery("FROM Roles").list();
	}

	public Roles find(Integer rid) {
		Roles roles=(Roles) getSession().get(Roles.class, rid);
		return roles;
	}

	public void save(Roles roles) {
		getSession().save(roles);
	}

	public void update(Roles roles) {
		getSession().update(roles);
	}

	public List<Roles> find(int startLine, int size) {
		//查出分页的那些行
		Query q = getSession().createQuery("FROM Roles");
		//设置起始行
		q.setFirstResult(startLine);
		//设置要显示的行数
		q.setMaxResults(size);
		return q.list();
	}

	public int getRowCount() {
		long x=(Long) getSession().createQuery("SELECT COUNT(*) FROM Roles").uniqueResult();
		return (int) x;
	}

}
