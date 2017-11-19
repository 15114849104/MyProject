package com.westos.dao.impl;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.westos.dao.IUserDao;
import com.westos.domain.User;
@Repository
public class UserDaoImpl implements IUserDao {
	
	@Autowired
	private SessionFactory sf;
	
	private Session getSession(){
		return sf.getCurrentSession();
	}
	

	public void delete(Integer uid) {
		User user = (User) getSession().get(User.class, uid);
		getSession().delete(user);

	}

	public List<User> find() {
		
		return getSession().createQuery("FROM User").list();
	}

	public User find(Integer uid) {
		User user = (User) getSession().get(User.class, uid);
		return user;
	}

	public void save(User user) {
		getSession().save(user);

	}

	public void update(User user) {
		getSession().update(user);

	}


	public List<User> find(int startLine, int size) {
		Query q = getSession().createQuery("FROM User");
		q.setFirstResult(startLine);
		q.setMaxResults(size);
		//把查询到的放到list里面
		return q.list();
	}


	public int getRowCount() {
		long x= (Long) getSession().createQuery("SELECT COUNT(*) FROM User").uniqueResult();
		return (int) x;
	}


	public User find(String account, String password) {
		Query q = getSession().createQuery("FROM User WHERE account = ? and password = ?");
		q.setParameter(0, account);
		q.setParameter(1, password);
		//这里用uniqueResult是因为用户名account必须是唯一的，查到的数据必须是一个，
		//如果是多个就会报异常，如果是一个就会返回一个object（user）也就是我们需要的，
		//如果是没找到就会返回一个null
		return (User) q.uniqueResult();
	}

}
