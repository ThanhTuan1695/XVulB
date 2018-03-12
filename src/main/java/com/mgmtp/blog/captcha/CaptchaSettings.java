package com.mgmtp.blog.captcha;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.recaptcha")
public class CaptchaSettings {
 
    private String keysite;
    private String keysecret;
    private String uri;
    public CaptchaSettings () {
    	
    }
	public String getKeySite() {
		return keysite;
	}
	public void setKeySite(String keysite) {
		this.keysite = keysite;
	}
	public String getKeySecret() {
		return keysecret;
	}
	public void setKeySecret(String keysecret) {
		this.keysecret = keysecret;
	}
	public String getURI() {
		return uri;
	}
	public void setURI(String uri) {
		this.uri = uri;
	}
    
}
