package com.swarmhr.gateway.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swarmhr.gateway.entity.SwarmHRRole;
import com.swarmhr.gateway.entity.SwarmHRUser;
import com.swarmhr.gateway.repository.SwarmHRUserRepository;
import com.swarmhr.gateway.repository.SwarmHrUserInfoRepository;
import com.swarmhr.gateway.vo.SwarmHRUserDetails;

@Service
public class SwarmHRUserService implements UserDetailsService{
	
	@Autowired
	SwarmHRUserRepository userRepository;
	
	@Autowired
	SwarmHrUserInfoRepository swarmHrUserInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 SwarmHRUser user = userRepository.findByUserName(username);
		 
		 user.setRoles(getTestRoles(username));
	        if (user == null || user.getRoles() == null || user.getRoles().isEmpty()) {
	            throw new UsernameNotFoundException("Invalid username or password."+ HttpStatus.UNAUTHORIZED);  // Changed it to standard exception
	        }
	        String [] authorities = new String[user.getRoles().size()];
	        int count=0;
	        for (SwarmHRRole role : user.getRoles()) {
	            //NOTE: normally we dont need to add "ROLE_" prefix. Spring does automatically for us.
	            //Since we are using custom token using JWT we should add ROLE_ prefix
	            authorities[count] = role.getRole();
	            count++;
	        }
	        SwarmHRUserDetails userDetails = new SwarmHRUserDetails(user.getUserName(),user.getPassword(),1,false,false,true,authorities);  
	        return userDetails;
	}
	
	private Set<SwarmHRRole> getTestRoles(String username) {
		List<String> roleList = swarmHrUserInfoRepository.getSwarmUserRoles(username);
		Set<SwarmHRRole> set = new HashSet<>();
		SwarmHRRole swarmHRRole = new SwarmHRRole();
		swarmHRRole.setRole(roleList.toString());
		set.add(swarmHRRole);
		return set;
	}

}
