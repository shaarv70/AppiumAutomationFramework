package com.saucelabs.pages;

import org.openqa.selenium.WebElement;

import com.saucelabs.enums.WaitStrategy;

import io.appium.java_client.pagefactory.AndroidFindBy;

public final class ProductDetailPage extends MenuBar {
	
	
	@AndroidFindBy(xpath="//*[@text=\"Sauce Labs Backpack\"]")
	private WebElement productName;
	
	@AndroidFindBy(xpath="//*[@text=\"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.\"]")
	private WebElement productDesc;
	
	@AndroidFindBy(accessibility = "test-Price")
	private WebElement productPrice;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"ADD TO CART\"]")
	public WebElement addTOCart;
	
	
	public ProductDetailPage assertProductNameonDetailsPage(String name)
	{
		assertFunction(getText(WaitStrategy.VISIBILITY,productName), name);
		return this;
	}
	
	public ProductDetailPage assertProductDescription(String desc)
	{
		assertFunction(getText(WaitStrategy.VISIBILITY,productDesc),desc);
		return this;
		
	}
	
	public ProductDetailPage assertProductPrice(String price)
	{
		scroll("ADD TO CART","Product Price");
		assertFunction(getText(WaitStrategy.VISIBILITY,productPrice),price);
		return this;
		
	}
	
	
	
	
	
	

}
