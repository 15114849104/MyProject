package com.westos.dao;

import java.util.List;

import com.westos.domain.Roles;

public interface IRolesDao {
	public void save(Roles roles);
	public void delete(Integer rid);
	public void update(Roles roles);
	public List<Roles> find();
	public Roles find(Integer rid);
	
	public int getRowCount();
	public List<Roles> find(int startLine,int size);
	

}
