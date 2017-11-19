package com.westos.dao;

import java.util.List;

import com.westos.domain.Score;

public interface IScoreDao {
	
	public void save(Score score);
	public void delete(Integer sid);
	public void update(Score score);
	public List<Score> find();
	public Score find(Integer sid);

}
