package com.westos.service;

import java.util.List;

import com.westos.domain.Page;
import com.westos.domain.Roles;

public interface IRolesService {
	public void save(Roles roles);
	public void delete(Integer rid);
	public void update(Roles roles);
	public List<Roles> find();
	public Roles find(Integer rid);
	
	public Page findPageData(int p,int size);
	

}
