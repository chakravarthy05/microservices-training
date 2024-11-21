package com.swarmhr.gateway.service; 

import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.swarmhr.gateway.entity.OffboardConfiguration;
import com.swarmhr.gateway.entity.OrganizationProperties;
import com.swarmhr.gateway.entity.Reports;
import com.swarmhr.gateway.entity.SpecialAccessGroup;
import com.swarmhr.gateway.entity.SwarmHRJwtToken;
import com.swarmhr.gateway.entity.SwarmHRRole;
import com.swarmhr.gateway.entity.SwarmHRUser;
import com.swarmhr.gateway.entity.UserSettings;
import com.swarmhr.gateway.entity.WebLogin;
import com.swarmhr.gateway.exception.SwarmHRGatewayException;
import com.swarmhr.gateway.repository.OffboardConfigurationRepository;
import com.swarmhr.gateway.repository.OrganizationPropertyRepository;
import com.swarmhr.gateway.repository.ReportsRepository;
import com.swarmhr.gateway.repository.SwarmHRChannelPartnerRepository;
import com.swarmhr.gateway.repository.SwarmHRJwtTokenRepository;
import com.swarmhr.gateway.repository.SwarmHRUserRepository;
import com.swarmhr.gateway.repository.SwarmHrFeatureRepository;
import com.swarmhr.gateway.repository.SwarmHrUserInfoRepository;
import com.swarmhr.gateway.repository.UserSettingsRepository;
import com.swarmhr.gateway.repository.WebLoginRepository;
import com.swarmhr.gateway.security.SwarmHRJwtTokenProvider;
import com.swarmhr.gateway.util.DataStoreCommonUtility;
import com.swarmhr.gateway.vo.AccessGroup;
import com.swarmhr.gateway.vo.ReportPermission;
import com.swarmhr.gateway.vo.ReportVo;
import com.swarmhr.gateway.vo.ReportsConfigurationVO;
import com.swarmhr.gateway.vo.SwarmHRAuthResponse;
import com.swarmhr.gateway.vo.SwarmHREmail;
import com.swarmhr.gateway.vo.SwarmHRFeatureVo;
import com.swarmhr.gateway.vo.SwarmHRLoginRequest;
import com.swarmhr.gateway.vo.SwarmHRLogoutRequest;
import com.swarmhr.gateway.vo.SwarmHRUserVo;
import com.swarmhr.gateway.vo.SwarmHrTextMessage;
import com.swarmhr.gateway.vo.UserPropertiesVo;

import io.jsonwebtoken.JwtException;

@Transactional(readOnly = true)
@Service
public class SwarmHRLoginService implements ISwarmHRLoginService {

	private static Logger logger = LoggerFactory.getLogger(SwarmHRLoginService.class);

	private static String REPORTS_CONFIGURATION = "reports_configuration";
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SwarmHRJwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private SwarmHRUserRepository userRepository;

	@Autowired
	private SwarmHRJwtTokenRepository jwtTokenRepository;

	@Autowired
	private SwarmHrUserInfoRepository swarmHrUserInfoRepository;

	@Autowired
	private SwarmHRChannelPartnerRepository swarmHRChannelPartnerRepository;

	@Autowired
	private SwarmHrFeatureRepository swarmHrFeatureRepository;
	
	@Autowired
	OffboardConfigurationRepository offboardConfigurationRepository;

	@Autowired
	OrganizationPropertyRepository orgPropertyRepository;
	
	@Autowired
	UserSettingsRepository userSettingsRepository;
	
	@Autowired
	Environment env;
	
	@Autowired
	ReportsRepository reportsRepository;
	
	@Autowired
	WebLoginRepository webLoginRepository;
	
	@Autowired
	DataStoreCommonUtility dataStoreCommonUtility;
	
