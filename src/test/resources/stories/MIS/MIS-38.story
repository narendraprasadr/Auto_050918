MIS-38 Create dropdown for Account# in submit screen

MIS-38 Create dropdown for Account# in submit screen

Meta:
@issue MIS-38

Narrative:
In order to select the account # I want to view in AP Qry

As a user

I want to a dropdown of account #s available to me

Scenario: Compare the UI Accountnbr with Database
Meta:
@id MIS-38-SC01
@acceptance
@automation
@rest
@productName MacysNet
@moduleName AP
@project MIS
@automatedBy BH07405_Senthilkumar K

Given user on AP query page
When Select activity in AP Query screen
And Select division in AP Query screen
Then Get the accountnumber from AccountNumber dropdown
And Compare the UI Accountnbr with Database

Examples:
|URL                                                   |inputs |activity     |division|
|http://dev.macysnet.com/AccountsPayable/?UserID=b051UXG#/submit|B051UXG|Trial Balance|Macy's  |
