package com.swarmhr.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.cloud.storage.Storage;
import com.swarmhr.gateway.security.SwarmHRJwtTokenFilterConfigurer;
import com.swarmhr.gateway.security.SwarmHRJwtTokenProvider;
import com.swarmhr.gateway.util.GoogleStorageUtility;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SwarmHRSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SwarmHRJwtTokenProvider jwtTokenProvider;
	
	@Value("${spring.profile}")
	String currEnv;


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Disable CSRF (cross site request forgery)
		http.cors().and().csrf().disable();

		// No session will be created or used by spring security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Entry points
		http.authorizeRequests().antMatchers("/**/signin/**").permitAll()
				.antMatchers("/**/emailPasswordLink/**").permitAll()
				.antMatchers("/**/sendPasswordEmail/**").permitAll()
				.antMatchers("/**/resetPassword/**").permitAll()
				.antMatchers("/**/savePassword/**").permitAll()
				.antMatchers("/**/activateAlias/**").permitAll()
				.antMatchers("/**/includeSignature/**").permitAll()
				.antMatchers("/**/RedirectGoogle/**").permitAll()  
				.antMatchers("/**/RedirectOutlook/**").permitAll() 
				.antMatchers("/**/employeeMailTrack/**").permitAll()
				.antMatchers("/**/signRequestRedirectLink/**").permitAll()
				.antMatchers("/**/onboardingRedirectLink/**").permitAll()
				.antMatchers("/**/lcaMailTrack/**").permitAll()
				.antMatchers("/**/SwarmHrRedirectLink/**").permitAll()
				.antMatchers("/**/saveI9Documents/**").permitAll()
				.antMatchers("/**/viewDetails/**").permitAll() //check
				.antMatchers("/**/viewAssignTaskDetails/**").permitAll()
				.antMatchers("/**/sendTextEmailWithoutToken/**").permitAll()
				.antMatchers("/**/StatusReportFromLink/**").permitAll()
				.antMatchers("/**/UpdateStatusReportFromLink/**").permitAll()
				.antMatchers("/**/paf/**").permitAll()
				.antMatchers("/**/sharedLink/**").permitAll()
				.antMatchers("/**/invoiceMailTrack/**").permitAll()
				.antMatchers("/**/emailTrack/**").permitAll()
				.antMatchers("/**/viewDetailsPremium/**").permitAll()  
				.antMatchers("/**/viewAssignTaskDetailsPremium/**").permitAll() 
				.antMatchers("/**/userUpdatedNotesPremium/**").permitAll()
				.antMatchers("/**/createdUserUpdatedNotesPremium/**").permitAll()
				.antMatchers("/**/display_shared_link/**").permitAll()
				.antMatchers("/**/oauth2redirect/**").permitAll()
				.antMatchers("/**/externalManager/**").permitAll()	
				.antMatchers("/**/Tasks/**").permitAll() 
				.antMatchers("/**/sendExternalTextEmail/**").permitAll()
				.antMatchers("/**/organisation/**").permitAll() //check
				.antMatchers("/**/saveI9Documents/**").permitAll()
				.antMatchers("/**/taskuserUpdatedNotes/**").permitAll()
				.antMatchers("/**/userUpdatedNotes/**").permitAll()
				.antMatchers("/**/sendPublicTemplateByOrgModule/**").permitAll()
				.antMatchers("/**/manager/onboard/**").permitAll()
				.antMatchers("/**/redirect-authcode/**").permitAll()
				.antMatchers("/**/billingMakePayment/**").permitAll()
				.antMatchers("/**/addOrderid/**").permitAll()
				.antMatchers("/**/receiveEmailSNSNotification/**").permitAll()
				.antMatchers("/**/includeSignature1/**").permitAll()
				.antMatchers("/**/MyDocuments/downloadDocument/**").permitAll()
				.antMatchers("/**/interviews/public/**").permitAll()
				.antMatchers("/**/public/questionaire/**").permitAll()
				.antMatchers("/**/myProgram/checkProgramExpiryDate/**").permitAll()
				.antMatchers("/**/myProgram/reRequestTest/**").permitAll()
				.antMatchers("/**/getSelectedQuestionsForEmployeeExitTemplateForm**").permitAll()
				.antMatchers("/**/myMessages/getGmailInboxSentCount**").permitAll()
				.antMatchers("/**/MyDocuments/getDocPathPublic/**").permitAll()
				.antMatchers("/**/i9EmployeeForm/**").permitAll()
				.antMatchers("/**/exitManagement/getusernamewithtoken/**").permitAll()
				.antMatchers("/**/exitManagementForm/**").permitAll()
				.antMatchers("/**/i9/fetchI9/**").permitAll()
				.antMatchers("/**/i9/updateI9Form/**").permitAll()
				.antMatchers("/**/i9/saveI9Document/**").permitAll()
				.antMatchers("/**/i9/autoFillI9Form/**").permitAll()
				.antMatchers("/**/user/getAllCountriesBasedOnExteOrgId/**").permitAll()
				.antMatchers("/**/i9/geti9DocumentswithUniqueId/**").permitAll()
				.antMatchers("/**/manager/saveExternalOnboard/**").permitAll()
				.antMatchers("/**/exitManagement/getEMSQuestionnaireExt/**").permitAll()
				.antMatchers("/**/exitManagement/saveEmployeeEMSFormExt/**").permitAll()
				.antMatchers("/**/exitManagement/getEmployeeEMSFormExt/**").permitAll()
				.antMatchers("/**/exitManagement/getEMSDocumentswithExtUsernameAndStatus/**").permitAll()
				.antMatchers("/**/exitManagement/viewDocumentExt/**").permitAll()
				.antMatchers("/**/exitManagement/viewDocumentswithDocumentType/**").permitAll()
				.antMatchers("/**/exitManagement/downloadMultipleDocuments/**").permitAll()
				.antMatchers("/**/user/saveOnboardAddress/**").permitAll()
				.antMatchers("/**/user/getOnboardAddressandType/**").permitAll()
				.antMatchers("/**/exitManagement/getEmployeeEMSFormStatus/**").permitAll()
				.antMatchers("/**/properties/getExtQuestionaireConfig/**").permitAll()
				.antMatchers("/**/employeeInfo/getPersonalInfoDetails/**").permitAll()
				.antMatchers("/**/employeeInfo/savePersonalInfo/**").permitAll()
				.antMatchers("/**/employeeInfo/saveFamilyDetails/**").permitAll()
				.antMatchers("/**/employeeInfo/getFamilyDetails/**").permitAll()
				.antMatchers("/**/employeeInfo/saveEducationalDetails/**").permitAll()
				.antMatchers("/**/employeeInfo/getEmployeeEducationDetails/**").permitAll()
				.antMatchers("/**/employeeInfo/saveImmigration/**").permitAll()
				.antMatchers("/**/employeeInfo/getImmigrationlDetails/**").permitAll()
				.antMatchers("/**/employeeInfo/saveDependents/**").permitAll()
				.antMatchers("/**/employeeInfo/getQuesDependents/**").permitAll()
				.antMatchers("/**/employeeInfo/quesDependentsUpdate/**").permitAll()
				.antMatchers("/**/employeeInfo/updateDependentInfo/**").permitAll()
				.antMatchers("/**/employeeInfo/quesApplicationSave/**").permitAll()
				.antMatchers("/**/employeeInfo/getQuesApplications/**").permitAll()
				.antMatchers("/**/employeeInfo/quesApplicationUpdate/**").permitAll()
				.antMatchers("/**/properties/getByPropertyType/**").permitAll()
				.antMatchers("/**/employeeInfo/saveReportingDetails/**").permitAll()
				.antMatchers("/**/employeeInfo/getReportingDetails/**").permitAll()
				.antMatchers("/**/employeeInfo/saveOtherPersonalDetails/**").permitAll()
				.antMatchers("/**/employeeInfo/getOtherPersonalDetails/**").permitAll()
				.antMatchers("/**/employeeInfo/saveOnBoardEmployeeAddress/**").permitAll()
				.antMatchers("/**/employeeInfo/getOnBoardEmployeeAddress/**").permitAll()
				.antMatchers("/**/employeeInfo/getOnBoardEmployeeHistory/**").permitAll()
				.antMatchers("/**/employeeInfo/saveOnBoardEmployeeHistory/**").permitAll()
				.antMatchers("/**/exitManagement/sendEmailSubmittedFormsExt/**").permitAll()
				.antMatchers("/**/offerManagement/getOfferDocumenttoATS/**").permitAll()
				.antMatchers("/**/offerManagement/approveorRejectOffers/**").permitAll()
				.antMatchers("/**/offerManagement/approveorRejectOfferCandidate/**").permitAll()
				.antMatchers("/**/offerManagement/shortlistedCandidates/**").permitAll()
				.antMatchers("/**/OrganizationAdmin/Properties/updateextProperties/**").permitAll()
				.antMatchers("/**/tds/**").permitAll()
				

				
				// Disallow everything else..
				.anyRequest().authenticated();

		// If a user try to access a resource without having enough permissions
		http.exceptionHandling().accessDeniedPage("/login");

		// Apply JWT
		http.apply(new SwarmHRJwtTokenFilterConfigurer(jwtTokenProvider));

		// Optional, if you want to test the API from a browser
		// http.httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Allow eureka client to be accessed without authentication
		web.ignoring().antMatchers("/*/")//
				.antMatchers("/eureka/**")//
				.antMatchers(HttpMethod.OPTIONS, "/**"); // Request type options should be allowed.
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}
	
}