	@Autowired
	Datastore datastore;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	@Override
	public SwarmHRAuthResponse login(SwarmHRLoginRequest loginReq) {
		SwarmHRAuthResponse response = null;
		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
			SwarmHRUser user = userRepository.findByUserName(loginReq.getUsername());

			List<String> roles = swarmHrUserInfoRepository.getSwarmUserRoles(user.getUserName());
			List<String> list = new ArrayList<String>();
			list.addAll(roles);
  
			//Block is username case doesnot match
//			if(!loginReq.getUsername().equals(user.getUserName())) {
//				 response = new SwarmHRAuthResponse("Failed, Username is Case-Sensitive!");
//				 return response; 
//			}
			
			//ImmiUser & HR check check 
			if(list.contains("ROLE_IMMIHR") || list.contains("ROLE_IMMIUSER")) {
				List<SwarmHRFeatureVo> features = swarmHrFeatureRepository.getFeaturesList(user.getOrgID());
				boolean flag = false;
				for(SwarmHRFeatureVo f: features) {
					if(f.getFeatureName().equals("Immigration Portal"))
						flag = true;
				}
				if(!flag) {
					 response = new SwarmHRAuthResponse("Failed, Access has been Deactivated, Please contact your Attorney!");
					 return response; 
				}
			}
			if (!user.getUserName().equalsIgnoreCase("spadmin") && !list.contains("ROLE_CHPARTNER")) {
				String status = swarmHrUserInfoRepository.getOrganizationStatus(user.getOrgID());
				if (status.equalsIgnoreCase("Disable") || status.equalsIgnoreCase("Disabled"))
					return null;
				
				String accessType = swarmHrUserInfoRepository.getAccess(user.getUserName());

				if (accessType.equalsIgnoreCase("Mobile") || accessType.equalsIgnoreCase("None")) {
					 response = new SwarmHRAuthResponse("Failed, Login Access has been changed, please check with \"Hr or Admin\"");
					 return response; 
					 }
				 
				String status1 = swarmHrUserInfoRepository.getStatus(user.getUserName());
				
			 if (status1.equalsIgnoreCase("Disable") || status1.equalsIgnoreCase("Disabled")) {
				 response = new SwarmHRAuthResponse("Failed, User has been Deactivated, please contact HR!");
				 return response; 
				 }
				  
				status = swarmHrUserInfoRepository.getStatus(user.getUserName());
				if (status.equalsIgnoreCase("Disable") || status.equalsIgnoreCase("Disabled"))
					return null;

				if (!(accessType.equalsIgnoreCase("Both") || accessType.equalsIgnoreCase("Web")
						|| accessType.equalsIgnoreCase("All Access") || accessType.equalsIgnoreCase("Web Only")))
					return null;

			}

			if (user == null || user.getRoles() == null || user.getRoles().isEmpty()) {
				logger.error("Invalid username or password");
				// throw new SwarmHRGatewayException("Invalid username or password.",
				// HttpStatus.UNAUTHORIZED);
			}
			// NOTE: normally we dont need to add "ROLE_" prefix. Spring does automatically
			// for us.
			// Since we are using custom token using JWT we should add ROLE_ prefix
			 
			String token = jwtTokenProvider.createToken(user.getUserName(), user.getRoles().stream()
					.map((SwarmHRRole role) -> role.getRole()).filter(Objects::nonNull).collect(Collectors.toList()));
			response = new SwarmHRAuthResponse();

			logger.info("token generated: " + token);

			response = new SwarmHRAuthResponse(token);
			
			// Login Audit 
			saveLoginAudit(user.getUserName(), user.getOrgID(), "Login", loginReq.getIp(), loginReq.getDate(),
					loginReq.getTime(), loginReq.getTimezone());
			 
			
		} catch (AuthenticationException e) {
			logger.error("Invalid username or password");
			e.printStackTrace();
			// throw new SwarmHRGatewayException("Invalid username or password.",
			// HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			logger.error("General Exeption");
			e.printStackTrace();
		}
		return response;
	}

	private void saveLoginAudit(String username, String orgId, String type, String ip, Date date, String time, String timezone) {
		try { 

			WebLogin webLogin = new WebLogin();
			webLogin.setUsername(username); 
			webLogin.setDate(date);
			webLogin.setTime(time + "(" + timezone + ")");
			webLogin.setTimezone(timezone);
			if (null == ip) {
				webLogin.setIp("");
			} else if (ip.length() > 50) {
				webLogin.setIp("");
			} else
				webLogin.setIp(ip);
			webLogin.setType(type);
			webLogin.setApplication("SwarmHR");
			webLogin.setOrganizationId(orgId);
			webLoginRepository.save(webLogin);
		} catch (Exception e) {
			logger.error("General Exeption");
			e.printStackTrace();
		}
	}
	
	@Transactional
	@Override
	public SwarmHRUser saveUser(SwarmHRUser user) throws SwarmHRGatewayException {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userRepository.save(user);
		} catch (Exception e) {
			throw new SwarmHRGatewayException(e);
		}
	}

	@Transactional
	@Override
	public boolean logout(String token, SwarmHRLogoutRequest logoutReq) throws SwarmHRGatewayException {
		try {
			SwarmHRJwtToken jwtToken = jwtTokenRepository.findByToken(token);
			jwtTokenRepository.deleteById(jwtToken.getId());
		
 			String userName = jwtTokenProvider.getUsername(jwtToken.getToken(), jwtToken.getCreationDate());
			List<String> roles = swarmHrUserInfoRepository.getSwarmUserRoles(userName);
			List<String> list = new ArrayList<String>();
			list.addAll(roles);
			// logout Audit
			if (!userName.equalsIgnoreCase("spadmin") && !list.contains("ROLE_CHPARTNER")) {
				SwarmHRUserVo user = swarmHrUserInfoRepository.getSwarmHrUserVo(userName);
				saveLoginAudit(jwtToken.getUsername(), user.getOrgId(), "Logout", logoutReq.getIp(),
						logoutReq.getDate(), logoutReq.getTime(), logoutReq.getTimezone());
			} else {
				//SwarmHRUserVo user = swarmHrUserInfoRepository.getSwarmHrUserVo(userName);
				saveLoginAudit(userName, userName, "Logout", logoutReq.getIp(), logoutReq.getDate(),
						logoutReq.getTime(), logoutReq.getTimezone());
			}
			return true;
		} catch (Exception e) {
			throw new SwarmHRGatewayException(e);
		}
	}
	
	public boolean logout(String token ) throws SwarmHRGatewayException { 
		try {
			SwarmHRJwtToken jwtToken = jwtTokenRepository.findByToken(token);
			jwtTokenRepository.deleteById(jwtToken.getId());
			return true;
		} catch (Exception e) {
			throw new SwarmHRGatewayException(e);
		}
	}

	@Override
	public Boolean isValidToken(String token) throws SwarmHRGatewayException {
		try {
			SwarmHRJwtToken jwtToken = jwtTokenProvider.validateToken(token);
			return jwtToken != null && !StringUtils.isEmpty(jwtToken.getToken());
		} catch (Exception e) {
			throw new SwarmHRGatewayException(e);
		}
	}

	@Transactional
	@Override
	public String createNewToken(String token) throws SwarmHRGatewayException {
		try {
			SwarmHRJwtToken jwtToken = jwtTokenRepository.findByToken(token);
			String username = jwtTokenProvider.getUsername(token, jwtToken.getCreationDate());
			List<String> roleList = jwtTokenProvider.getRoleList(token, jwtToken.getCreationDate());
			String newToken = jwtTokenProvider.createToken(username, roleList);
			logout(token); // Delete the old token
			return newToken;
		} catch (Exception e) {
			throw new SwarmHRGatewayException(e);
		}
	}

	@Transactional
	@Override
	public SwarmHRUserVo getUserInfo(String token, String view) {
		String envUrl = env.getProperty("spring.profile");
		SwarmHRJwtToken jwtToken = null;
		SwarmHRUserVo user = new SwarmHRUserVo();
		if (token != null) {
			try {
				jwtToken = jwtTokenProvider.validateToken(token);
				Authentication auth = token != null
						? jwtTokenProvider.getAuthentication(token, jwtToken.getCreationDate())
						: null;
				SecurityContextHolder.getContext().setAuthentication(auth);

			} catch (JwtException | IllegalArgumentException e) {
				return user;
			}

			if (jwtToken != null) {
				String userName = jwtTokenProvider.getUsername(jwtToken.getToken(), jwtToken.getCreationDate());
				List<String> roleList = swarmHrUserInfoRepository.getSwarmUserRoles(userName);
				String[] userRoles = new String[roleList.size()];
				userRoles = roleList.toArray(userRoles);

				for (String r : userRoles) {
					if (r.equalsIgnoreCase("ROLE_SUPERADMIN")) {
						user.setSpAdminFlag(true);
						user.setChannelPartnerFlag(false);
						user.setFirstName("Super");
						user.setLastName("Admin");
						user.setUserName("spadmin");
						user.setRoles(userRoles);
						user.setEnv(envUrl);
						user.setOrgLogo(swarmHrUserInfoRepository.fetchOrgLogoPublicUrl("swarm", envUrl));
						return user;
					}

					if (r.equalsIgnoreCase("ROLE_CHPARTNER")) {
						user.setSpAdminFlag(false);
						user.setChannelPartnerFlag(true);
						user.setChannelPartner(swarmHRChannelPartnerRepository.findByUsername(userName));
						user.setUserName(userName);
						user.setRoles(userRoles);
						user.setEnv(envUrl);
						user.setOrgLogo(swarmHrUserInfoRepository.fetchOrgLogoPublicUrl("swarm", envUrl));
						return user;
					}
				}
				user = swarmHrUserInfoRepository.getSwarmHrUserVo(userName);
				user.setRoles(userRoles);
				user.setEnv(envUrl);
				user.setOrgLogo(swarmHrUserInfoRepository.fetchOrgLogoPublicUrl(user.getOrgId(), envUrl));
				if (null != user.getDeptId())
					user.setDeptLogo(swarmHrUserInfoRepository.fetchDeptLogoPublicUrl(user.getDeptId(), envUrl));

				List<SwarmHRFeatureVo> featureList = new ArrayList<SwarmHRFeatureVo>(
						swarmHrFeatureRepository.listUserFeatures(userName, userRoles));
				List<SwarmHRFeatureVo> finalFeaturesList = new ArrayList<SwarmHRFeatureVo>();
				for (String r : userRoles) {
					if (r.equalsIgnoreCase("ROLE_MANAGER")) {
						featureList.addAll(swarmHrFeatureRepository.getManagerFeatures(user.getOrgId(), userName));
						SwarmHRFeatureVo managerFeatures = swarmHrFeatureRepository.getManagerAccessDetails(userName,
								user.getOrgId());
						if (StringUtils.isEmpty(managerFeatures.getFeatureID())) {
							managerFeatures.setFeatureName(
									"Approve Cash Advance" + "," + "Approve Performance Evaluation" + ","
											+ "Approve Expenses" + "," + "Approve Leaves" + "," + "Approve Timesheets"
											+ "," + "Manager Assign Tasks" + "," + "Manager Chat" + "," + "My Team" + "," + "OffBoarding");
						}
						else {
							managerFeatures.setFeatureName(managerFeatures.getFeatureName());
						}
						List<SwarmHRFeatureVo> list = swarmHrFeatureRepository.getBasicManagerFeatures();
						for (SwarmHRFeatureVo li : list) {
							if (!(managerFeatures.getFeatureName().contains(li.getFeatureName()))) {
								finalFeaturesList.add(li);
							}
						}
						featureList.removeAll(finalFeaturesList);
					}
				}
				featureList = swarmHrFeatureRepository.listAllOrgFeatures(user.getOrgId());
				List<SwarmHRFeatureVo> listfil = featureList.stream().distinct().collect(Collectors.toList());
				
				Collections.sort(listfil, new Comparator<SwarmHRFeatureVo>() {
					@Override
					public int compare(SwarmHRFeatureVo a1, SwarmHRFeatureVo a2) {
						return a1.getFeatureName().compareTo(a2.getFeatureName());
					}
				}); 
				
				UserSettings setting = userSettingsRepository.findByUsernameAndPropertyType(userName, "mobileSetting"); 
				if (view.equalsIgnoreCase("Mobile")) {
					if (setting == null) {
						user.setFeatureList(listfil);
					} else {
						if (setting.getPropertyValue().equals("Disable"))
							user.setFeatureList(listfil);
						else
							user.setFeatureList(getMobileOnlyFetures(listfil));
					}
				} else {
					user.setFeatureList(listfil);
				}

			
				List<AccessGroup> accessGroup = new ArrayList<AccessGroup>();
				List<SpecialAccessGroup> specialGroup = swarmHrFeatureRepository.getSpecialGroups(user.getOrgId(), userName);
				if(specialGroup != null && specialGroup.size() > 0) {
					for(SpecialAccessGroup spl: specialGroup) {
						AccessGroup group = new AccessGroup();
						group.setGroupID(spl.getGroupID());
						group.setGroupName(spl.getGroupName());
						if(spl.getFeatures() != null) {
							List<String> myList = new ArrayList<String>(Arrays.asList(spl.getFeatures().split(",")));
							List<SwarmHRFeatureVo> features = swarmHrFeatureRepository.getSpecialFeatures(myList);
							group.setFeatureList(features);
						}
						accessGroup.add(group);
					}
				}
				user.setGroupList(accessGroup);
				List<String> list= new ArrayList<>(Arrays.asList(user.getRoles()));
				
				if(list.contains("ROLE_SUPERUSER")) {
				List<Reports> reportsList = reportsRepository.findAll();
				List<ReportsConfigurationVO> reportConfigList;
				try {
					reportConfigList = getReportsConfigList(user.getOrgId());
					List<Reports> availableReports = new ArrayList<Reports>();
					List<Reports> configuredReports = new ArrayList<Reports>();
					
					if(reportConfigList != null) {
						configuredReports = reportsList.stream()
						      .filter(rep -> reportConfigList.stream()
						        .anyMatch(repConfig -> 
						           
						        rep.getReportID().equals(repConfig.getReportID())))
						        .collect(Collectors.toList());
						for (Reports report : reportsList) {
							List<ReportsConfigurationVO>	 reportsConfigurations = reportConfigList.stream()
									      .filter(repConfig -> 
									      repConfig.getReportID().equals(report.getReportID()))
									        .collect(Collectors.toList());
								if (reportsConfigurations.size() == 0)
									availableReports.add(report);
						}
					} else {
						availableReports = reportsList;
					}
					Map<String,Object> map = new HashMap<String, Object>();
					Collections.sort(availableReports, new Comparator<Reports>() {
						@Override
						public int compare(Reports a1, Reports a2) {
							return a1.getReportName().compareTo(a2.getReportName());
						}
					});
					
					Collections.sort(configuredReports, new Comparator<Reports>() {
						@Override
						public int compare(Reports a1, Reports a2) {
							return a1.getReportName().compareTo(a2.getReportName());
						}
					});
					map.put("availableReports", availableReports);
					map.put("configuredReports", configuredReports);
					user.setReportList(map);
				} catch (SwarmHRGatewayException e) {
					e.printStackTrace();
				}
			}else {
				 Map<String,Object> map=new LinkedHashMap<>();
				 List<ReportVo> listReport = new ArrayList<>();
				List<ReportPermission> reportPermissions = getReportPermissionListforUser(user.getUserName(),
						user.getOrgId());
				StringBuffer reportIDs = new StringBuffer();
				for (ReportPermission r : reportPermissions) {
					if (r.getStatus().equals("Active"))
						reportIDs.append("'" + r.getReportID() + "',");
				}
				if (reportIDs.length() > 0) {
					reportIDs.setLength(reportIDs.length() - 1);
				listReport = listActiveReportsUser(reportIDs.toString());
				}
				map.put("configuredReports", listReport);
				user.setReportList(map);
			}
					
				List<OffboardConfiguration> offboardConfigList = offboardConfigurationRepository
						.findByOrganizationID(user.getOrgId());
				String usernames = "";
				if(offboardConfigList != null && offboardConfigList.size() > 0) {
					if( offboardConfigList.get(0).getUsernames() != null)
					   usernames = offboardConfigList.get(0).getUsernames();
				}
				user.setOffConfigUsers(usernames);
				
				String headerCountStatus = "Disable";
				OrganizationProperties property = orgPropertyRepository.findByOrganizationIDAndPropertyType(user.getOrgId(), "HeaderCount Status");
				if(property!=null && property.getPropertyValue()!=null && !property.getPropertyValue().equals("")) {
					headerCountStatus = property.getPropertyValue();
			     }
				user.setHeaderCountStatus(headerCountStatus);
				

				String orgAuth = swarmHrUserInfoRepository.getAuthentication(user.getOrgId());
				if (orgAuth != null && !orgAuth.isEmpty()) {
					if (orgAuth.equalsIgnoreCase("Disable"))
						user.setAuthentication("Disable");
				}

				SendOTPMessage(user, userName);
				// get default table entry
				Map<String, String> typeValueMap = new LinkedHashMap<String, String>();
				typeValueMap = listTypesAndValues(user.getOrgId());
				if (typeValueMap.size() == 0) {
					typeValueMap.put("Default Table Entries", "10");
				}
				for (Entry<String, String> element : typeValueMap.entrySet()) {
					if (element.getKey().equals("Default Table Entries")) {
						if(element.getValue() != null && (!element.getValue().equalsIgnoreCase(""))) {
						user.setDefaultTableEntry(element.getValue());
						}else {
							user.setDefaultTableEntry("20");
						}
					}
				}
			}
		}
		return user;
	}


	private List<SwarmHRFeatureVo> getMobileOnlyFetures(List<SwarmHRFeatureVo> featureList) {
		List<String> mobileFeatures = new ArrayList<>(Arrays.asList("My Info", "My Timesheets", "Claim Expenses", "Request Leave", "Payslips"));
		 List<SwarmHRFeatureVo> finalList = new ArrayList<SwarmHRFeatureVo>();
		 for(String mobile:mobileFeatures ) {
			 for(SwarmHRFeatureVo vo:featureList ) {
				 if(vo.getFeatureName().equals(mobile)) {
					 finalList.add(vo);
					 break;
				 }
			 }
		 }
		return finalList;
	}
	
	SwarmHRUserVo SendOTPMessage(SwarmHRUserVo user, String userName) {
		String orgAuth = swarmHrUserInfoRepository.getAuthentication(user.getOrgId());
		if (orgAuth != null && !orgAuth.isEmpty()) {
			String empAuth = user.getAuthentication();
			String pinValidation = user.getAuthenticationType();
			if (empAuth != null && !empAuth.isEmpty()) {
				if (pinValidation != null && !pinValidation.isEmpty()) {
					if (orgAuth.equals("Enable") && user.getAuthentication().equals("Enable")) {
						int countryCode;
						Random rand = new Random();
						String OTP = String.format("%06d", rand.nextInt(1000000));
						String textMessage = user.getFirstName() + " " + user.getLastName() + ","
								+ "\t\r\n Please use PIN: " + OTP + " to Login to SWARM HR Application";
//						String country = swarmHrUserInfoRepository.country(userName, user.getOrgId());
						String country = user.getCountry();
						if (country.equals("India")) {
							countryCode = 91;
						} else {
							countryCode = 1;
						}
						swarmHrUserInfoRepository.saveOtp(OTP, userName, user.getOrgId());

						if (user.getAuthenticationType().equalsIgnoreCase("Phone")) {
							SwarmHrTextMessage message = new SwarmHrTextMessage();
							message.setUsername(userName);
							message.setCountryCode(countryCode);
							message.setMobile(user.getMobile());
							message.setOrgID(user.getOrgId());
							message.setTextMessage(textMessage);
							user.setTextMessage(message);
						}
						if (user.getAuthenticationType().equalsIgnoreCase("Email")) {
							SwarmHREmail email = new SwarmHREmail();
							email.setUsername(userName);
							email.setTo(user.getEmail());
							email.setSubject("OTP Login for SWARM HR");
							email.setBody("Dear " + user.getLastName() + "," + user.getFirstName() + ","
									+ "\t\r\n Please use PIN: " + OTP + " to Login to SWARM HR Application.");
							String fromEmailID = swarmHrUserInfoRepository.getOrgEmail(user.getOrgId());
							String fromName = user.getLastName() + "," + user.getFirstName();
							try {
								fromEmailID = swarmHrUserInfoRepository
										.getCutomPropertyDetailsByName("Default-Email", user.getOrgId())
										.getPropertyValue().replace(",", "");
							} catch (Exception e) {
							}
							email.setFrom(fromEmailID);
							email.setName(fromName);
							user.setEmailMessage(email);
						}
					}
				}
			}
		}
		return user;
	}
	
	@Override
	public List<ReportPermission> getReportPermissionListforUser(String username, String orgID) {
		String gql = "SELECT * FROM report_permission WHERE username = @username";
		Query<Entity> query = Query.newGqlQueryBuilder(Query.ResultType.ENTITY, gql).setNamespace(orgID)
				.setBinding("username", username).build();
		QueryResults<Entity> results = datastore.run(query);
		List<ReportPermission> ReportPermissionList = new ArrayList<ReportPermission>();
		while (results.hasNext()) {
			Entity rs = results.next();
			ReportPermission permission = new ReportPermission();
			permission.setUsername(rs.getString("username"));
			permission.setFullname(rs.getString("fullname"));
			permission.setReportID(rs.getString("reportID"));
			permission.setStatus(rs.getString("status"));
			permission.setCreatedBy(rs.getString("createdBy"));
			permission.setCreatedTime(rs.getString("createdTime"));
			ReportPermissionList.add(permission);
		}
		return ReportPermissionList;
	}
	
	@Override
	public Map<String, String> listTypesAndValues(String organizationID) {
		String sql = "SELECT * FROM organization_properties where organizationID = '" + organizationID + "'";
		List<UserPropertiesVo> listUserProperties = jdbcTemplate.query(sql, new RowMapper<UserPropertiesVo>() {
			@Override
			public UserPropertiesVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserPropertiesVo UserProperties = new UserPropertiesVo();
				UserProperties.setOrganizationID(rs.getString("organizationID"));
				UserProperties.setPropertyType(rs.getString("propertyType"));
				UserProperties.setPropertyValue(rs.getString("propertyValue"));
				UserProperties.setCreatedBy(rs.getString("createdBy"));
				UserProperties.setCreatedDate(rs.getDate("createdDate"));
				UserProperties.setModifiedBy(rs.getString("modifiedBy"));
				UserProperties.setModifiedDate(rs.getDate("modifiedDate"));
				return UserProperties;
			}
		});
		Map<String, String> typeValueMap = new LinkedHashMap<String, String>();
		for (UserPropertiesVo user : listUserProperties) {
			typeValueMap.put(user.getPropertyType(), user.getPropertyValue().trim());
		}

		return typeValueMap;
	}
	
	@Override
	public List<ReportVo> listActiveReportsUser(String reportIDs) {
		try {
			String sql = "SELECT * FROM reports where status='Active' and reportID in (" + reportIDs + ")";
			
			List<ReportVo> featuresList = jdbcTemplate.query(sql, new RowMapper<ReportVo>() {
				@Override
				public ReportVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					ReportVo rep = new ReportVo();
					rep.setId(rs.getInt("id"));
					rep.setReportID(rs.getString("reportID"));
					rep.setReportName(rs.getString("reportName"));
					rep.setIconClass(rs.getString("iconClass"));
					rep.setUrl(rs.getString("url"));
					rep.setStatus(rs.getString("status"));
					return rep;
				}
			});
			return featuresList;
		} catch (Exception e) {
		
			return null;
		}
	}
	
	@Override
	public List<ReportsConfigurationVO> getReportsConfigList(String orgId) throws SwarmHRGatewayException {
		
		List<ReportsConfigurationVO> listReportsConfig = new ArrayList<ReportsConfigurationVO>();
		try {
			QueryResults<Entity> results = dataStoreCommonUtility.getDatastoreQueryResultsWithOneParam(REPORTS_CONFIGURATION, orgId, "status", "Active");
			while (results.hasNext()) {
				Entity rs = results.next();
				ReportsConfigurationVO vo = setReportsConfigData(rs);
				listReportsConfig.add(vo);
			}
		} catch (Exception e) {
			return null;
		}
		return listReportsConfig;
	}
	private ReportsConfigurationVO setReportsConfigData(Entity rs) {
		ReportsConfigurationVO vo = new ReportsConfigurationVO();
		vo.setReportID(rs.getString("reportID"));
		return vo;
	}
	@Override
	public boolean validateOTP(int otp, String username) throws SwarmHRGatewayException {
		try {
			return swarmHrUserInfoRepository.validateOTP(username, otp);
		} catch (Exception e) {
			throw new SwarmHRGatewayException(e);
		}
	}

	@Transactional
	@Override
	public boolean saveLoginDetails(String username, String IdAddress) throws SwarmHRGatewayException {
		try {
			SwarmHRUserVo user = swarmHrUserInfoRepository.getSwarmHrUserVo(username);
			if (user != null) {
				swarmHrUserInfoRepository.createLoginObject(user, IdAddress);
			}
			return true;
		} catch (Exception e) {
			throw new SwarmHRGatewayException(e);
		}
	}

}
