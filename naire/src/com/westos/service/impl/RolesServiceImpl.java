package com.westos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.westos.dao.IRolesDao;
import com.westos.domain.Page;
import com.westos.domain.Roles;
import com.westos.service.IRolesService;
@Service
@Transactional
public class RolesServiceImpl implements IRolesService {

	@Autowired
	private IRolesDao dao;
	public void delete(Integer rid) {
		dao.delete(rid);
	}

	public List<Roles> find() {
		return dao.find();
	}

	public Roles find(Integer rid) {
		return dao.find(rid);
	}

	public void save(Roles roles) {
		dao.save(roles);
	}

	public void update(Roles roles) {
        dao.update(roles);		
	}

	public Page findPageData(int p, int size) {
		int rowCount=dao.getRowCount();
		Page page = new Page(p,rowCount,size);//从这里进入PAGE的构造器，出来后得到了
		//maxPage prevPage nextPage startLine p rowCount size
		//在查出分页那些行数据
		List<Roles> list=dao.find(page.getStartLine(), page.getSize());//在这里有了list数据
		//把list放入page中
		page.setList(list);
		return page;
	}
	
	

}
