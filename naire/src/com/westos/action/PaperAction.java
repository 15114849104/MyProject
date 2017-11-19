package com.westos.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.westos.domain.Paper;
import com.westos.domain.Question;
import com.westos.service.IPaperService;
import com.westos.service.IQuestionService;

@Controller
@RequestMapping("/paper")
public class PaperAction {
	
	@Autowired
	private IPaperService service;
	@Autowired
	private IQuestionService qService;
	
	@RequestMapping("/find")
	public void find(HttpServletResponse response) throws Exception{
		List<Paper> list = service.find();
		JsonConfig jc =new JsonConfig();
		jc.setExcludes(new String[]{"scores","questions","papers","roles"});
		//������Ϊ�з���ʱ���������������Ҫ��������
		jc.registerJsonValueProcessor(Date.class, new JsonValueProcessor(){

			public Object processArrayValue(Object arg0, JsonConfig arg1) {
				return null;
			}

			public Object processObjectValue(String arg0, Object arg1,JsonConfig arg2) {
				//����������ʱ������������ǵ�ǰ����µ��ʾ��ʱ��
				Date dd=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				
				return sdf.format(dd);
			}
			
		});
		String json = JSONArray.fromObject(list, jc).toString();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json);
	}
	@RequestMapping("/findOne")
	public void findOne(int pid,HttpServletResponse response) throws Exception{
		Paper paper = service.find(pid);
		List<Question> list = new ArrayList(paper.getUser().getRoles().getQuestions());
		JsonConfig jc = new JsonConfig();
		jc.setExcludes(new String[]{"roles","scores","papers"});
		
		//��˼���ǰ�list�е�jc�е��ų����⣬�����Ĵ��json
		String json = JSONArray.fromObject(list, jc).toString();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json);
	}
	@RequestMapping("save")
	public void save(Paper paper,HttpServletResponse response) throws Exception{
		service.save(paper);
		//System.out.println(paper);
		response.getWriter().write("{}");
	}
	@RequestMapping("/delete")
	public void delete(int pid,HttpServletResponse response) throws Exception{
		service.delete(pid);
		response.getWriter().write("{}");
	}
	@RequestMapping("/update")
	public void update(Paper paper,HttpServletResponse response) throws Exception{
		service.update(paper);
		response.getWriter().write("{}");
	}
	@RequestMapping("/frozen")
	public void frozen(int pid,HttpServletResponse response) throws Exception{
		Paper paper = service.find(pid);
		paper.setStatus(0);
		service.update(paper);
		response.getWriter().write("{}");
	}
	
	@RequestMapping("/active")
	public void active(int pid,HttpServletResponse response) throws Exception{
		Paper paper = service.find(pid);
		paper.setStatus(1);
		service.update(paper);
		response.getWriter().write("{}");
	}
	@RequestMapping("/addQuestionToPaperUI")
	public void addQuestionToPaperUI(int pid,HttpServletResponse response) throws Exception{
		Paper paper = service.find(pid);
		//��ȡ������Ϣ
		//1.�ʾ���⣬������������
		//��Paper.jsp�Ѿ����
		//2.�ʾ������е�����
		  List list1 = new ArrayList(paper.getQuestions());
		//3.���е�����
		   List list2 = new ArrayList(paper.getUser().getRoles().getQuestions());
		   list2.removeAll(list1);
		   
		   JsonConfig jc = new JsonConfig();
		   jc.setExcludes(new String[]{"roles","scores","papers"});
		   String part2 = JSONArray.fromObject(list1, jc).toString();
		   String part3 = JSONArray.fromObject(list2, jc).toString();
		   //����ļӺ��ǰ�part2��ǰ���ð�������������Ӻ��������ӵ�����
		   //����ѵõ������ݴ����һ�����JSON
		   String json="{\"two\":"+part2+",\"three\":"+part3+"}";
		   
		   response.setCharacterEncoding("UTF-8");
		   response.getWriter().write(json);
	}
	@RequestMapping("/addQuestionToPaper")
	public void addQuestionToPaper(int pid,int qid[],HttpServletResponse response) throws Exception{
		//System.out.println(pid);
	    //System.out.println(Arrays.toString(qid));
		//�Ȳ鵽���ĸ��ʾ�Ȼ���������ѡ������ID�鵽���⣬Ȼ����������ӵ��ʼ�鵽�������ʾ���
		Paper paper = service.find(pid);
        for(int x : qid){
		 Question question = qService.find(x);
		 paper.getQuestions().add(question);
        }
        service.update(paper);
        response.getWriter().write("{}");
	}
	@RequestMapping("/removeQuestionFromPaper")
	public void removeQuestionFromPaper(int pid,int qid[],HttpServletResponse response) throws Exception{
//		System.out.println(pid);
//		for(int i=0;i<qid.length;i++){System.out.println(qid[i]);}
		Paper paper = service.find(pid);
		List list = new ArrayList(paper.getQuestions());//�õ�����ʾ����������
		//�����������򹤳̵õ�����set���ϣ�������ת����List����
		//�����Ƕ���һ��X��X��0��ʼ��С��qid
		for(int x : qid){
			Question question = qService.find(x);
			list.remove(question);
		}
		//����ɾ�����˺�Ҫ��ɾ�����list���⼯�Ϸ����ʾ���
		//�����������򹤳���set������������ת������list�������е�ת������
		paper.setQuestions(new HashSet(list));
		service.update(paper);
		
		response.getWriter().write("{}");
	}
	@RequestMapping("/showOnePaperUI")
	public String showOnePaperUI(int pid,HttpServletRequest request){
		Paper p = service.find(pid);
		request.setAttribute("p", p);
		return "front/showOnePaper";
	}

}
