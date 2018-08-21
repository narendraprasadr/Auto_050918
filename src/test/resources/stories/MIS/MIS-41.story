MIS-41 Create a service to get email address for Logged in User

Meta:
@issue MIS-41

Narrative:
In order to view search results from Status of Invoices in my email
As a user
I want to a service created to email the search results when I select the email option

Scenario: REST service to get email address for Logged in User

Meta:
@id MIS-41-SC01
@acceptance
@automation
@rest
@productName MacysNet
@moduleName AP
@project MIS
@automatedBy BH02727_Sivanarumugam M

Given REST service return code 200  					
Then Get the values from Service and Database			
And Compare the Service values with Database
Examples:
|serviceUrl                                                |returnCode|inputs                                     |values|
|http://lp000xstrs0002:8360/api/macysnet/v1/vendorAP/Report|200       |/b051uxg/71/00312463390?invoiceNbr=40148428|email |