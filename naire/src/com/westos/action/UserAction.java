package com.westos.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.westos.domain.Page;
import com.westos.domain.Paper;
import com.westos.domain.Roles;
import com.westos.domain.User;
import com.westos.service.IPaperService;
import com.westos.service.IUserService;


@Controller
@RequestMapping("/user")
public class UserAction {
	@Autowired
	private IUserService service;
	
	@Autowired
	private IPaperService pService;
	
	@RequestMapping("/find")
	public void find(int page,int rows,HttpServletResponse response) throws Exception{
		//easyUi���Զ�����������ֵ���ֱ���page��rows������ǰҳ��ÿҳ��ʾ����
		//List<User> list = service.find();
		Page pp = service.findPageData(page, rows);
		
		JsonConfig jc = new JsonConfig();
		//��list���json,�ڱ��String����
		jc.setExcludes(new String[]{"scores","papers","users","questions"});
		String json = JSONArray.fromObject(pp.getList(),jc).toString();
		
		json="{\"total\":\""+pp.getRowCount()+"\",\"rows\":"+json+"}";
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json);
	}
	@RequestMapping("/findAll")
	public void findAll(HttpServletResponse response) throws Exception{
		List<User> list =service.find();
		JsonConfig jc = new JsonConfig();
		jc.setExcludes(new String[]{"scores","papers","users","questions"});
		
		String json = JSONArray.fromObject(list, jc).toString();
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json);
	}
	@RequestMapping("/save")
	public void save(User u,HttpServletResponse response) throws Exception{
		
		service.save(u);
		response.getWriter().write("{}");
		
	}
	@RequestMapping("/delete")
	public void delete(int uid,HttpServletResponse response) throws Exception{
		service.delete(uid);
		response.getWriter().write("{}");
	}
	@RequestMapping("/update")
	public void update(User u,HttpServletResponse response) throws Exception{
		//u�Ǳ���������    u1�ǲ�ѯ�Ŀ������
		//��������д����Ϊ       һ���û������ж����ʾ�  �����洫��ֻ�� 1.�˻� 
		//2.����
		//3.��ʵ����
		//4.��ɫ  ���ᴫ����paper��ID������  ���޸ĺ���Ϊû������paper��Ĭ�Ͼ�Ϊnull,
		//������󣬾ͻ���һ��null�����bug
		User u1 = service.find(u.getUid());
		u1.setAccount(u.getAccount());
		u1.setPassword(u.getPassword());
		u1.setRealname(u.getRealname());
		u1.setRoles(u.getRoles());
		service.update(u1);
		
		response.getWriter().write("{}");
		
	}
	@RequestMapping("/login")
	public String login(String account,String password,HttpSession session,HttpServletRequest request){
		User user = service.find(account, password);
		String path = "";
		if(user == null){
			path="redirect:/front/index.jsp";
		}else{
			//��¼�ɹ�ʱ������ѵ�¼�û���Ϣ����Session��
				session.setAttribute("user", user);
				
				String roles=user.getRoles().getRname();
				String roles1="ѧ��";
				if(roles.equals(roles1)){
					List<Paper> list = pService.find();
					List list1 = new ArrayList();
					for(int i=0;i<list.size();i++){
						Paper p=list.get(i);
						if(p.getStatus()==1){
							list1.add(p);
						}
					}
					//������ͬ������ҳ���������ݣ����������Ȱ����ݷŵ�request�У�ҳ��Ҫ��ʱ��ֱ������
					request.setAttribute("list1", list1);
					//���������ȥxianshiҳ���ϣ�����Ҫ��ת��
					path="front/xianshi";
				}else{
					String roles2=user.getRoles().getRname();
					String roles3="У��";
					if(roles2.equals(roles3)){
						path="redirect:/admin/home.jsp";
					}else{
					//�ߵ�����˵���������̣������Σ���ʦ
					//׼������֮�йص������ʾ�
					List list = new ArrayList(user.getPapers());
					request.setAttribute("list", list);
					
					path="front/xianshiUserPaper";
				}
			}
		}
		return path;
	}
}

