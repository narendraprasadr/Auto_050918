MIS-3 Create button in MNET for Accounts Payable to open new tab

MIS-3 Create button in MNET for Accounts Payable to open new tab

Meta:
@issue MIS-3

Narrative:
In order to open a new tab for Accounts Payable within MNET

As a user

I want to a button available within the MNET home screen for AP

Scenario: Create button in MNET for Accounts Payable to open new tab
Meta:
@id MIS-3-SC01
@acceptance
@automation
@productName MacysNet
@moduleName AP
@project MIS
@automatedBy BH07405_Senthilkumar K

Given vendor user is on <URL> AccountsPayable screen
Then AP Query screen is available in a new tab

Examples:
|URL                     |
|http://dev.macysnet.com/|


Scenario: Validate Accounts Payable link should not be displayed for UserIDs that do NOT have access to Accounts Payable.
Meta:
@id MIS-3-SC02
@acceptance
@automation
@productName MacysNet
@moduleName AP
@project MIS
@automatedBy BH07405_Senthilkumar K

Given Vendor user is on Macysnet application
Then AP Query screen is NOT available for the vendor who doesnot have access

Examples:
|URL                     |UserID      |
|http://dev.macysnet.com/|CKKanuparthi|
