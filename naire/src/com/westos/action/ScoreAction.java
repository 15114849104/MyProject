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
				//����ŵ������� ����������ID
				s.setQuestion(qservice.find(qid[i]));
				s.setScore(score[i]);
				s.setUser((User) session.getAttribute("user"));
				
				service.save(s);
			}
		}catch(Exception e){
			throw new BuNengChongFuDaFenException("���Ѿ�������ˣ��㵽�׻���զ��������");
		}
		//�����ת�������Զ�ǰ���б�ܣ������jsp��������ض�����Ҫ���Լ�д
		return "redirect:/front/index.jsp";
	}
	@RequestMapping("/seeScore")
	public String seeScore(int pid,HttpServletRequest request){
		Paper p = pservice.find(pid);
		//�����ʾ�õ�����
		List list1 = new ArrayList(p.getQuestions());
        request.setAttribute("list1", list1);
        
        
        //�����ʾ�õ�������������
        Map map = new HashMap();//map������ȥ���ظ����ż�ֵ�ԣ�ͨ�����ҵ�ֵ
       List list = new ArrayList(p.getScores()); //list����ŵ��Ƿ�����
       for(int i=0;i<list.size();i++){
    	   Score s =(Score) list.get(i);//�ŵĵ�һ��������
    	   String realname = s.getUser().getRealname();
    	   if(map.containsKey(realname)){//�������ж�map�����Ƿ��������
    		   List fenshu = (List) map.get(realname);//����ͨ�����ҵ�����ֵ��Ҳ���Ƿ����Ǹ����飨list����Ȼ����list��һ��
    	       fenshu.add(s.getScore());
    	       map.put(realname, fenshu);
    	   }else{
    		   List fenshu = new ArrayList();//��Ҫ����ŵķ�����list
    		   fenshu.add(s.getScore());
    		   //�����ŵ�fenshu�����ˣ������������ֺͷ����ֱ�������ֵ�ŵ�map����
    		   map.put(realname, fenshu);
    	   }
    	   
       }
       request.setAttribute("map", map);
       
		return "front/highcharts";
	}

}







