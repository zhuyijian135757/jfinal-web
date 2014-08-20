package com.demo.common;

import com.demo.blog.Blog;
import com.jfinal.core.Controller;

/**
 * CommonController
 */
public class CommonController extends Controller {
	
	public void index() {
		
		initHead();
		render("/common/index.html");
	}
	
	
	public void initHead(){
		setAttr("java", Blog.dao.paginate(1, 10,"java"));
		setAttr("socket", Blog.dao.paginate(1, 10,"网络编程"));
		setAttr("web", Blog.dao.paginate(1,10, "web应用"));
		setAttr("linux", Blog.dao.paginate(1, 10,"linux"));
		setAttr("mine", Blog.dao.paginate(1, 10,"原创"));
	}
}
