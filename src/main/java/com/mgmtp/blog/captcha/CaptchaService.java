package com.mgmtp.blog.captcha;


public interface CaptchaService {
    String getReCaptchaKeySite();
    String getReCaptchaKeySecret();
    boolean verifyResponse(String g_recaptcha_response);

}
