package com.westos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.westos.dao.IQuestionDao;
import com.westos.domain.Question;
@Repository
public class QuestionDaoImpl implements IQuestionDao {
	//自动装配
	@Autowired
	private SessionFactory sf;
	
	private Session getSession(){
		return sf.getCurrentSession();
	}

	public void delete(Integer qid) {
		Question question = (Question) getSession().get(Question.class, qid);
		getSession().delete(question);
	}

	public List<Question> find() {
		return getSession().createQuery("FROM Question").list();
	}

	public Question find(Integer qid) {
		 Question question=(Question) getSession().get(Question.class, qid);
		 return question;
	}

	public void save(Question question) {
		getSession().save(question);
	}

	public void update(Question question) {
		getSession().update(question);
	}

	public List<Question> find(int startLine, int size) {
		//先查询好数据
		Query q = getSession().createQuery("FROM Question");
		//设置起始行
		q.setFirstResult(startLine);
		//设置要显示的行数
		q.setMaxResults(size);
		return q.list();
	}

	public int getRowCount() {
		long x = (Long) getSession().createQuery("SELECT COUNT(*) FROM Question").uniqueResult();
		return (int) x;
	}

}
