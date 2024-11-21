package com.swarmhr.gateway.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swarmhr.gateway.entity.SwarmHRJwtToken;

import io.jsonwebtoken.JwtException;

public class SwarmHRJwtTokenFilter extends GenericFilterBean{

	private SwarmHRJwtTokenProvider jwtTokenProvider;

    public SwarmHRJwtTokenFilter(SwarmHRJwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
	
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		 HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        String token = jwtTokenProvider.resolveToken(request);
	        SwarmHRJwtToken jwtToken = null;
	        if (token != null) {
	            try {
	                jwtToken = jwtTokenProvider.validateToken(token) ;
	                Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token, jwtToken.getCreationDate()) : null;
		            SecurityContextHolder.getContext().setAuthentication(auth);
		            
	            } catch (JwtException | IllegalArgumentException e) {
	            	ObjectMapper om = new ObjectMapper();
	            	String jsonError = "{\"Message\" : \" Invalid JWT Token\"}"; 
	            	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            	response.getWriter().write(jsonError);
	            	return;
	                //response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid JWT token");
	            }
	            if(jwtToken != null) {
	            	List<String> roleList = jwtTokenProvider.getRoleList(jwtToken.getToken(), jwtToken.getCreationDate());
	            	String roles = roleList.stream().map(Object::toString).collect(Collectors.joining(","));
	            	String userName = jwtTokenProvider.getUsername(jwtToken.getToken(), jwtToken.getCreationDate());
	            	String userInfo = jwtTokenProvider.getUserInfo(token); 
	            	request.setAttribute("USER_INFO", userInfo);
	            	request.setAttribute("USER_NAME", userName);
	            	request.setAttribute("USER_ROLES", roles);
	            }
	        }
	        filterChain.doFilter(req, res);
		
	}

}
