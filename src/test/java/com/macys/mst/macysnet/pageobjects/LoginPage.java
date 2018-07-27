package com.macys.mst.macysnet.pageobjects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.macys.mst.artemis.config.FileConfig;
import com.macys.mst.artemis.reports.StepDetail;
import com.macys.mst.artemis.selenium.PageObject;
import com.macys.mst.artemis.selenium.SeUiContextBase;
import com.macys.mst.macysnet.config.Constants;
//import com.macys.mst.FreightOptimization.stepdefs.VPCMSteps;
import com.macys.mst.macysnet.utils.General;

public class LoginPage extends PageObject {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	SeUiContextBase objSeUiContextBase=new SeUiContextBase();
public static String userType=null;
public static String userId=null;

	@FindBy(name = "UserName")
	public WebElement txtUsername;

	@FindBy(name = "Password")
	public WebElement txtPassword;

	@FindBy(xpath = "//span[@class='menuheading']/following-sibling::a[1][contains(text(),'Vendor Lookup')]")
	public WebElement lnkVendorLookup;
	@FindBy(name = "txtVendorName")
	public WebElement txtVendorName;
	@FindBy(name = "btnSubmit")
	public WebElement btnSubmit;
	@FindBy(name = "btnNext")
	public WebElement btnNext;

	@FindBy(name = "rdSRR")
	public WebElement rdSRR;

	@FindBy(xpath = "//tr/td/a")
	public WebElement lnkAccountNumber;

	@FindBy(name = "txtBOL")
	public WebElement txtBOL;

	@FindBy(name = "grdAddress$ctl03$btnS")
	public WebElement btnS;

	@FindBy(name = "submit")
	public WebElement btnLogin;

	@FindBy(xpath = ".//*[@id='toast-container']/div/div[2]/span/span")
	public WebElement errMsgLoginPage;


	@FindBy(xpath="//h3")
	public WebElement errorPageHeader;

	//AFOJ33 obj=new AFOJ33();
	public String loginErrorMsg() {

		try {
			General.waitToLoadElement(this.errMsgLoginPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errMsgLoginPage.getText();
	}

	public void openBrowser(String usertype) {

try {

			SeUiContextBase.gotourl(General.driver, Constants.loginUrl);
			SeUiContextBase.Maximize_Browser_Window(General.driver);
			StepDetail.addDetail("MacysNET login page is displayed", true);
			assertTrue("MacysNET login page", true);
			userType=usertype;

			if (userType.equals("Admin")) {
				userId="roint3";//FileConfig.getInstance().getStringConfigValue("MacysNet.Admin.userId");
			} else {
				userId=FileConfig.getInstance().getStringConfigValue("cyberark.userIDOther");
			}


         /*   String cyberarksafe=FileConfig.getInstance().getStringConfigValue("cyberark.safe");
            String cyberarkappid=FileConfig.getInstance().getStringConfigValue("cyberark.appid");
           // String passwordobj=FileConfig.getInstance().getStringConfigValue("MacysNet.Admin.pwdobjectid");
            String passwordobj = FileConfig.getInstance().getStringConfigValue("MacysNet.Admin.pwdobjectid");
            String password=GetPasswordCyberArk.getpassword(cyberarksafe,cyberarkappid, passwordobj);*/

			Thread.sleep(2000);
			SeUiContextBase.sendkeys(this.txtUsername, "roint3");
			assertTrue("MTO Username entered successfully", true);
			SeUiContextBase.sendkeys(this.txtPassword, "routing1");
   			assertTrue("Password entered successfully", true);

			SeUiContextBase.Capture_Page_Screenshot(General.driver, Constants.scnshotPassPath +"LoginScreen"+General.getTimeStamp()+".jpg");
			objSeUiContextBase.clickElement(this.btnLogin);

			StepDetail.addDetail("username" + ":" + "Password", true);
			assertTrue("submitted successfully", true);

		} catch (Exception e) {

			assertFalse("Failed To login", true);
			SeUiContextBase.Capture_Page_Screenshot(General.driver, Constants.scnshotFailPath +"LoginScreen"+General.getTimeStamp()+".jpg");

		}




	}

}
