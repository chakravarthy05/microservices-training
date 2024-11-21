package com.swarmhr.gateway.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swarmhr.gateway.entity.SwarmHRJwtToken;
import com.swarmhr.gateway.entity.SwarmHRUserInfo;
import com.swarmhr.gateway.repository.SwarmHrUserInfoRepository;
import com.swarmhr.gateway.service.SwarmHRLoginService;
import com.swarmhr.gateway.repository.SwarmHRJwtTokenRepository;
import com.swarmhr.gateway.vo.SwarmHRUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class SwarmHRJwtTokenProvider {

	private static final String AUTH = "auth";
	private static final String AUTHORIZATION = "Authorization";

	@Value("${secret-key:swarm-hr-2020@rthumu}")
	private String secretKey = "secret-key";
	private long validityInMilliseconds = 43200000; // 12h

	@Autowired
	private SwarmHRJwtTokenRepository jwtTokenRepository;

	@Autowired
	private SwarmHrUserInfoRepository swarmHrUserInfoRepository;

//    @Autowired
//    private SwarmHRUserService userDetailsService;
//    
	private static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

	private static Logger logger = LoggerFactory.getLogger(SwarmHRJwtTokenProvider.class);

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(String username, List<String> roles) {

		try {
			Claims claims = Jwts.claims().setSubject(username);
			claims.put(AUTH, roles);

			Date now = new Date();
			Date validity = new Date(now.getTime() + validityInMilliseconds);

			LocalDateTime currentDateTime = LocalDateTime.now();

			String dateTime = currentDateTime.format(formatter);

			logger.info("Trying to create Token ");

			String token = Jwts.builder()//
					.setClaims(claims)//
					.setIssuedAt(now)//
					.setExpiration(validity)//
					.signWith(SignatureAlgorithm.HS512, secretKey + dateTime) // Use a custom current time to harden the
																				// secret key or salt key to detect
					.compact();

			logger.info("Token created: " + token);

			SwarmHRUserInfo userInfo = swarmHrUserInfoRepository.getSwarmHrUserInfo(username);

			String base64encodedUserInfo;
			try {
				base64encodedUserInfo = Base64.getEncoder().encodeToString(convertToJSON(userInfo).getBytes("utf-8"));
				logger.info("COnverted userinfo to Base64");
			} catch (UnsupportedEncodingException e) {
				base64encodedUserInfo = "";
				logger.error("Base 64 Encode issue");
				e.printStackTrace();
			}
			
			jwtTokenRepository.save(new SwarmHRJwtToken(token, dateTime, base64encodedUserInfo, username));
			logger.info("JWT Token saved successfully");
			
			return token;
		} catch (Exception e) {
			logger.error("General Exception while creating token");
			e.printStackTrace();
			return "";
		}
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader(AUTHORIZATION);
		/*
		 * if (bearerToken != null && bearerToken.startsWith("Bearer ")) { return
		 * bearerToken.substring(7, bearerToken.length()); }
		 */
		if (bearerToken != null) {
			return bearerToken;
		}
		return null;
	}

	// Validate token for each call....
	public SwarmHRJwtToken validateToken(String token) throws JwtException, IllegalArgumentException {
		SwarmHRJwtToken jwtToken = jwtTokenRepository.findByToken(token);
		if (jwtToken == null || StringUtils.isEmpty(jwtToken.getToken())) {
			throw new JwtException("Token not valid !!!");
		}
		// Use a custom current time to harden the secret key or salt key to detect
		Jwts.parser().setSigningKey(secretKey + jwtToken.getCreationDate()).parseClaimsJws(token);
		return jwtToken;
	}

	// Check if the token present in DB
//    public boolean isTokenPresentInDB (String token) {
//        SwarmHRJwtToken jwtToken =  jwtTokenRepository.findByToken(token);
//        if(jwtToken != null && jwtToken.getToken() != null &&  jwtToken.getToken().length() > 0)
//        	return true;
//        return false;
//        		
//    }
	// user details with out database hit
	public UserDetails getUserDetails(String token, String dateTime) {
		String userName = getUsername(token, dateTime);
		List<String> roleList = getRoleList(token, dateTime);
		UserDetails userDetails = new SwarmHRUserDetails(userName, roleList.toArray(new String[roleList.size()]));
		return userDetails;
	}

	public List<String> getRoleList(String token, String dateTime) {
		return (List<String>) Jwts.parser().setSigningKey(secretKey + dateTime).parseClaimsJws(token).getBody()
				.get(AUTH);
	}

	public String getUsername(String token, String dateTime) {
		return Jwts.parser().setSigningKey(secretKey + dateTime).parseClaimsJws(token).getBody().getSubject();
	}

	public String getUserInfo(String token) {
		return jwtTokenRepository.findByToken(token).getUserInfoJson();
	}

	public Authentication getAuthentication(String token, String dateTime) {
		// using data base: uncomment when you want to fetch data from data base
		// UserDetails userDetails =
		// userDetailsService.loadUserByUsername(getUsername(token));
		// from token take user value. comment below line for changing it taking from
		// data base
		UserDetails userDetails = getUserDetails(token, dateTime);
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private String convertToJSON(SwarmHRUserInfo swarmHRUserInfo) {
		ObjectMapper Obj = new ObjectMapper();
		try {
			String jsonStr = Obj.writeValueAsString(swarmHRUserInfo);
			return jsonStr;
		} catch (IOException e) {
			return "";
		}
	}

}
