MIS-5 Create a SVS to Populate the division drop down

Meta:
@issue MIS-5

Narrative:
In order to view my selected division based on my search criteria
As a user
I want to the divison information to be based on user

Scenario: Division Information should be displayed based on Users

Meta:
@id MIS-5-SC01
@acceptance
@automatedBy BH05479_Narendra 
@productName MacysNet
@moduleName Division_Drop_Down - AP forms

Given user <user> on AP query screen to validate the divison with activity <activity>
Then Get the values from Services and Compare the Service values with Database

Examples:
|URL                       |activity         |user   |
|http://qa.macysnet.com/AP/|Status of Invoice|vendor1|