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
		//这里因为有发布时间这个变量，所以要处理日期
		jc.registerJsonValueProcessor(Date.class, new JsonValueProcessor(){

			public Object processArrayValue(Object arg0, JsonConfig arg1) {
				return null;
			}

			public Object processObjectValue(String arg0, Object arg1,JsonConfig arg2) {
				//这里这样到时候出来的日期是当前添加新的问卷的时间
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
		
		//意思就是把list中的jc中的排除在外，其他的打成json
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
		//获取三项信息
		//1.问卷标题，调查对象的名字
		//在Paper.jsp已经添加
		//2.问卷中已有的问题
		  List list1 = new ArrayList(paper.getQuestions());
		//3.所有的问题
		   List list2 = new ArrayList(paper.getUser().getRoles().getQuestions());
		   list2.removeAll(list1);
		   
		   JsonConfig jc = new JsonConfig();
		   jc.setExcludes(new String[]{"roles","scores","papers"});
		   String part2 = JSONArray.fromObject(list1, jc).toString();
		   String part3 = JSONArray.fromObject(list2, jc).toString();
		   //这里的加号是把part2和前面的冒号连接起来，加号是起到连接的作用
		   //这里把得到的数据打包成一个大的JSON
		   String json="{\"two\":"+part2+",\"three\":"+part3+"}";
		   
		   response.setCharacterEncoding("UTF-8");
		   response.getWriter().write(json);
	}
	@RequestMapping("/addQuestionToPaper")
	public void addQuestionToPaper(int pid,int qid[],HttpServletResponse response) throws Exception{
		//System.out.println(pid);
	    //System.out.println(Arrays.toString(qid));
		//先查到是哪个问卷，然后根据所勾选的问题ID查到问题，然后把这个问题加到最开始查到的所属问卷中
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
		List list = new ArrayList(paper.getQuestions());//得到这个问卷的所有问题
		//这里由于逆向工程得到的是set集合，所以先转换成List类型
		//这里是定义一个X，X重0开始，小于qid
		for(int x : qid){
			Question question = qService.find(x);
			list.remove(question);
		}
		//这里删除完了后，要把删除后的list问题集合放入问卷中
		//但是由于逆向工程是set，而这里上面转换成了list，所以有的转换回来
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
