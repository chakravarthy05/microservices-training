package com.swarmhr.gateway.repository;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.swarmhr.gateway.entity.SwarmHRUserInfo;
import com.swarmhr.gateway.util.GoogleStorageUtility;
import com.swarmhr.gateway.vo.CustomPropertyConfiguration;
import com.swarmhr.gateway.vo.LogoFileVO;
import com.swarmhr.gateway.vo.SwarmHRUserVo;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@Repository
public class SwarmHrUserInfoRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	Datastore datastore;

	@Autowired
	GoogleStorageUtility storageUtility;

	private final DateTimeFormatter displayTimestampFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss");

	public SwarmHRUserInfo getSwarmHrUserInfo(String username) {
		String sql = "SELECT e.firstname, e.lastname, e.mobile, e.email, e.organizationID, d.departmentID from employee e"
				+ " Left Join department_member d on d.username=e.username  WHERE e.username='" + username + "'";
		SwarmHRUserInfo swarmHRUserInfo = jdbcTemplate.query(sql, new ResultSetExtractor<SwarmHRUserInfo>() {

			@Override
			public SwarmHRUserInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					SwarmHRUserInfo userInfo = new SwarmHRUserInfo();
					userInfo.setFirstName(rs.getString("firstname"));
					userInfo.setLastName(rs.getString("lastname"));
					String mobile = rs.getString("mobile");
					if (mobile != null)
						userInfo.setMobile(mobile.replace("_", " "));
					userInfo.setEmail(rs.getString("email"));
					userInfo.setOrgId(rs.getString("organizationID"));
					userInfo.setDeptId(rs.getString("departmentID"));
					return userInfo;
				}
				return null;
			}
		});
		return swarmHRUserInfo;
	}

	public SwarmHRUserVo getSwarmHrUserVo(String username) {
		String sql = "SELECT e.username, e.firstname, e.lastname, e.mobile, e.email, e.organizationID, e.authentication, e.authenticationType, e.baseWorkingCountry, d.departmentID,"
				+ " d.departmentName,  o.organizationName  from employee e  Left Join department_member d on d.username=e.username Left Join"
				+ " organization o on o.organizationID=e.organizationID  WHERE e.username='" + username + "'";
		SwarmHRUserVo swarmHRUserVo = jdbcTemplate.query(sql, new ResultSetExtractor<SwarmHRUserVo>() {

			@Override
			public SwarmHRUserVo extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					SwarmHRUserVo userInfo = new SwarmHRUserVo();
					userInfo.setUserName(rs.getString("username"));
					userInfo.setFirstName(rs.getString("firstname"));
					userInfo.setLastName(rs.getString("lastname"));
					String mobile = rs.getString("mobile");
					if (mobile != null)
						userInfo.setMobile(mobile.replace("_", " "));
					userInfo.setEmail(rs.getString("email"));
					userInfo.setOrgId(rs.getString("organizationID"));
					userInfo.setDeptId(rs.getString("departmentID"));
					userInfo.setDepartmentName(rs.getString("departmentName"));
					userInfo.setOrganizationName(rs.getString("organizationName"));
					userInfo.setAuthentication(rs.getString("authentication"));
					userInfo.setAuthenticationType(rs.getString("authenticationType"));
					userInfo.setCountry(rs.getString("baseWorkingCountry"));
					return userInfo;
				}
				return null;
			}
		});
		return swarmHRUserVo;
	}

	public boolean validateUser(String username, String email) {
		Integer cnt = jdbcTemplate.queryForObject(
				"SELECT count(*) FROM employee WHERE email='" + email + "' and username='" + username + "'",
				Integer.class);
		if (cnt != null && cnt > 0)
			return true;
		return false;
	}

	public List<String> getSwarmUserRoles(String username) {
		String sql = "SELECT role from user_roles where username = '" + username + "'";
		List<String> rolesList = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("role");
			}
		});
		return rolesList;
	}

	public String getAuthentication(String organizationID) {
		String sql = "select authentication from organization where organizationID ='" + organizationID + "'";
		String org = jdbcTemplate.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String authStatus = rs.getString("authentication");
					if (null == authStatus || authStatus.isEmpty()) {
						authStatus = "Disable";
					}
					return authStatus;
				}
				return "Disable";
			}
		});
		return org;
	}

	public String country(String username, String organizationID) {
		String sql = "SELECT t1.country FROM department t1, department_member t2 WHERE t2.username='" + username
				+ "' AND t1.departmentID=t2.departmentID ";
		String country = "";
		country = jdbcTemplate.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getString("country");
				}
				return "No Country";
			}
		});
		if (country.equalsIgnoreCase("No Country")) {
			String sql1 = "SELECT country FROM organization Where organizationID='" + organizationID + "'";
			country = jdbcTemplate.query(sql1, new ResultSetExtractor<String>() {
				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next()) {
						return rs.getString("country");
					}
					return null;
				}
			});
		}
		return country;
	}

	public String getOrgEmail(String orgID) {
		String sql = "SELECT * FROM organization WHERE  organizationID='" + orgID + "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getString("email");
				}
				return null;
			}
		});
	}

	public CustomPropertyConfiguration getCutomPropertyDetailsByName(String propertyName, String organizationID) {
		String entityKind = "custom_properties";
		CustomPropertyConfiguration cutomProperty = new CustomPropertyConfiguration();
		String gql = "SELECT * FROM " + entityKind + " WHERE propertyName=@request";
		Query<Entity> query = Query.newGqlQueryBuilder(Query.ResultType.ENTITY, gql).setNamespace(organizationID)
				.setBinding("request", propertyName).build();
		try {
			QueryResults<Entity> results = datastore.run(query);
			if (results.hasNext()) {
				Entity rs = results.next();
				cutomProperty.setRequestID(rs.getString("requestID"));
				cutomProperty.setOrganizationID(rs.getString("organizationID"));
				cutomProperty.setUsername(rs.getString("username"));
				cutomProperty.setPropertyName(rs.getString("propertyName"));
				cutomProperty.setPropertyGroupName(rs.getString("propertyGroupName"));
				cutomProperty.setOptionsType(rs.getString("optionsType"));
				cutomProperty.setPropertyValue(rs.getString("propertyValue"));
				cutomProperty.setCreatedByUsername(rs.getString("createdByUsername"));
				cutomProperty.setCreatedByFullName(rs.getString("createdByFullName"));
				cutomProperty.setCreatedBySqlTimestamp(rs.getString("createdBySqlTimestamp"));
				cutomProperty.setCreatedByTimestamp(rs.getString("createdByTimestamp"));
				cutomProperty.setDatastoreEntityKey(rs.getKey());
			}
		} catch (Exception e) {
		}
		return cutomProperty;
	}

	public void saveOtp(String otp, String username, String orgID) {
		String remove = "DELETE FROM otp WHERE username='" + username + "' and otp != '" + otp + "'";
		jdbcTemplate.update(remove);
		String sql = "INSERT INTO otp ( username, otp, organizationID)" + "VALUES(?, ?, ?)";
		jdbcTemplate.update(sql, username, otp, orgID);
	}

	public boolean validateOTP(String username, int otp) {
		Integer cnt = jdbcTemplate.queryForObject(
				"SELECT count(*) FROM otp WHERE otp='" + otp + "' and username='" + username + "'", Integer.class);
		if (cnt != null && cnt > 0)
			return true;
		return false;
	}

	public String fetchOrgLogoPublicUrl(String orgID, String envUrl) {
		String url = "";
		try {
			String sql = "SELECT * FROM organization WHERE  organizationID='" + orgID + "'";
			LogoFileVO logo = jdbcTemplate.query(sql, new ResultSetExtractor<LogoFileVO>() {
				@Override
				public LogoFileVO extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next()) {
						LogoFileVO logo = new LogoFileVO();
						logo.setFile(rs.getBytes("logoFile"));
						logo.setFileType(rs.getString("logoFileType"));
						return logo;
					}
					return null;
				}
			});

			File logoFile = File.createTempFile("OrgLogo", ".png", null);
			FileOutputStream fileOuputStream = new FileOutputStream(logoFile);
			fileOuputStream.write(logo.getFile());
			fileOuputStream.close();

			File logoFile1 = File.createTempFile("ThumbNail", ".png", null);

			Thumbnails.of(new File(logoFile.getAbsolutePath())).size(100, 50).outputFormat("png")
					.toFile(logoFile1.getAbsolutePath());

			byte[] byteFile = FileUtils.readFileToByteArray(logoFile1.getAbsoluteFile());
			storageUtility.deleteDocument(orgID, logo.getFileType(), envUrl);
			url = storageUtility.getPublicURL_orgLogo(orgID, byteFile, logo.getFileType(), envUrl);
			logoFile.delete();
			logoFile1.delete();

		} catch (Exception e) { 
		}
		return url;
	}

	public String fetchDeptLogoPublicUrl(String deptID, String envUrl) {
		String url = "";
		try {
			String sql = "SELECT logoFile, logoFileType FROM department WHERE departmentID ='" + deptID + "'";
			LogoFileVO logo = jdbcTemplate.query(sql, new ResultSetExtractor<LogoFileVO>() {
				@Override
				public LogoFileVO extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next()) {
						LogoFileVO logo = new LogoFileVO();
						logo.setFile(rs.getBytes("logoFile"));
						logo.setFileType(rs.getString("logoFileType"));
						return logo;
					}
					return null;
				}
			});
			
			File logoFile = File.createTempFile("OrgLogo", ".png", null);
			FileOutputStream fileOuputStream = new FileOutputStream(logoFile);
			fileOuputStream.write(logo.getFile());
			fileOuputStream.close();

			File logoFile1 = File.createTempFile("ThumbNail", ".png", null);

			Thumbnails.of(new File(logoFile.getAbsolutePath())).size(100, 50).outputFormat("png")
					.toFile(logoFile1.getAbsolutePath());

			byte[] byteFile = FileUtils.readFileToByteArray(logoFile1.getAbsoluteFile());
			storageUtility.deleteDocument(deptID, logo.getFileType(), envUrl);
			url = storageUtility.getPublicURL_orgLogo(deptID, byteFile, logo.getFileType(), envUrl);
			logoFile.delete();
			logoFile1.delete();
		} catch (Exception e) {
		}
		return url;
	}

	public String getStatus(String username) {
		String sql = "select status from employee where username ='" + username + "'";
		String status = jdbcTemplate.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String authStatus = rs.getString("status");
					return authStatus;
				}
				return "Disable";
			}
		});
		return status;
	}

	public String getAccess(String username) {
		String sql = "select accessType from employee where username ='" + username + "'";
		String status = jdbcTemplate.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String authStatus = rs.getString("accessType");
					return authStatus;
				}
				return "None";
			}
		});
		return status;
	}

	public String getOrganizationStatus(String organizationID) {
		String sql = "select status from organization where organizationID ='" + organizationID + "'";
		String status = jdbcTemplate.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String authStatus = rs.getString("status");
					return authStatus;
				}
				return "Disable";
			}
		});
		return status;
	}

	public boolean createLoginObject(SwarmHRUserVo obj, String IPAddress) {
		Date logDate = Date.valueOf(LocalDate.now(ZoneId.of("America/Chicago")));
		String timestamp = LocalDateTime.now(ZoneId.of("America/Chicago")).format(displayTimestampFormatter);
		try {
			if (null == obj.getDeptId())
				obj.setDeptId("No Department");
			else
				obj.setDeptId(obj.getDeptId());
		} catch (Exception e) {
			obj.setDeptId("No Department");
		}
		String table_name = obj.getOrgId() + "_loginTrack";
		final KeyFactory keyFactory = datastore.newKeyFactory().setKind(table_name);
		// The Cloud Datastore key for the new entity
		Key key = datastore.allocateId(keyFactory.newKey());
		// Prepares the new entity
		Entity task = Entity.newBuilder(key).set("username", obj.getUserName()).set("firstname", obj.getFirstName())
				.set("lastname", obj.getLastName()).set("organizationID", obj.getOrgId())
				.set("organizationName", obj.getOrganizationName()).set("departmentID", obj.getDeptId())
				.set("ipaddress", IPAddress).set("logDate", logDate.toString()).set("timeStamp", timestamp)
				.set("logChannel", "Web Application").build();
		// Saves the entity
		try {
			datastore.put(task);
			System.out.println("Created a new login object for: " + obj.getFirstName() + " " + obj.getLastName()
					+ " with username: " + obj.getUserName() + " from org: " + obj.getOrgId());
			return true;
		} catch (Exception e) {
			System.out.println("Caught Exception: " + e.getCause());
			System.out.println("Failed to create a new login object for: " + obj.getFirstName() + " "
					+ obj.getLastName() + " with username: " + obj.getUserName() + " from org: " + obj.getOrgId());
			return false;
		}
	}
}
