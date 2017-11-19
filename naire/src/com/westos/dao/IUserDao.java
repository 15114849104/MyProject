package com.westos.dao;

import java.util.List;

import com.westos.domain.User;

public interface IUserDao {
	
	public void save(User user);
	public void delete(Integer uid);
	public void update(User user);
	public List<User> find();
	public User find(Integer uid);
	public User find(String account,String password);
	
	public int getRowCount();
	public List<User> find(int startLine,int size);

}
