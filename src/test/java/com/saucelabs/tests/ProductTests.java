package com.saucelabs.tests;

import static com.saucelabs.utils.LoggingUtils.log;

import org.testng.annotations.Test;

import com.saucelabs.annotations.FrameworkAnnotation;
import com.saucelabs.pages.LoginPage;
import com.saucelabs.utils.JsonUtils;
import com.saucelabs.utils.StaticTextUtils;

public final class ProductTests extends BaseTest {


	@FrameworkAnnotation(author = {"Harish"})
	@Test
	public  void validateProductonProductsPage()
	{
		log().info("*****************Starting validateProductonProductsPage test**********************");
		new LoginPage().login(JsonUtils.get("validCredentials","username"), JsonUtils.get("validCredentials","password")).
		assertProductName(StaticTextUtils.getStaticText("product_name")).
		assertProductPrice(StaticTextUtils.getStaticText("product_price")).
		clickMenuIcon().clicklogout();


	}

	@FrameworkAnnotation(author = {"Arvind"})
	@Test
	public  void validateProductonProductDetailsPage() {

		log().info("*****************Starting validateProductonProductDetailsPage test**********************");
		new LoginPage().login(JsonUtils.get("validCredentials","username"), JsonUtils.get("validCredentials","password")).
		clickProduct().assertProductNameonDetailsPage(StaticTextUtils.getStaticText("product_name")).
		assertProductDescription(StaticTextUtils.getStaticText("product_desc")).
		assertProductPrice(StaticTextUtils.getStaticText("product_price")).
		clickMenuIcon().clicklogout();


	}

}