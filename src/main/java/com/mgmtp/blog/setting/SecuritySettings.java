package com.mgmtp.blog.setting;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security")
public class SecuritySettings {
	
	private PwBruteForceEnum pwbruteforce;

	public PwBruteForceEnum getPwbruteforce() {
		return pwbruteforce;
	}

	public void setPwbruteforce(PwBruteForceEnum pwbruteforce) {
		this.pwbruteforce = pwbruteforce;
	}
	
	
}

