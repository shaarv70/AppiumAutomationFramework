package com.saucelabs.pages;

import org.openqa.selenium.WebElement;

import com.saucelabs.enums.WaitStrategy;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class SettingsPage extends BasePage {
	
	
	@AndroidFindBy(accessibility = "test-LOGOUT")
	private WebElement logoutBtn;
	
	
	
	public LoginPage clicklogout()
	{
		click(WaitStrategy.NONE,logoutBtn);
		return new LoginPage();
	}
	
	
	

}
