package com.westos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.westos.dao.IUserDao;
import com.westos.domain.Page;
import com.westos.domain.User;
import com.westos.service.IUserService;
@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao dao;

	public void delete(Integer uid) {
		dao.delete(uid);

	}

	public List<User> find() {
		return dao.find();
	}

	public User find(Integer uid) {
		return dao.find(uid);
	}

	public void save(User user) {
		dao.save(user);

	}

	public void update(User user) {
		dao.update(user);

	}

	public Page findPageData(int p,int size) {
		int rowCount = dao.getRowCount();
		Page page = new Page(p,rowCount,size);
		List<User> list=dao.find(page.getStartLine(), page.getSize());
		page.setList(list);
		return page;
	}

	public User find(String account, String password) {
		return dao.find(account, password);
	}

}
