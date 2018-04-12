package com.mgmtp.blog.setting;

public class SecurityEnum {
	
	public static enum PwBruteForce { Captcha, Userlockout, False };
	
	public static enum SessionFixation { True, False };
	
	public static enum SqlInjection { True, False };
	
	public static enum PasswordStorage { Clear, Hashed, SaltHashed, PBKDF2 };
	
	public static enum ResetPassword { True, False };
	
	public static enum SetCookie { True, False };
	
	public static enum XSSPrevention { True, False };
	
	public static enum CSRFProtection { Token, Samesite, Both, False };
	
}


