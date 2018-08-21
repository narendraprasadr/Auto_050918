MIS-28 Status of Invoice search

MIS-28 Status of Invoice search

Meta:
@issue MIS-28

Narrative:
In order to display the filtered Status of Invoice screen
As a user
I want to the AP Query submit screen updated for the Status of Invoice activity Required search criteria when Status of Invoice is selected are Division, Account  and Invoice  OR PO Submit shall display the Status of Invoice screen with filtered data based on search criteria Note - Current production Status of Invoice and Status of Invoice by PO are being combined under 1 screen

Scenario: (1) Validate all the available widgets of Status of Invoice screen
Meta:
@id MIS-28-SC001
@automated_by BH07405_Senthilkumar K
@regression
@ProductName Macy'sNet
@ModuleName AP forms

Given vendor user is on <URL> AccountsPayable screen
Then AP Query option is available for the <user>
Then user clicks on AP Query option
Then AP Query Screen has displayed
Then Activity widget <Activities> has displayed on AP Query Screen
When user selects Status of Invoice option from Activity
Then validate Division, Account Number, Invoice Number widgets are displayed

Examples:
|URL                                |Activities                                                                                            |user  |
|http://ma001xsweb95/accountspayable|Chargebacks and RTVs,Check Detail,Status of Invoice,Trial Balance,Last 2 Checks,Vendor Integrity Audit|vendor|

Scenario: (2) Status of Invoice search
Meta:
@id MIS-28-SC002
@acceptance
@automatedBy B005725_Chenna 
@productName MacysNet
@moduleName AP forms

Given user on AP query screen to validate the <activity> for <division> division
When user enter the Account no <accountNo>, Invoice no <invoiceNo> and click on submit button
Then validate the results of Invoice Status

Examples:
|URL                                |activity         |division|accountNo|invoiceNo |
|http://ma001xsweb95/accountspayable|Status of Invoice|Macy's  |910927390|9027307930|