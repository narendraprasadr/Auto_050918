package com.macys.mst.macysnet.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.macys.mst.macysnet.MISObjects.Invoice;
import com.macys.mst.macysnet.MISObjects.InvoicesUI;

import junit.framework.Assert;

public class ExistingUIMethods {
	private static Logger logger = Logger.getLogger(ExistingUIMethods.class.getName());
	public static ArrayList<InvoicesUI> newScreendatasList=new ArrayList<>();
	public static ArrayList<InvoicesUI> existingScreendatasList=new ArrayList<>();
	public static ArrayList<InvoicesUI> serviceList=new ArrayList<>();
	public static ArrayList<InvoicesUI> databaseList=new ArrayList<>();
	
	public static void Validate_Record_Count(WebDriver lclDriver){
		//logger.info("Inside Action --> Validate_Record_Count  ");
		
		try {
			if(records_On_Page(lclDriver,"documentsCount")>0)
				{
				//logger.info("Inside Action --> Records are displayed  ");				
			
			System.out.println(lclDriver.findElement(By.xpath(General.get_Locator("statusDetails"))).getText());		
			
			int noOfPages = pages_Count(lclDriver,"totalRecords");
			
			System.out.println(noOfPages);
			lclDriver.findElement(By.xpath("(//th/span)[1]")).click();
			add_Records_On_Page(lclDriver,"new");
			
		if(noOfPages>1) {
			
			int page=2;
			while(page==2) {
				lclDriver.findElement(By.xpath("//main/div[2]/div/div[2]/div/div[2]/div/button["+page+"]")).click();
				add_Records_On_Page(lclDriver,"new");
				page++;
				page++;
			}
			
			for( ;page<=noOfPages+1;page++) {	
				
				Thread.sleep(3000);
				lclDriver.findElement(By.xpath("//main/div[2]/div/div[2]/div/div[2]/div/button["+page+"]")).click();
				add_Records_On_Page(lclDriver,"new");
			}
		}
					
		//getRecordsFromExistingScreen(lclDriver);				
				
				Assert.assertTrue(true);
			}
			else
			{
				logger.info("Inside Action --> NO Records are displayed  ");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		//validateExistingScreenWithNewScreenRecords(existingScreendatasList,newScreendatasList);
	}

	private static void compareExistingScreenWithNewScreenRecords(ArrayList<InvoicesUI> existingScreendatasList2,
			ArrayList<InvoicesUI> newScreendatasList2) {

		
	}

	public static int records_On_Page(WebDriver lclDriver,String parameter) {
		logger.info("Inside Project Action --> records_On_Page ");
		int counts = 0;
		try {
			
			counts = lclDriver.findElements(By.xpath(General.get_Locator(parameter))).size();
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return counts;
	}

	public static int pages_Count(WebDriver lclDriver,String parameter) {
		
		logger.info("Inside Project Action --> pages_Count ");
		String recordsDesc = null;

		try {
			System.out.println(lclDriver.findElement(By.xpath(General.get_Locator(parameter))).getText());
			 recordsDesc = lclDriver.findElement(By.xpath(General.get_Locator(parameter))).getText();		
		//	 records = recordsDesc.substring(Math.max(recordsDesc.length() - 2, 0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		
		int records = Integer.parseInt(recordsDesc.substring(Math.max(recordsDesc.length() - 2, 0)));
		
		if(records%10==0) {
			
			return (records/10);
		}else {
			
			return (records/10+1);
		}		
	}

	public static void add_Records_On_Page(WebDriver lclDriver,String screenType) {
		int columns=0;
		int rows =0;
		
		/*if(sort==1) {
			lclDriver.findElement(By.xpath("(//th/span)[1]")).click();
			sort++;
		}
		*/
		
		if(screenType.equalsIgnoreCase("new")) {
		
			 columns = records_On_Page(lclDriver, "noofColumns");
			 rows = records_On_Page(lclDriver, "documentsCount");
		
		}else if(screenType.equalsIgnoreCase("existing")){
			
		
			 rows = records_On_Page(lclDriver, "recordsCountExisting");
			
		}
		
	int column = 1;
	List<String> bodylist = new ArrayList<String>();
	if(screenType.equalsIgnoreCase("new")) {

	for(int i=1;i<=rows;i++){
		
		
		 
		//InvoicesUI.newScreendatasList.add(new InvoicesUI(lclDriver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+column+"]")).getText().trim(), lclDriver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+(column+2)+"]")).getText().trim(), lclDriver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+(column+3)+"]")).getText().trim(),lclDriver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+(column+5)+"]")).getText().trim()));
	   }
	//newScreendatasList.add(new Invoice(bodylist));
	}else if(screenType.equalsIgnoreCase("existing")){
		for(int i=2;i<=rows;i++){				 
			 
			//Invoices.addValuesExisting(lclDriver.findElement(By.xpath("//table[2][@class='tblResults']/tbody/tr["+i+"]/td["+column+"]")).getText().trim(), lclDriver.findElement(By.xpath("//table[2][@class='tblResults']/tbody/tr["+i+"]/td["+(column+1)+"]")).getText(),lclDriver.findElement(By.xpath("//table[2][@class='tblResults']/tbody/tr["+i+"]/td["+(column+2)+"]")).getText(), lclDriver.findElement(By.xpath("//table[2][@class='tblResults']/tbody/tr["+i+"]/td["+(column+3)+"]")).getText(),lclDriver.findElement(By.xpath("//table[2][@class='tblResults']/tbody/tr["+i+"]/td["+(column+4)+"]")).getText(), lclDriver.findElement(By.xpath("//table[2][@class='tblResults']/tbody/tr["+i+"]/td["+(column+6)+"]")).getText());
		//	InvoicesUI.existingScreendatasList.add(new Invoices(lclDriver.findElement(By.xpath("//table[2][@class='tblResults']/tbody/tr["+i+"]/td["+column+"]")).getText().trim(),lclDriver.findElement(By.xpath("//table[2][@class='tblResults']/tbody/tr["+i+"]/td["+(column+2)+"]")).getText().trim(), lclDriver.findElement(By.xpath("//table[2][@class='tblResults']/tbody/tr["+i+"]/td["+(column+3)+"]")).getText().trim(), lclDriver.findElement(By.xpath("//table[2][@class='tblResults']/tbody/tr["+i+"]/td["+(column+6)+"]")).getText().trim()));  
		   }
		
	}	
	}
}
