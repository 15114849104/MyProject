package com.westos.test;



import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.westos.domain.Paper;
import com.westos.domain.User;
import com.westos.service.IPaperService;
import com.westos.service.IUserService;





public class App {
	@Test
	public void save() throws Exception {
		
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//IRolesService service=(IRolesService) ctx.getBean("rolesServiceImpl");
		
		//Roles roles = new Roles();
		//roles.setRname("��ʦ");
		//service.save(roles);
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//IQuestionService service=(IQuestionService) ctx.getBean("questionServiceImpl");
		
		//Question question = new Question();
		//question.setContent("�Ͽ��Ƿ�����Ϸ��");
		//service.save(question);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		IPaperService service=(IPaperService) ctx.getBean("paperServiceImpl");
		
		Paper paper = new Paper();
		paper.setTitle("����һ�׸�");
		
		service.save(paper);
		
	}
	
}