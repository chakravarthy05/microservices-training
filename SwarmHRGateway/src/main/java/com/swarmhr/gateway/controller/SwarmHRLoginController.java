package com.swarmhr.gateway.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swarmhr.gateway.exception.SwarmHRGatewayException;
import com.swarmhr.gateway.service.ISwarmHRLoginService;
import com.swarmhr.gateway.vo.OTPResponse;
import com.swarmhr.gateway.vo.SwarmHRAuthResponse;
import com.swarmhr.gateway.vo.SwarmHRLoginRequest;
import com.swarmhr.gateway.vo.SwarmHRLogoutRequest;
import com.swarmhr.gateway.vo.SwarmHRUserVo;

@CrossOrigin("*")
@RestController
public class SwarmHRLoginController {

	@Autowired
	private ISwarmHRLoginService iLoginService;

	@PostMapping("/api/auth/signin")
	@ResponseBody
	public ResponseEntity<SwarmHRAuthResponse> login(HttpServletRequest request,@RequestBody SwarmHRLoginRequest loginRequest)
			throws SwarmHRGatewayException {
		//System.out.println("Login Request => ["+loginRequest.getUsername()+"]===========["+loginRequest.getPassword()+"]");
		SwarmHRAuthResponse response = iLoginService.login(loginRequest);
		HttpHeaders headers = new HttpHeaders();

		if(response == null)
			return new ResponseEntity("Invalid Username or Password", headers, HttpStatus.BAD_REQUEST);
		iLoginService.saveLoginDetails(loginRequest.getUsername(), request.getRemoteAddr());
		List<String> headerlist = new ArrayList<>();
		
		headerlist.add("Content-Type");
		headerlist.add("Accept");
		headerlist.add("X-Requested-With");
		headerlist.add("Authorization");
		headerlist.add("x-xsrf-token");
		headerlist.add("Origin");
		headerlist.add("SWARMHRAPP");
		headerlist.add("SWARMHRUSERNAME");
		headerlist.add("ATSTOKEN");
		headerlist.add("SWARMRH_ATS_TOKEN");
		
		headers.setAccessControlAllowHeaders(headerlist);
		
		List<String> exposeList = new ArrayList<>();
		exposeList.add("Authorization");
		exposeList.add("Content-Type");
		exposeList.add("Accept");
		exposeList.add("X-Requested-With");
		exposeList.add("Authorization");
		exposeList.add("x-xsrf-token");
		exposeList.add("Origin");
		exposeList.add("SWARMHRAPP");
		exposeList.add("SWARMHRUSERNAME");
		exposeList.add("ATSTOKEN");
		exposeList.add("SWARMRH_ATS_TOKEN");
		
		
		
		headers.setAccessControlExposeHeaders(exposeList);
		
		
		// Add access control allow origin
		//headers.setAccessControlAllowOrigin("*");
		
		// Add Http Methods....
		List<HttpMethod> httpMethods = new ArrayList<>();
		httpMethods.add(HttpMethod.GET);httpMethods.add(HttpMethod.POST);httpMethods.add(HttpMethod.DELETE);httpMethods.add(HttpMethod.OPTIONS);
		httpMethods.add(HttpMethod.PUT);httpMethods.add(HttpMethod.PATCH);httpMethods.add(HttpMethod.HEAD);httpMethods.add(HttpMethod.TRACE);
		headers.setAccessControlAllowMethods(httpMethods);
		
		headers.setAccessControlMaxAge(3600);
		
		headers.set("Authorization", response.getAccessToken());
		
		return new ResponseEntity<SwarmHRAuthResponse>(response, headers, HttpStatus.CREATED);
	}

	@PostMapping("/api/auth/signout")
	@ResponseBody
	public ResponseEntity<SwarmHRAuthResponse> logout(@RequestHeader(value = "Authorization") String token, @RequestBody SwarmHRLogoutRequest logoutReq)
			throws SwarmHRGatewayException {
		HttpHeaders headers = new HttpHeaders();
		if (iLoginService.logout(token, logoutReq)) {
			headers.remove("Authorization");
			return new ResponseEntity<SwarmHRAuthResponse>(new SwarmHRAuthResponse("logged out"), headers,
					HttpStatus.CREATED);
		}
		return new ResponseEntity<SwarmHRAuthResponse>(new SwarmHRAuthResponse("Logout Failed"), headers,
				HttpStatus.NOT_MODIFIED);
	}

	@PostMapping("/api/auth/valid-token")
	@ResponseBody
	public Boolean isValidToken(@RequestHeader(value = "Authorization") String token) throws SwarmHRGatewayException {
		return iLoginService.isValidToken(token);
	}

	@PostMapping("/api/auth/token")
	@ResponseBody
	public ResponseEntity<SwarmHRAuthResponse> createNewToken(@RequestHeader(value = "Authorization") String token)
			throws SwarmHRGatewayException {
		String newToken = iLoginService.createNewToken(token);
		HttpHeaders headers = new HttpHeaders();
		List<String> headerList = new ArrayList<>();
		List<String> exposeList = new ArrayList<>();
		headerList.add("Content-Type");
		headerList.add(" Accept");
		headerList.add("X-Requested-With");
		headerList.add("Authorization");
		headers.setAccessControlAllowHeaders(headerList);
		exposeList.add("Authorization");
		headers.setAccessControlExposeHeaders(exposeList);
		headers.set("Authorization", newToken);
		return new ResponseEntity<SwarmHRAuthResponse>(new SwarmHRAuthResponse(newToken), headers, HttpStatus.CREATED);
	}
	
	@PostMapping("/api/auth/user-info")
	@ResponseBody
	public ResponseEntity<SwarmHRUserVo> getUserDetails(@RequestHeader(value = "Authorization") String token,
			@RequestParam(value = "view", required = false) String view){ 
		if(view==null || view.equalsIgnoreCase(""))
			view = "Web";
		SwarmHRUserVo vo = iLoginService.getUserInfo(token, view);
		return new ResponseEntity<SwarmHRUserVo>(vo, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/auth/validateOTP")
	@ResponseBody
	public ResponseEntity<OTPResponse> validateOTP(@RequestParam("otp") int otp,
			@RequestParam("username") String username) throws SwarmHRGatewayException {
		OTPResponse reponse = new OTPResponse();
		reponse.setUsername(username);
		if (iLoginService.validateOTP(otp, username))
			reponse.setStatus("Valid OTP");
		else
			reponse.setStatus("In-Valid OTP");
		return new ResponseEntity<OTPResponse>(reponse, HttpStatus.ACCEPTED);
	}
}
