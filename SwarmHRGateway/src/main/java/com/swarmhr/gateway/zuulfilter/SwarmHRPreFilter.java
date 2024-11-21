package com.swarmhr.gateway.zuulfilter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class SwarmHRPreFilter extends ZuulFilter {

	@Override
	  public String filterType() {
	    return "pre";
	  }
	 
	  @Override
	  public int filterOrder() {
	    return 1;
	  }
	 
	  @Override
	  public boolean shouldFilter() {
	    return true;
	  }
	 
	  @Override
	  public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		System.out.println(" User Name -> "+request.getAttribute("USER_NAME"));
		System.out.println(" User Roles -> "+request.getAttribute("USER_ROLES"));
		System.out.println(" User Info -> "+request.getAttribute("USER_INFO"));
		System.out.println("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
		ctx.addZuulRequestHeader("USER_NAME", (String)request.getAttribute("USER_NAME"));
		ctx.addZuulRequestHeader("USER_ROLES", (String)request.getAttribute("USER_ROLES"));
		ctx.addZuulRequestHeader("USER_INFO", (String)request.getAttribute("USER_INFO"));
		return null;
	  }
	
}
