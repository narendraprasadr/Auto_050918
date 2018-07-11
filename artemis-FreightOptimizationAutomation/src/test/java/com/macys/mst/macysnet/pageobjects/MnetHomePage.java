package com.macys.mst.macysnet.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class MnetHomePage extends LoginPage {

	public MnetHomePage(WebDriver driver) {
		super(driver);
	}
	 
	
	@FindBy(xpath="//span[@class='menuheading'][3]/following-sibling::a[2][contains(text(),'Document Library')]/preceding::a[contains(text(),'Freight Movement Request')]")
	public static WebElement freightMovementRequestLink;
	

}
