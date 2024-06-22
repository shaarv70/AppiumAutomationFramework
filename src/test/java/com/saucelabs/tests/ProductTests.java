package com.saucelabs.tests;

import org.testng.annotations.Test;

import com.saucelabs.pages.LoginPage;
import com.saucelabs.pages.ProductsPage;
import com.saucelabs.utils.JsonUtils;
import com.saucelabs.utils.StaticTextUtils;

public class ProductTests extends BaseTest {

	
	
     @Test
	public void validateProductonProductsPage()
	{
	 new LoginPage().login(JsonUtils.get("validCredentials","username"), JsonUtils.get("validCredentials","password")).
		 assertProductName(StaticTextUtils.getStaticText("product_name")).
		 assertProductPrice(StaticTextUtils.getStaticText("product_price")).
		 clickMenuIcon().clicklogout();
		
		
	}
	
	
	@Test
	public void validateProductonProductDetailsPage() {
	
        new LoginPage().login(JsonUtils.get("validCredentials","username"), JsonUtils.get("validCredentials","password")).
		    clickProduct().assertProductNameonDetailsPage(StaticTextUtils.getStaticText("product_name")).
		    assertProductDescription(StaticTextUtils.getStaticText("product_desc")).
		    assertProductPrice(StaticTextUtils.getStaticText("product_price")).
		    clickMenuIcon().clicklogout();
		
       
	}

}