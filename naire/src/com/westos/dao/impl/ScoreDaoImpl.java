package com.westos.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.westos.dao.IScoreDao;
import com.westos.domain.Score;
@Repository
public class ScoreDaoImpl implements IScoreDao {
	
	@Autowired
	private SessionFactory sf;
	
	private Session getSession(){
		return sf.getCurrentSession();
	}

	public void delete(Integer sid) {
		Score score = (Score) getSession().get(Score.class, sid);
		getSession().delete(score);
		
	}

	public List<Score> find() {
		return getSession().createQuery("FROM Score").list();//查询出所有的score并且放到list中
	}

	public Score find(Integer sid) {
		Score score = (Score) getSession().get(Score.class, sid);//这里是根据ID得到一组数据
		return score;
	}

	public void save(Score score) {
		getSession().save(score);

	}

	public void update(Score score) {
		getSession().update(score);

	}

}
