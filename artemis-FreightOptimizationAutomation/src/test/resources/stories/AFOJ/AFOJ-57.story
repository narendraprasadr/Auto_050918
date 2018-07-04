AFOJ-57 DC2DC "SUBMIT" button functionality

Meta:
@issue AFOJ-57

Narrative:

In order to understand that a user has successfully submitted a request
As a MTO colleague managing shipping
I want to the system to generate a request number and clear all fields once a request has been successfully submitted

Scenario: Validate request number once request has been submitted successfully

Meta:
@acceptance
@id AFOJ-57-SC020
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH05412_Praveenkumar

Given Admin user is on freight movement page
When select DCTODC radio button
Then user enter all mandatory fields
Then click on submit button
Then validate all the fields are cleared on DCTODC page


Scenario: Validate Invalid SCAC Code error message

Meta:
@acceptance
@id AFOJ-57-SC021
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH05412_Praveenkumar

Given Admin user is on freight movement page
When select DCTODC radio button
Then user enter all mandatory fields 
Then user enter invalid SCAC Code
Then click on submit button
Then validate the service with params