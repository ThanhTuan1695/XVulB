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
	
}

