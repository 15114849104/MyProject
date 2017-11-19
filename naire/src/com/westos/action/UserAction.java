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
		//easyUi会自动传过来两个值，分别是page和rows，代表当前页和每页显示行数
		//List<User> list = service.find();
		Page pp = service.findPageData(page, rows);
		
		JsonConfig jc = new JsonConfig();
		//把list打成json,在变成String类型
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
		//u是表单传过来的    u1是查询的库里面的
		//这里这样写是因为       一个用户可以有多张问卷  表单里面传的只有 1.账户 
		//2.密码
		//3.真实姓名
		//4.角色  不会传过来paper的ID，所以  当修改后因为没传那张paper，默认就为null,
		//当保存后，就会有一个null，造成bug
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
			//登录成功时，必须把登录用户信息存入Session中
				session.setAttribute("user", user);
				
				String roles=user.getRoles().getRname();
				String roles1="学生";
				if(roles.equals(roles1)){
					List<Paper> list = pService.find();
					List list1 = new ArrayList();
					for(int i=0;i<list.size();i++){
						Paper p=list.get(i);
						if(p.getStatus()==1){
							list1.add(p);
						}
					}
					//这里是同步请求，页面请求数据，所以这里先把数据放到request中，页面要的时候直接请求
					request.setAttribute("list1", list1);
					//这里带数据去xianshi页面上，所以要用转发
					path="front/xianshi";
				}else{
					String roles2=user.getRoles().getRname();
					String roles3="校长";
					if(roles2.equals(roles3)){
						path="redirect:/admin/home.jsp";
					}else{
					//走到这里说明会是助教，班主任，教师
					//准备好与之有关的所有问卷
					List list = new ArrayList(user.getPapers());
					request.setAttribute("list", list);
					
					path="front/xianshiUserPaper";
				}
			}
		}
		return path;
	}
}

