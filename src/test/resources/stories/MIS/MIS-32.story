MIS-32 Check Detail search  

Meta:
@issue MIS-32

Narrative:
In order to display the filtered Check Detail screen
As a user 
I want to AP Query screen updated for Check Detail activity. Required search criteria when check details selected are Division, Account # and Check #, Submit shall display the Check Details screen with filtered data based on search criteria

Scenario:Validate search options for Division, Account Number, Check Number are displayed for Check Detail Activity
Meta:
@id MIS-32-SC001
@acceptance
@automation
@selenium
@automatedBy B005725_Chenna
@regression
@ProductName MacysNet
@ModuleName AP

Given vendor user is on <URL> AccountsPayable screen
Then AP Query option is available for the <user> 
Then user clicks on AP Query option
Then AP Query Screen has displayed 
Then Activity widget <Activities> has displayed on AP Query Screen
When user selects Check Detail option from Activity
Then validate Division, Account Number, Check Number widgets are displayed
Examples:
|URL                                |Activities                                                                                            |user  |
|http://ma001xsweb95/accountspayable|Chargebacks and RTVs,Check Detail,Status of Invoice,Trial Balance,Last 2 Checks,Vendor Integrity Audit|vendor|


Scenario:Validate Check Detail screen widgets only.
Meta:
@id MIS-32-SC002
@acceptance
@automation
@selenium
@automatedBy B005725_Chenna
@regression
@productName MacysNet
@moduleName AP

Given user on AP query screen to validate the <activity> for <division> division
When user enter the Account no <accountNo>, check no <checkNo> and click on submit button
Then validate the results of Check Detail screen

Examples:
|URL                                |activity    |division|accountNo|checkNo|
|http://ma001xsweb95/accountspayable|Check Detail|Macy's  |910927390|1662562|