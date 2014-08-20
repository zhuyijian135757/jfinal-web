package com.demo.admin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.blog.Blog;
import com.jfinal.core.Controller;


public class AdminFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest r, ServletResponse re,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest  req=(HttpServletRequest)r;
		HttpServletResponse  resp=(HttpServletResponse)re;
		Object name=req.getSession().getAttribute("name");
		
		String url=req.getRequestURL().toString();
		if(!url.contains("admin/login") && (name==null || name.toString().equals(""))){
			r.getRequestDispatcher("/admin/login.html");
		}
		chain.doFilter(r, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	
	
}
