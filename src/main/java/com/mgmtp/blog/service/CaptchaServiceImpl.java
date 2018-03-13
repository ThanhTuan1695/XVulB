package com.mgmtp.blog.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mgmtp.blog.service.CaptchaService;
import com.mgmtp.blog.setting.CaptchaSettings;

import java.util.Map;
import java.util.HashMap;


@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Autowired
    private CaptchaSettings captchaSettings;
    
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    
	@Override
	public String getReCaptchaKeySite() {
		return captchaSettings.getKeySite();
	}
	@Override
	public String getReCaptchaKeySecret() {
		return captchaSettings.getKeySecret();
	}
	@Override
	public boolean verifyResponse(String g_recaptcha_response) {

		Map<String, String> body = new HashMap<>();
	    body.put("secret", captchaSettings.getKeySecret());
	    body.put("response", g_recaptcha_response);
	    
	    ResponseEntity<Map> recaptchaResponseEntity = restTemplateBuilder.build()
	    		.postForEntity(captchaSettings.getURI()+"?secret={secret}&response={response}",
	    				body, Map.class, body);
	    
	    Map<String, Object> responseBody = recaptchaResponseEntity.getBody();
	    	System.out.println(responseBody);       
	    	boolean recaptchaSucess = (Boolean)responseBody.get("success");
	    	return recaptchaSucess;
	}
}
