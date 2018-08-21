MIS-21 Upgrade UI for Status of Invoice screen in MNET AP

MIS-21 Status of Invoice search

Meta:
@issue MIS-21

Narrative:
In order to view an updated Status of Invoice screen

As a user

I want to the technology behind the UI upgraded Search requirements to retrieve the data in AP shall be Division, Account #, Invoice # or PO#

Scenario: (1) Status of Invoice search
Meta:
@id MIS-21-SC001
@acceptance
@automatedBy BH07405_Senthilkumar K 
@productName MacysNet
@moduleName AP forms

Given user on AP query screen to validate the <activity> for <division> division
When user enter the Account no <accountNo>, Invoice no <invoiceNo> and click on submit button
Then validate the results of Invoice Status

Examples:
|URL                                |activity         |division|accountNo|invoiceNo |
|http://ma001xsweb95/accountspayable|Status of Invoice|Macy's  |910927390|9027307930|