package com.mgmtp.blog.setting;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.mgmtp.blog.setting.SecurityEnum.*;

@Component
@ConfigurationProperties(prefix = "security")
public class SecuritySettings {
	
	private PwBruteForce pwbruteforce;
	private SessionFixation ssfixation;

	public PwBruteForce getPwbruteforce() {
		return pwbruteforce;
	}

	public void setPwbruteforce(PwBruteForce pwbruteforce) {
		this.pwbruteforce = pwbruteforce;
	}

	public SessionFixation getSsFixation() {
		return ssfixation;
	}

	public void setSsFixation(SessionFixation ssfixation) {
		this.ssfixation = ssfixation;
	}
	
	
}

