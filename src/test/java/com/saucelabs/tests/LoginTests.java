package com.saucelabs.tests;


import static com.saucelabs.utils.LoggingUtils.log;

import org.testng.annotations.Test;

import com.saucelabs.annotations.FrameworkAnnotation;
import com.saucelabs.pages.LoginPage;
import com.saucelabs.utils.JsonUtils;
import com.saucelabs.utils.StaticTextUtils;

public final class LoginTests extends BaseTest{
	
	
	
	@FrameworkAnnotation(author = {"Arvind"})
	@Test
	public  void invalidUsername() 
	{
	   log().info("*****************Starting invalidUsername test**********************");
		new LoginPage().setUsername(JsonUtils.get("invalidUsername","username")).
		    setPassword(JsonUtils.get("invalidUsername","password")).clickLogin().
		    assertLoginNotification(StaticTextUtils.getStaticText("err_invalid_username_password"));
	}

	@FrameworkAnnotation(author = {"Harish"})
	@Test
	public void invalidPassword() 
	{
		log().info("*****************Starting invalidPassword test**********************");
		new LoginPage().setUsername(JsonUtils.get("invalidPassword","username")).
	    setPassword(JsonUtils.get("invalidPassword","password")).clickLogin().
	    assertLoginNotification(StaticTextUtils.getStaticText("err_invalid_username_password"));
	}
	
	@FrameworkAnnotation(author = {"Arvind"})
	@Test
	public  void validCredentials() 
	{
		log().info("*****************Starting validCredentials test**********************");
		new LoginPage().setUsername(JsonUtils.get("validCredentials","username")).
	    setPassword(JsonUtils.get("validCredentials","password")).clickLogin().
	    assertProductPageTitle().assertPageTitle(StaticTextUtils.getStaticText("product_page_title")).
	    clickMenuIcon().clicklogout();
	
		
	}
	
	

	
	
	
	
	
}
