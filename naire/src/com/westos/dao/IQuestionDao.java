package com.westos.dao;

import java.util.List;

import com.westos.domain.Question;

public interface IQuestionDao {
	public void save(Question question);
	public void delete(Integer qid);
	public void update(Question question);
	public List<Question> find();
	public Question find(Integer qid);
	
	public int getRowCount();
	
	public List<Question> find(int startLine,int size);

}
