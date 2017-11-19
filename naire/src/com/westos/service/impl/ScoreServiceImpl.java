package com.westos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.westos.dao.IScoreDao;
import com.westos.domain.Score;
import com.westos.service.IScoreService;

@Service
@Transactional
public class ScoreServiceImpl implements IScoreService {
	@Autowired
	private IScoreDao dao;

	public void delete(Integer sid) {
		dao.delete(sid);

	}

	public List<Score> find() {
		return dao.find();
	}

	public Score find(Integer sid) {
		return dao.find(sid);
	}

	public void save(Score score) {
		dao.save(score);

	}

	public void update(Score score) {
		dao.update(score);

	}

}
