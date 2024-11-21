package com.swarmhr.gateway.service;

import java.util.List;
import java.util.Map;

import com.swarmhr.gateway.entity.SwarmHRUser;
import com.swarmhr.gateway.exception.SwarmHRGatewayException;
import com.swarmhr.gateway.vo.ReportPermission;
import com.swarmhr.gateway.vo.ReportVo;
import com.swarmhr.gateway.vo.ReportsConfigurationVO;
import com.swarmhr.gateway.vo.SwarmHRAuthResponse;
import com.swarmhr.gateway.vo.SwarmHRLoginRequest;
import com.swarmhr.gateway.vo.SwarmHRLogoutRequest;
import com.swarmhr.gateway.vo.SwarmHRUserVo;

public interface ISwarmHRLoginService {
	
	public SwarmHRAuthResponse login(SwarmHRLoginRequest loginReq);

	public SwarmHRUser saveUser(SwarmHRUser user) throws SwarmHRGatewayException;

	public boolean logout(String token, SwarmHRLogoutRequest logoutReq) throws SwarmHRGatewayException;

	public Boolean isValidToken(String token) throws SwarmHRGatewayException;

	public String createNewToken(String token) throws SwarmHRGatewayException;
	
	public SwarmHRUserVo getUserInfo(String token, String view);
	
	public boolean validateOTP(int otp, String username) throws SwarmHRGatewayException;

	boolean saveLoginDetails(String username, String IdAddress) throws SwarmHRGatewayException;

	public List<ReportsConfigurationVO> getReportsConfigList(String orgId) throws SwarmHRGatewayException;

	public List<ReportPermission> getReportPermissionListforUser(String username, String orgID) throws SwarmHRGatewayException;

	public List<ReportVo> listActiveReportsUser(String reportIDs) throws SwarmHRGatewayException;

	Map<String, String> listTypesAndValues(String organizationID);

}
