package com.swarmhr.gateway.zuulfilter;

import com.netflix.zuul.ZuulFilter;

public class SwarmHRPostFilter extends ZuulFilter {

	@Override
	  public String filterType() {
	    return "post";
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
		System.out.println("Inside Response Filter");
	    return null;
	  }
	
}
