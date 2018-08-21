MIS-10 Status of Invoices - Create service to allow user to pull invoice information based on PO

MIS-10 Status of Invoices - Create service to allow user to pull invoice information based on PO

Meta:
@issue MIS-10

Narrative:
In order to view invoices tied to a specific purchase order
As a user
I want to the ability to add a purchase order to my search criteria in Status of Invoices,Invoice and PO cannot both be entered and are mutually exclusive


Scenario:REST service to pull invoice information based on the PO
Meta:
@id MIS-10-SC01
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
|serviceUrl                                                          |returnCode|inputs               |values                                                                     |POs|
|http://lp000xstrs0002:8360/api/macysnet/v1/vendorAP/VendorInvoicesPO|200       |/71/910927390/2914988|invoiceNbr,invoiceStatus,desc,checkNbr,checkDate,transDueDate,transGrossAmt|PO |