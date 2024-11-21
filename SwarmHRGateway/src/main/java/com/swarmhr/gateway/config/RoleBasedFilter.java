//package com.swarmhr.gateway.config;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.Date;
//import java.util.List;
//import java.util.Base64.Decoder;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//@Component
//@Order(1)
//public class RoleBasedFilter implements Filter {
//
//	private static Logger log = LoggerFactory.getLogger(RoleBasedFilter.class);
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletResponse res = (HttpServletResponse) response;
//		HttpServletRequest req = (HttpServletRequest) request;
//		String path = req.getRequestURI();
//		if (!path.contains("auth")) {
//			try {
//				String roles = req.getAttribute("USER_ROLES").toString();
//				if (path.contains("/superAdmin/getSuperAdminPlanList")) {
//					if (!roles.contains("ROLE_SUPERADMIN")) {
//						res.sendError(401, "Not Authorized");
//						return;
//					}
//				}
//			} catch (Exception e) {
//				res.sendError(401, "Not Authorized");
//				return;
//			}
//		}
//		chain.doFilter(request, response);
//
//	}
//
//}
