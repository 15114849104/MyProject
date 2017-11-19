package com.westos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.westos.dao.IPaperDao;
import com.westos.domain.Paper;
import com.westos.service.IPaperService;
@Service
@Transactional
public class PaperServiceImpl implements IPaperService {
	@Autowired
	private IPaperDao dao;
	public void delete(Integer pid) {
		dao.delete(pid);

	}

	public List<Paper> find() {
		return dao.find();
	}

	public Paper find(Integer pid) {
		return dao.find(pid);
	}

	public void save(Paper paper) {
		dao.save(paper);

	}

	public void update(Paper paper) {
		dao.update(paper);

	}

}
