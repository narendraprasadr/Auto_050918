package com.macys.mst.macysnet.MISObjects;

import java.util.ArrayList;
import java.util.List;

public class LastTwoChecks {
	public static ArrayList<LastTwoChecks> newScreendatasList=new ArrayList<>();
	public static ArrayList<LastTwoChecks> existingScreendatasList=new ArrayList<>();
	

	public  String checkNumber;
	public  String checkDate;
	public  String divisionalContribution;
	public  String checkAmount;

	  
	  
	    
	   public LastTwoChecks(List<String> bodylist) {

				if (bodylist.size() == 4) {
		     
	         this.checkNumber = bodylist.get(0).toString();;
	         this.checkDate = bodylist.get(1).toString();;
	         this.divisionalContribution = bodylist.get(2).toString();;
	         this.checkAmount = bodylist.get(3).toString();;
				} 
	         
	    }
	
	    public static void printValues(ArrayList<LastTwoChecks> list)
	    {	        	 
	        for (int i = 0; i < list.size(); i++)
	        {      
	        	LastTwoChecks data = list.get(i);	 
	          
	        System.out.println(data.checkNumber+"      "+data.checkDate+"    "+data.divisionalContribution+"      "+data.checkAmount);
	            	            
	        }
	        
	    }


		public String getCheckNumber() {
			return checkNumber;
		}


		public void setCheckNumber(String checkNumber) {
			this.checkNumber = checkNumber;
		}


		public String getCheckDate() {
			return checkDate;
		}


		public void setCheckDate(String checkDate) {
			this.checkDate = checkDate;
		}


		public String getDivisionalContribution() {
			return divisionalContribution;
		}


		public void setDivisionalContribution(String divisionalContribution) {
			this.divisionalContribution = divisionalContribution;
		}


		public String getCheckAmount() {
			return checkAmount;
		}


		public void setCheckAmount(String checkAmount) {
			this.checkAmount = checkAmount;
		}
	   

}
