package com.westos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.westos.dao.IQuestionDao;
import com.westos.domain.Page;
import com.westos.domain.Question;
import com.westos.service.IQuestionService;
@Service
@Transactional
public class QuestionServiceImpl implements IQuestionService {
	@Autowired
	private IQuestionDao dao;

	public void delete(Integer qid) {
		dao.delete(qid);

	}

	public List<Question> find() {
		return dao.find();
	}

	public Question find(Integer qid) {
		return dao.find(qid);
	}

	public void save(Question question) {
		dao.save(question);

	}

	public void update(Question question) {
		dao.update(question);

	}

	public Page findPageDate(int p,int size) {
		int rowCount = dao.getRowCount();
		Page page = new Page(p,rowCount,size);//从这里进入PAGE的构造器，出来后得到了
		//maxPage prevPage nextPage startLine p rowCount size
		
		//查询分页数据
		List<Question> list= dao.find(page.getStartLine(),page.getSize());
		page.setList(list);
		return page;
	}

}
