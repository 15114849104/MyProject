package com.westos.service;

import java.util.List;

import com.westos.domain.Page;
import com.westos.domain.Question;

public interface IQuestionService {
	public void save(Question question);
	public void delete(Integer qid);
	public void update(Question question);
	public List<Question> find();
	public Question find(Integer qid);
	
	public Page findPageDate(int p,int size);

}
