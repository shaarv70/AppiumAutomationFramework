package com.saucelabs.pages;

import org.openqa.selenium.WebElement;

import com.saucelabs.enums.WaitStrategy;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends MenuBar {

	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"PRODUCTS\"]")
	 private WebElement productsPageTitle;
	
	@AndroidFindBy(xpath="//*[@text=\"Sauce Labs Backpack\"]")
	private WebElement productName;
	
	@AndroidFindBy(xpath="//*[@text=\"$29.99\"]")
	private WebElement productPrice;
	
	
	public ProductsPage assertPageTitle(String exp)
	{
	   assertMess(productsPageTitle.getText(), exp);
	   return this;
	}
	
	public ProductsPage assertProductName(String name)
	{
		assertMess(productName.getText(), name);
		return this;
	}
	
	public ProductsPage assertProductPrice(String price)
	{
		assertMess(productPrice.getText(),price);
		return this;
		
	}
	
	public ProductDetailPage clickProduct()
	{
		click(WaitStrategy.NONE,productName);
		return new ProductDetailPage();
		
	}
	
	
	
	
	
}
