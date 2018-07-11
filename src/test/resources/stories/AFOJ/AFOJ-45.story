AFOJ-45 DC2DC Ability to validate values entered for ALL mandatory fields 

AFOJ-45 story

Meta:
@issue AFOJ-45

Narrative:

In order to ensure that the system has the proper data to create a DC to DC shipment

As a MTO colleague managing shipping

I want to the system to validate values entered for ALL mandatory fields on the DC to DC page

Scenario: Highlight the mandatory fields which are null and give the user a pop up message

Meta:
@acceptance
@id AFOJ-45-SC018
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH0446_Sriram
					 

Given Admin user is on freight movement page
When select DC to DC radio button
Then user user enter all mandatory fields expect Trailer Class
And click on submit button
Then verify the mandatory fields which are null are highlight with please enter value


Scenario: Validate all fields contain valid values

Meta:
@acceptance
@id AFOJ-45-SC019
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH0446_Sriram
					 
Given Admin user is on freight movement page
When select DC to DC radio button
Then select Origin address
Then select Destination address
And Verify Origin and Destination Should not be same message is display 


Scenario: Validate all fields are refresh when we rest page

Meta:
@acceptance
@id AFOJ-45-SC027
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH0446_Sriram
					 
Given Admin user is on freight movement page
When select DC to DC radio button
Then user user enter all mandatory fields 
And refresh Freight Movement Request page
Then validate all the fields are cleared on DCTODC page
