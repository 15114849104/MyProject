package com.westos.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.westos.domain.Page;
import com.westos.domain.Question;
import com.westos.domain.Roles;
import com.westos.service.IRolesService;

@Controller
@RequestMapping("/roles")
public class RolesAction {
	@Autowired
	private IRolesService service;

	@RequestMapping("/save")
	public void save(Roles r,HttpServletResponse response) throws Exception{
		service.save(r);
		response.getWriter().write("{}");
	}
	@RequestMapping("/find")
	public void find(int page,int rows,HttpServletResponse response) throws Exception {
		//easyUI���Զ��Ĵ�һ��page��rows��������ǵڼ�ҳ��Ҳ���ǵ�ǰҳ������ÿҳ��ʾ����
		Page pp = service.findPageData(page, rows);
		//List<Roles> list = service.find();
		JsonConfig jc = new JsonConfig();
		jc.setExcludes(new String[]{"roles","papers","scores","users"});
		//�Ȱ�list���JSON��Ȼ��ת���ַ�����ʽ
		
		
       String json = JSONArray.fromObject(pp.getList(),jc).toString();
       //����������json���洫��������
       json="{\"total\":\""+pp.getRowCount()+"\",\"rows\":"+json+"}";
       
       response.setCharacterEncoding("utf-8");
       response.getWriter().write(json);
	}
	
	@RequestMapping("/findAll")
    public void findAll(HttpServletResponse response) throws Exception{
		
		List<Roles> list = service.find();
		JsonConfig jc=new JsonConfig();
		jc.setExcludes(new String[]{"scores","papers","users","roles"});
		String json = JSONArray.fromObject(list,jc).toString();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json);
	}
	@RequestMapping("/delete")
	public void delete(int rid,HttpServletResponse response) throws Exception{
		
		service.delete(rid);
		response.getWriter().write("{}");
	}
	@RequestMapping("/update")
	public void update(Roles r,HttpServletResponse response) throws Exception{
		
		service.update(r);
		response.getWriter().write("{}");
	}
}
