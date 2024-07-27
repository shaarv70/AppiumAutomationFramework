package com.saucelabs.tests;

import static com.saucelabs.utils.LoggingUtils.log;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.saucelabs.annotations.FrameworkAnnotation;
import com.saucelabs.pages.ProductDetailPage;
import com.saucelabs.pages.ProductsPage;
import com.saucelabs.utils.StaticTextUtils;

public final class ProductTests extends BaseTest {


	private ProductTests() {}


	@FrameworkAnnotation(author = {"Harish"})
	@Test(groups ="DeepLinks")
	public  void validateProductonProductsPage(Method m)
	{
		log().info("*****************Starting validateProductonProductsPage test**********************");
		try {
			new ProductsPage().
			assertProductName(StaticTextUtils.getStaticText("product_name")).
			assertProductPrice(StaticTextUtils.getStaticText("product_price")).
			clickMenuIcon().clicklogout();
		}
		catch(Exception e)
		{
			log().error("Test "+m.getName()+" failed");
			log().info(e.getStackTrace().toString());
		}

	}

	@FrameworkAnnotation(author = {"Arvind"})
	@Test(groups ="DeepLinks") 
	public  void validateProductonProductDetailsPage(Method m) {

		try {
			log().info("*****************Starting validateProductonProductDetailsPage test**********************");
			
			new ProductsPage().clickProduct().assertProductNameonDetailsPage(StaticTextUtils.getStaticText("product_name")).
			assertProductDescription(StaticTextUtils.getStaticText("product_desc")).
			assertProductPrice(StaticTextUtils.getStaticText("product_price")).
			clickMenuIcon().clicklogout();
		}
		catch(Exception e)
		{
			log().error("Test "+m.getName()+" failed");
			log().info(e.getStackTrace().toString());
		}

	}

}