package com.mgmtp.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mgmtp.blog.service.UserService;

@Component
public class ApplicationStartup 
implements ApplicationListener<ApplicationReadyEvent> {
	
	@Autowired
	private UserService userService;
	
  /**
   * This event is executed as late as conceivably possible to indicate that 
   * the application is ready to service requests.
   */
  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
	  userService.resetAllPassword();
	  return;
  }
 
} // class