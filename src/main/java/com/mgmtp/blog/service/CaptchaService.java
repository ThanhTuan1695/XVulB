package com.mgmtp.blog.service;


public interface CaptchaService {
    String getReCaptchaKeySite();
    String getReCaptchaKeySecret();
    boolean verifyResponse(String g_recaptcha_response);

}
