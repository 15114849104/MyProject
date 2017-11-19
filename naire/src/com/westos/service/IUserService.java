package com.westos.service;

import java.util.List;

import com.westos.domain.Page;
import com.westos.domain.User;

public interface IUserService {
	public void delete(Integer uid);
	public void save(User user);
	public void update(User user);
	public List<User> find();
	public User find(Integer uid);
	public User find(String account,String password);
	
	public Page findPageData(int p,int size);

}
