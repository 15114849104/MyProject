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
	//�Զ�װ��
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
		//�Ȳ�ѯ������
		Query q = getSession().createQuery("FROM Question");
		//������ʼ��
		q.setFirstResult(startLine);
		//����Ҫ��ʾ������
		q.setMaxResults(size);
		return q.list();
	}

	public int getRowCount() {
		long x = (Long) getSession().createQuery("SELECT COUNT(*) FROM Question").uniqueResult();
		return (int) x;
	}

}
