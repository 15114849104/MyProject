package com.westos.service;

import java.util.List;

import com.westos.domain.Paper;

public interface IPaperService {
	public void delete(Integer pid);
	public void save(Paper paper);
	public void update(Paper paper);
	public List<Paper> find();
	public Paper find(Integer pid);

}
