MIS-8 Status of Invoices - Create service for Status of Invoices data retrieval

Meta:
@issue MIS-8

Narrative:
In order to pull a range of 50 invoices when a user enters an Invoice #
As a system
I want to pull max of invoices based on qty and not by invoice #. Invoice #s may not be consecutive and Invoices shall be pulled based on last 50 for the specified Divison/Account selected

Scenario:REST service to retrieve last 50 Invoices matching on Account # and Document #
Meta:
@id MIS-8-SC01
@acceptance
@automation
@rest
@productName MacysNet
@moduleName AP
@project MIS
@automatedBy BH07405_Senthilkumar K

Given REST service return code 200  					
Then Get the values from Service and Database			
And Compare the Service values with Database
Examples:
|serviceUrl                                                        |returnCode|inputs                  |values                                                                                                  |
|http://lp000xstrs0002:8360/api/macysnet/v1/vendorAP/VendorInvoices|200       |/71/910927390/9027304930|div,dunsNbr,dunsSuf,invoiceNbr,transNbr,transSeq,tranCode,desc,poNbr,checkNbr,transDueDate,transGrossAmt|
