package com.crm.demo.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/***
 * 
 * @author jacy
 *
 */
@Service
public class userFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException 
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
//		获取请求地址
		String uri = request.getRequestURI();
		if(checkResource(uri)){
			chain.doFilter(request, response);
			return;
		}
//		登录界面不拦截
		if(uri.endsWith("/index")){
			chain.doFilter(request, response);
			return;
		}
//		接口不拦截
		if(uri.indexOf("/api/")!=-1){
			chain.doFilter(request, response);
			return;
		}

		//从session中获取user
		HttpSession session = request.getSession();
		String userKey="user";
		Integer userId= (Integer)session.getAttribute(userKey);
		if(userId==null){
			session.removeAttribute(userKey);
			response.sendRedirect("/index");
			return;
		}
		chain.doFilter(request, response);
	}
	
	
	/**
	 * 检查是否资源文件进入拦截器，拦截器放过资源文件
	 * @param url 请求资源url
	 * @return
	 */
	private boolean checkResource(String url){
		String[] strRexArray={".ico",".jpg",".jpeg",".png",".css",".js",".font"};
		for(String reg : strRexArray)
		if(url.endsWith(reg))
		{
			return true;
		}
		return false;
	}

	
	@Override
	public void destroy() {
	}

}
