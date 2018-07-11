package com.macys.mst.macysnet.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.macys.mst.artemis.selenium.PageObject;


public class LoginPage extends PageObject {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	String givenYear=null;
	String lastUpdateBy=null;
	String lastUpdated=null;

	@FindBy(name = "UserName")
	public static WebElement username;

	@FindBy(name = "Password")
	public static  WebElement password;

	@FindBy(name = "submit")
	public static WebElement LoginButton;
	
	
}
