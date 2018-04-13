package com.mgmtp.blog.setting;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.mgmtp.blog.setting.SecurityEnum.*;

@Component
@ConfigurationProperties(prefix = "security")
public class SecuritySettings {
	
	private PwBruteForce pwBruteForce;
	private SessionFixation ssFixation;
	private SqlInjection sqlInjection;
	private PasswordStorage pwStorage;
	private ResetPassword resetPassword;
	private SetCookie setCookie;
	private XSSPrevention xssPrevention;
	private CSRFProtection csrfProtection;
	private HorizontalEscalation horizontalEscalation; 
	private VerticalEscalation verticalEscalation; 
	
	public SqlInjection getSqlInjection() {
		return sqlInjection;
	}

	public void setSqlInjection(SqlInjection sqlInjection) {
		this.sqlInjection = sqlInjection;
	}

	public PwBruteForce getPwbruteforce() {
		return pwBruteForce;
	}

	public void setPwbruteforce(PwBruteForce pwBruteForce) {
		this.pwBruteForce = pwBruteForce;
	}

	public SessionFixation getSsFixation() {
		return ssFixation;
	}

	public void setSsFixation(SessionFixation ssFixation) {
		this.ssFixation = ssFixation;
	}

	public PasswordStorage getPwStorage() {
		return pwStorage;
	}

	public void setPwStorage(PasswordStorage pwStorage) {
		this.pwStorage = pwStorage;
	}

	public ResetPassword getResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(ResetPassword resetPassword) {
		this.resetPassword = resetPassword;
	}

	public SetCookie getSetCookie() {
		return setCookie;
	}

	public void setSetCookie(SetCookie setCookie) {
		this.setCookie = setCookie;
	}

	public XSSPrevention getXssPrevention() {
		return xssPrevention;
	}

	public void setXssPrevention(XSSPrevention xssPrevention) {
		this.xssPrevention = xssPrevention;
	}

	public CSRFProtection getCsrfProtection() {
		return csrfProtection;
	}

	public void setCsrfProtection(CSRFProtection csrfProtection) {
		this.csrfProtection = csrfProtection;
	}

	public HorizontalEscalation getHorizontalEscalation() {
		return horizontalEscalation;
	}

	public void setHorizontalEscalation(HorizontalEscalation horizontalEscalation) {
		this.horizontalEscalation = horizontalEscalation;
	}

	public VerticalEscalation getVerticalEscalation() {
		return verticalEscalation;
	}

	public void setVerticalEscalation(VerticalEscalation verticalEscalation) {
		this.verticalEscalation = verticalEscalation;
	}
	
}

