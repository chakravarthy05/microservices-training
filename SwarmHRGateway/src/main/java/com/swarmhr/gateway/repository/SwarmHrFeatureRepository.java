package com.swarmhr.gateway.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.swarmhr.gateway.entity.Organisation;
import com.swarmhr.gateway.entity.SpecialAccessGroup;
import com.swarmhr.gateway.vo.SwarmHRFeatureVo;

@Repository
public class SwarmHrFeatureRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	OrganisationRepository organisationRepository;
	private static Map<String, Integer> featureSortOrder = new HashMap<String, Integer>();

	static {
		featureSortOrder.put("My Info", 1);
		featureSortOrder.put("My Timesheets", 2);
		featureSortOrder.put("Claim Expenses", 3);
		featureSortOrder.put("Request Leave", 4);
		featureSortOrder.put("Cash Advance", 5);
		featureSortOrder.put("My Evaluations", 6);
		featureSortOrder.put("My Programs", 7);
		featureSortOrder.put("My Documents", 8);
		featureSortOrder.put("My Messages", 9);
		featureSortOrder.put("My Tasks", 10);
		featureSortOrder.put("Email Signature", 11);
		featureSortOrder.put("Message Templates", 12);
		featureSortOrder.put("Employee Directory", 13);
		featureSortOrder.put("Bills", 14);
		featureSortOrder.put("Reports", 15);
		featureSortOrder.put("File Storage", 16);
		featureSortOrder.put("Finance", 17);
		featureSortOrder.put("Group Mailing", 18);
		featureSortOrder.put("ATS", 19);
		featureSortOrder.put("Sample Forms", 20);
		featureSortOrder.put("My Case", 21);
	}

	public List<SwarmHRFeatureVo> listUserFeatures(String username, String[] userRoles) {
		String sql = "SELECT departmentID FROM department_member WHERE username='" + username + "'";
		String deptID = jdbcTemplate.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next())
					return rs.getString("departmentID");
				return "Not Found";
			}
		});
		if (deptID.equals("Not Found")) {
			String sql2 = "SELECT departmentID FROM department_admins WHERE username='" + username + "'";
			String dept = jdbcTemplate.query(sql2, new ResultSetExtractor<String>() {
				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next())
						return rs.getString("departmentID");
					return "Not Found";
				}
			});
			if (dept.equals("Not Found")) {
				String sql3 = "SELECT organizationID FROM employee WHERE username='" + username + "'";
				String orgID = jdbcTemplate.query(sql3, new ResultSetExtractor<String>() {
					@Override
					public String extractData(ResultSet rs) throws SQLException, DataAccessException {
						if (rs.next())
							return rs.getString("organizationID");
						return "Not Found";
					}
				});
				List<SwarmHRFeatureVo> orgFeatures = this.getFeaturesList(orgID);
				return validateUserRole(orgFeatures, username, userRoles);
			}
			List<SwarmHRFeatureVo> featuresList = this.listDepartmentFeatures(dept);
			return validateUserRole(featuresList, username, userRoles);
		}
		List<SwarmHRFeatureVo> featuresList = this.listDepartmentFeatures(deptID);
		return validateUserRole(featuresList, username, userRoles);
	}

	private List<SwarmHRFeatureVo> validateUserRole(List<SwarmHRFeatureVo> featuresList, String username,
			String[] userRoles) {
		
		List<String> roles = new ArrayList<String>();
        Collections.addAll(roles, userRoles); 

		if (roles.contains("ROLE_ORGADMIN")) {
			roles.clear();
			roles.add("ROLE_USER");
			roles.add("ROLE_SUPERUSER");
		}
		if (!roles.contains("ROLE_SUPERUSER")) {
			List<SwarmHRFeatureVo> returnList = new ArrayList<SwarmHRFeatureVo>();
			for (SwarmHRFeatureVo f : featuresList) {
				if (roles.contains(f.getAccessLevel())) {
					returnList.add(f);
				}
			}
			return returnList;
		}
		return featuresList;
	}

	public List<SwarmHRFeatureVo> getFeaturesList(String organizationID) {
		String sql = "(SELECT t1.featureID, t1.featureName, t1.iconClass, t1.url, t1.accessLevel FROM FeatureDetails t1, PlanFeatures t2, organization t3 WHERE t3.organizationID='"
				+ organizationID
				+ "' AND t3.subscriptionPlan=t2.planID AND t2.featureID=t1.featureID ORDER BY t1.featureID) UNION (SELECT t1.featureID, t1.featureName, t1.iconClass, t1.url, t1.accessLevel FROM FeatureDetails t1, additional_features t2 WHERE t2.organizationID='"
				+ organizationID + "' AND t2.featureID=t1.featureID)";
		return jdbcTemplate.query(sql, new RowMapper<SwarmHRFeatureVo>() {
			@Override
			public SwarmHRFeatureVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				SwarmHRFeatureVo feature = new SwarmHRFeatureVo();
				feature.setFeatureID(rs.getString("featureID"));
				feature.setFeatureName(rs.getString("featureName"));
				feature.setIconClass(rs.getString("iconClass"));
				feature.setUrl(rs.getString("url").trim());
				feature.setAccessLevel(rs.getString("accessLevel"));
				if (featureSortOrder.get(feature.getFeatureName()) != null) {
					feature.setPriorty(featureSortOrder.get(feature.getFeatureName()));
				}
				return feature;
			}
		});
	}
	
	
	public List<SwarmHRFeatureVo> listAllOrgFeatures(String organizationID) {
		Organisation orglist = organisationRepository.findByOrganizationID(organizationID);
		String planID= orglist.getSubscriptionPlan();
		String sql = "SELECT * FROM ebdb.FeatureDetails where featureID in (select featureID from  PlanFeatures where planID ='" + planID + "' Union all " + 
				"select featureID from additional_features where  organizationId ='" + organizationID+"')";
		List<SwarmHRFeatureVo> featuresList = jdbcTemplate.query(sql, new RowMapper<SwarmHRFeatureVo>() {
			@Override
			public SwarmHRFeatureVo mapRow(ResultSet rs, int rowNum) throws SQLException {

				SwarmHRFeatureVo feature = new SwarmHRFeatureVo();
				feature.setFeatureID(rs.getString("featureID"));
				feature.setFeatureName(rs.getString("featureName"));
				feature.setIconClass(rs.getString("iconClass"));
				feature.setUrl(rs.getString("url").trim());
				feature.setAccessLevel(rs.getString("accessLevel"));
//				feature.setCategory(rs.getString("category"));
				return feature;
			}
		});
		return featuresList;
	}

	private List<SwarmHRFeatureVo> listDepartmentFeatures(String departmentID) {
		String sql = "SELECT t1.featureID, t1.featureName, t1.iconClass, t1.url, t1.accessLevel FROM FeatureDetails t1, department_features t2 WHERE t2.departmentID='"
				+ departmentID + "' AND t2.featureID=t1.featureID";
		List<SwarmHRFeatureVo> featuresList = jdbcTemplate.query(sql, new RowMapper<SwarmHRFeatureVo>() {
			@Override
			public SwarmHRFeatureVo mapRow(ResultSet rs, int rowNum) throws SQLException {

				SwarmHRFeatureVo feature = new SwarmHRFeatureVo();
				feature.setFeatureID(rs.getString("featureID"));
				feature.setFeatureName(rs.getString("featureName"));
				feature.setIconClass(rs.getString("iconClass"));
				feature.setUrl(rs.getString("url").trim());
				feature.setAccessLevel(rs.getString("accessLevel"));
				if (featureSortOrder.get(feature.getFeatureName()) != null) {
					feature.setPriorty(featureSortOrder.get(feature.getFeatureName()));
				}
				return feature;
			}
		});
		return featuresList;
	}
	
	
	public List<SwarmHRFeatureVo> getBasicFeatures(){
		String sql = "SELECT * FROM FeatureDetails WHERE url IN ('user', 'MyProfile')";
		List<SwarmHRFeatureVo> featuresList = jdbcTemplate.query(sql, new RowMapper<SwarmHRFeatureVo>(){
			@Override
			public SwarmHRFeatureVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				SwarmHRFeatureVo feature = new SwarmHRFeatureVo();								
				feature.setFeatureID(rs.getString("featureID"));
				feature.setFeatureName(rs.getString("featureName"));
				feature.setIconClass(rs.getString("iconClass"));
				feature.setUrl(rs.getString("url").trim());
				feature.setAccessLevel(rs.getString("accessLevel"));	
				if(featureSortOrder.get(feature.getFeatureName()) != null) {
					feature.setPriorty(featureSortOrder.get(feature.getFeatureName()));
				}
				return feature;
			}			
		});
		return featuresList;
	}
	
	public List<SwarmHRFeatureVo> getManagerFeatures(String organizationID, String username){		
		List<SwarmHRFeatureVo> returnList = new ArrayList<SwarmHRFeatureVo>();
		String sql = "SELECT role FROM user_roles WHERE username='"+username+"'";
		List<String> roleList = jdbcTemplate.query(sql, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException{
				return rs.getString("role");
			}
		});
		if(roleList.contains("ROLE_MANAGER")){
			List<SwarmHRFeatureVo> featuresList = this.getFeaturesList(organizationID);		
			for(SwarmHRFeatureVo feature : featuresList){
				if(feature.getAccessLevel().equals("ROLE_MANAGER"))
					returnList.add(feature);
			}
		}		
		return returnList;
	}
	public SwarmHRFeatureVo getManagerAccessDetails(String empUsername, String organizationID) {
		String sql = "SELECT * FROM ManagerAccessGroup WHERE organizationID='" + organizationID + "' and empUsername ='"
				+ empUsername + "'";
		SwarmHRFeatureVo listGroup = jdbcTemplate.query(sql, new ResultSetExtractor<SwarmHRFeatureVo>() {
			@Override
			public SwarmHRFeatureVo extractData(ResultSet rs) throws SQLException {
				SwarmHRFeatureVo group = new SwarmHRFeatureVo(); 
				if (rs.next()) {
				group.setFeatureName(rs.getString("managerGroupNames"));
				group.setFeatureID(rs.getString("empUsername"));
				}
				return group;
			} 
	      });
		return listGroup;   
	}  
	
	public List<SwarmHRFeatureVo> getBasicManagerFeatures(){
		String sql = "SELECT * FROM FeatureDetails WHERE accessLevel='ROLE_MANAGER'";
		List<SwarmHRFeatureVo> featuresList = jdbcTemplate.query(sql, new RowMapper<SwarmHRFeatureVo>(){
			@Override
			public SwarmHRFeatureVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				SwarmHRFeatureVo feature = new SwarmHRFeatureVo();								
				feature.setFeatureID(rs.getString("featureID"));
				feature.setFeatureName(rs.getString("featureName"));
				feature.setIconClass(rs.getString("iconClass"));
				feature.setUrl(rs.getString("url").trim());
				feature.setAccessLevel(rs.getString("accessLevel"));	
				if(featureSortOrder.get(feature.getFeatureName()) != null) {
					feature.setPriorty(featureSortOrder.get(feature.getFeatureName()));
				}
				return feature;
			}			
		});
		return featuresList;
	}
	
	public List<SwarmHRFeatureVo> getSpecialFeatures(List<String> featureList){
		String features="";
		for (String user : featureList) {
			features = features+"'"+user+"',";
		}
		if(!features.isEmpty()) {
			features = features.substring(0, features.length()-1);
		}
		String sql = "SELECT * FROM FeatureDetails WHERE featureID IN ("+features+")";
		List<SwarmHRFeatureVo> featuresList = jdbcTemplate.query(sql, new RowMapper<SwarmHRFeatureVo>(){
			@Override
			public SwarmHRFeatureVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				SwarmHRFeatureVo feature = new SwarmHRFeatureVo();								
				feature.setFeatureID(rs.getString("featureID"));
				feature.setFeatureName(rs.getString("featureName"));
				feature.setIconClass(rs.getString("iconClass"));
				feature.setUrl(rs.getString("url").trim());
				feature.setAccessLevel(rs.getString("accessLevel"));	
				feature.setRouterLink(rs.getString("routerLink"));
				if(featureSortOrder.get(feature.getFeatureName()) != null) {
					feature.setPriorty(featureSortOrder.get(feature.getFeatureName()));
				}
				return feature;
			}			
		});
		return featuresList;
	}
	
	public List<SpecialAccessGroup> getSpecialGroups(String organizationID, String username) {
		String sql = "SELECT t1.groupID, t1.groupName, t1.features FROM SpecialAccessGroup t1, EmployeeGroups t2 WHERE t2.empUsername='"+username+"' AND t1.organizationID='"+organizationID+"' And t1.status='Active' AND t2.groupID=t1.groupID";
		List<SpecialAccessGroup> group = jdbcTemplate.query(sql, new RowMapper<SpecialAccessGroup>() {
			@Override
			public SpecialAccessGroup mapRow(ResultSet rs, int rowNum) throws SQLException {

				SpecialAccessGroup group = new SpecialAccessGroup();
				group.setGroupID(rs.getString("groupID"));
				group.setGroupName(rs.getString("groupName"));
				group.setFeatures(rs.getString("features"));
				return group;
			}
		});
		return group;
	}
	

}
