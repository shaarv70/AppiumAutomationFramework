package com.saucelabs.tests;


import java.util.Map;

import org.testng.annotations.Test;

import com.saucelabs.pages.BaseTest;
import com.saucelabs.pages.LoginPage;
import com.saucelabs.utils.JsonUtils;
import com.saucelabs.utils.StaticTextUtils;

public final class LoginTests extends BaseTest{
	
	
	
	
	@Test
	public  void invalidUsername() 
	{
		log.info("*****************Starting invalidUsername test**********************");
		new LoginPage().setUsername(JsonUtils.get("invalidUsername","username")).
		    setPassword(JsonUtils.get("invalidUsername","password")).clickLogin().
		    assertLoginNotification(StaticTextUtils.getStaticText("err_invalid_username_password"));
	}

	@Test
	public void invalidPassword() 
	{
		log.info("*****************Starting invalidPassword test**********************");
		new LoginPage().setUsername(JsonUtils.get("invalidPassword","username")).
	    setPassword(JsonUtils.get("invalidPassword","password")).clickLogin().
	    assertLoginNotification(StaticTextUtils.getStaticText("err_invalid_username_password"));
	}
	
	@Test
	public  void validCredentials() 
	{
		log.info("*****************Starting validCredentials test**********************");
		new LoginPage().setUsername(JsonUtils.get("validCredentials","username")).
	    setPassword(JsonUtils.get("validCredentials","password")).clickLogin().
	    assertProductPageTitle().assertPageTitle(StaticTextUtils.getStaticText("product_page_title")).
	    clickMenuIcon().clicklogout();
	
		
	}
	
	

	
	
	
	
	
}
