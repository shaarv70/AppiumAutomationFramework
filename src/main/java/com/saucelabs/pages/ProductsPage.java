package com.saucelabs.pages;

import org.openqa.selenium.WebElement;

import com.saucelabs.enums.WaitStrategy;

import io.appium.java_client.pagefactory.AndroidFindBy;

public final class ProductsPage extends MenuBar {


	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"PRODUCTS\"]")
	private WebElement productsPageTitle;

	@AndroidFindBy(xpath="//*[@text=\"Sauce Labs Backpack\"]")
	private WebElement productName;

	@AndroidFindBy(xpath="//*[@text=\"$29.99\"]")
	private WebElement productPrice;


	public ProductsPage assertPageTitle(String exp)
	{
		assertFunction(getText(WaitStrategy.VISIBILITY,productsPageTitle), exp);
		return this;
	}

	public ProductsPage assertProductName(String name)
	{
		assertFunction(getText(WaitStrategy.VISIBILITY,productName), name);
		return this;
	}

	public ProductsPage assertProductPrice(String price)
	{
		assertFunction(getText(WaitStrategy.VISIBILITY,productPrice),price);
		return this;

	}

	public ProductDetailPage clickProduct()
	{
		click(WaitStrategy.NONE,productName,"Product");
		return new ProductDetailPage();

	}





}
