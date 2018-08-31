package com.macys.mst.macysnet.MISObjects;
import java.util.ArrayList;
import java.util.List;

public class TrialBalance {
		
		public static ArrayList<TrialBalance> newScreendatasList=new ArrayList<>();
		public static ArrayList<TrialBalance> existingScreendatasList=new ArrayList<>();
		

		public  String documentNumber;
		public  String description;
		public  String poNumber;
		public  String dueDate;
		public  String amount;
		  
		    
		    public TrialBalance(List<String> bodylist) {

				if (bodylist.size() == 5) {
					
				    this.documentNumber = bodylist.get(0).toString();
			         this.description = bodylist.get(1).toString();
			         this.poNumber = bodylist.get(2).toString();
			         this.dueDate = bodylist.get(3).toString();	          
			         this.amount = bodylist.get(4).toString();
				}
		    }
				
		
		    public String getDocumentNumber() {
				return documentNumber;
			}


			public void setDocumentNumber(String documentNumber) {
				this.documentNumber = documentNumber;
			}


			public String getDescription() {
				return description;
			}


			public void setDescription(String description) {
				this.description = description;
			}


			public String getPoNumber() {
				return poNumber;
			}


			public void setPoNumber(String poNumber) {
				this.poNumber = poNumber;
			}


			public String getDueDate() {
				return dueDate;
			}


			public void setDueDate(String dueDate) {
				this.dueDate = dueDate;
			}


			public String getAmount() {
				return amount;
			}


			public void setAmount(String amount) {
				this.amount = amount;
			}


			public static void printValues(ArrayList<TrialBalance> list)
		    {	        	 
		        for (int i = 0; i < list.size(); i++)
		        {      
		        	TrialBalance data = list.get(i);	 
		          
		        System.out.println(data.documentNumber+"      "+data.description+"    "+data.poNumber+"      "+data.dueDate+"      "+data.amount);
		            	            
		        }
		        
		    }

			
	}

	


