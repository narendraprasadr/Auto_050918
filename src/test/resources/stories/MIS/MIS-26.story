MIS-26 Upgrade UI for Trial Balance screen in MNET AP

Meta:
@issue MIS-26

Narrative:
In order to view an updated Trial Balance screen 
As a user 
I want to have the technology behind the UI upgraded Search requirements to retrieve the data in AP shall remain the same as division and Account #

Scenario:Trail Balance search
Meta:
@id MIS-26-SC001
@acceptance
@automatedBy BH07405_Senthilkumar K
@productName MacysNet
@moduleName AP forms

Given user on AP query screen to validate the <activity> for <division> division
When user enter the Account no <accountNo> and click on submit button
Then validate the results of <activity>

Examples:
|URL                                |activity     |division|accountNo|
|http://ma001xsweb95/accountspayable|Trial Balance|Macy's  |910927390|