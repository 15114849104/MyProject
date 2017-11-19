package com.westos.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.westos.dao.IPaperDao;
import com.westos.domain.Paper;
@Repository
public class PaperDaoImpl implements IPaperDao {
	@Autowired
	private SessionFactory sf;
	
	private Session getSession(){
		return sf.getCurrentSession();
	}

	public void delete(Integer pid) {
		Paper paper = (Paper) getSession().get(Paper.class, pid);
		getSession().delete(paper);
	}

	public List<Paper> find() {
		return getSession().createQuery("FROM Paper").list();
	}

	public Paper find(Integer pid) {
		Paper paper = (Paper) getSession().get(Paper.class, pid);//这是根据ID查出这一组数据
		return paper;
	}

	public void save(Paper paper) {
		getSession().save(paper);

	}

	public void update(Paper paper) {
		getSession().update(paper);

	}

}
