package com.westos.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.westos.domain.Page;
import com.westos.domain.Question;
import com.westos.service.IQuestionService;

@Controller
@RequestMapping("/question")
public class QuestionAction {
	
	@Autowired
	private IQuestionService service;
	
	@RequestMapping("/find")
	public void find(int page,int rows,HttpServletResponse response) throws Exception{
		//easyUi会自动传过来两个值分别为page和rows，分别代表当前页p和每页显示行数size
		Page pp= service.findPageDate(page, rows);
		JsonConfig jc=new JsonConfig();
		jc.setExcludes(new String[]{"scores","papers","users","questions"});
		String json = JSONArray.fromObject(pp.getList(),jc).toString();
		//在往json里面传一个总行数
		json="{\"total\":\""+pp.getRowCount()+"\",\"rows\":"+json+"}";
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json);
	}
	@RequestMapping("/save")
	public void save(Question q,HttpServletResponse response) throws Exception{
		
		service.save(q);
		response.getWriter().write("{}");
	}
	@RequestMapping("/delete")
	public void delete(int qid,HttpServletResponse response) throws Exception{
		
		service.delete(qid);
		response.getWriter().write("{}");
	}
	@RequestMapping("/update")
	public void update(Question q,HttpServletResponse response) throws Exception{
		
		service.update(q);
		response.getWriter().write("{}");
		
	}

}
