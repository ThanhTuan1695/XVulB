package com.mgmtp.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mgmtp.blog.service.UserService;
import com.mgmtp.blog.setting.SecuritySettings;

@Component
public class ApplicationStartup 
implements ApplicationListener<ApplicationReadyEvent> {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	SecuritySettings securitySettings;
	
  /**
   * This event is executed as late as conceivably possible to indicate that 
   * the application is ready to service requests.
   */
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		switch (securitySettings.getResetPassword()) {
			case True:
				userService.resetAllPassword();
				break;
			case False:
				break;
		}
		return;
	}
 
} 