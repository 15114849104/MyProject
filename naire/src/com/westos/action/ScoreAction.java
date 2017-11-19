package com.westos.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.westos.domain.Paper;
import com.westos.domain.Score;
import com.westos.domain.User;
import com.westos.exception.BuNengChongFuDaFenException;
import com.westos.service.IPaperService;
import com.westos.service.IQuestionService;
import com.westos.service.IScoreService;


@Controller
@RequestMapping("/score")
public class ScoreAction {
	
	@Autowired
	private IScoreService service;
	@Autowired
	private IPaperService pservice;
	@Autowired
	private IQuestionService qservice;
	
	@RequestMapping("/save")
	public String save(int pid,int[] qid,int[] score,HttpSession session)throws BuNengChongFuDaFenException{
		try{
			Paper paper = pservice.find(pid);
			
			for(int i=0;i<qid.length;i++){
				Score s = new Score();
				s.setPaper(paper);
				//这里放的是问题 而不是问题ID
				s.setQuestion(qservice.find(qid[i]));
				s.setScore(score[i]);
				s.setUser((User) session.getAttribute("user"));
				
				service.save(s);
			}
		}catch(Exception e){
			throw new BuNengChongFuDaFenException("你已经打过分了，你到底还想咋样？？？");
		}
		//如果是转发，则自动前面加斜杠，后面加jsp，如果是重定向，则都要做自己写
		return "redirect:/front/index.jsp";
	}
	@RequestMapping("/seeScore")
	public String seeScore(int pid,HttpServletRequest request){
		Paper p = pservice.find(pid);
		//根据问卷得到问题
		List list1 = new ArrayList(p.getQuestions());
        request.setAttribute("list1", list1);
        
        
        //根据问卷得到分数和人名字
        Map map = new HashMap();//map里面是去除重复，放键值对，通过键找到值
       List list = new ArrayList(p.getScores()); //list里面放的是分数表
       for(int i=0;i<list.size();i++){
    	   Score s =(Score) list.get(i);//放的第一个分数表
    	   String realname = s.getUser().getRealname();
    	   if(map.containsKey(realname)){//这里是判断map里面是否有这个人
    		   List fenshu = (List) map.get(realname);//这里通过键找到的是值，也就是分数那个数组（list），然后用list接一下
    	       fenshu.add(s.getScore());
    	       map.put(realname, fenshu);
    	   }else{
    		   List fenshu = new ArrayList();//需要里面放的分数的list
    		   fenshu.add(s.getScore());
    		   //分数放到fenshu里面了，接下来把名字和分数分别当做键和值放到map里面
    		   map.put(realname, fenshu);
    	   }
    	   
       }
       request.setAttribute("map", map);
       
		return "front/highcharts";
	}

}







