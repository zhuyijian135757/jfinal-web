package com.demo.admin;

import javax.servlet.http.HttpSession;

import com.demo.blog.Blog;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * CommonController
 */
public class AdminController extends Controller {
	
	public void index() {
		render("/admin/login.html");
	}
	
	public void login(){
		String name=getPara("name");
		String pwd=getPara("pwd");
		HttpSession session=getSession();
		session.setAttribute("name",name);
		
		Page<Blog> blog=Blog.dao.paginate(1, 1000,"java");
		setAttr("blog",blog);
		
		render("/admin/admin.html");
	}
	
}
