package com.saucelabs.pages;

import org.openqa.selenium.WebElement;

import com.saucelabs.enums.WaitStrategy;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class MenuBar extends BasePage {

	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Menu\"]")
	private WebElement menuIcon;
	
	
	
	
	public SettingsPage clickMenuIcon()
	{
		click(WaitStrategy.NONE,menuIcon,"Hamburger");
		return new SettingsPage();
	}
	
	
	
	
	
	
	
	
	
	
	
}
