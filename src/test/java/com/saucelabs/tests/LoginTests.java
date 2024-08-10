package com.saucelabs.tests;


import static com.saucelabs.utils.LoggingUtils.log;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.saucelabs.annotations.FrameworkAnnotation;
import com.saucelabs.pages.LoginPage;
import com.saucelabs.utils.JsonUtils;
import com.saucelabs.utils.StaticTextUtils;

public final class LoginTests extends BaseTest{
	
	
	 private LoginTests() {}
	
	@FrameworkAnnotation(author = {"Arvind"})
	@Test(groups={"WithoutDeepLinks"})
	public  void invalidUsername(Method m) 
	{
	   log().info("*****************Starting invalidUsername test**********************");
	   try { 
	   new LoginPage().setUsername(JsonUtils.get("invalidUsername","username")).
		    setPassword(JsonUtils.get("invalidUsername","password")).clickLogin().
		    assertLoginNotification(StaticTextUtils.getStaticText("err_invalid_username_password"));
	      }
	   catch(Exception e)
	   {
		   log().info(e.getMessage());
		   log().error("Test "+m.getName()+" failed");
		  
		   
	   }
	   }

	@FrameworkAnnotation(author = {"Harish"})
	@Test(groups= {"WithoutDeepLinks"})
	public void invalidPassword(Method m) 
	{
		log().info("*****************Starting invalidPassword test**********************");
		try {
		new LoginPage().setUsername(JsonUtils.get("invalidPassword","username")).
	    setPassword(JsonUtils.get("invalidPassword","password")).clickLogin().
	    assertLoginNotification(StaticTextUtils.getStaticText("err_invalid_username_password"));
		}
		catch(Exception e)
		{
			log().info(e.getMessage());
			log().error("Test "+m.getName()+" failed");
			
		}
		
		
		}
	
	@FrameworkAnnotation(author = {"Arvind"})
	@Test(groups= {"WithoutDeepLinks"})
	public  void validCredentials(Method m) 
	{
		log().info("*****************Starting validCredentials test**********************");
		
		try {
		new LoginPage().setUsername(JsonUtils.get("validCredentials","username")).
	    setPassword(JsonUtils.get("validCredentials","password")).clickLogin().
	    assertProductPageTitle().assertPageTitle(StaticTextUtils.getStaticText("product_page_title")).
	    clickMenuIcon().clicklogout();
		}
		catch(Exception e)
		{
			log().info(e.getMessage());
			log().error("Test "+m.getName()+" failed");
			
		}
    		
	}
	
	

	
	
	
	
	
}
