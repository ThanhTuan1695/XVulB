package com.mgmtp.blog.setting;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.mgmtp.blog.setting.SecurityEnum.*;

@Component
@ConfigurationProperties(prefix = "security")
public class SecuritySettings {
	
	private PwBruteForce pwBruteForce;
	private SessionFixation ssFixation;

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
	
	
}

