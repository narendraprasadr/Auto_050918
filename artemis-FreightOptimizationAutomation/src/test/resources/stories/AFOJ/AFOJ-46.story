AFOJ-46 NonRet - Ability to validate values entered for ALL mandatory fields 

AFOJ-46 story

Meta:
@issue AFOJ-46

Narrative:

In order to ensure that the system has the proper data to create a Non-Retail  shipment

As a MTO colleague managing shipping

I want to the system to validate values entered for ALL mandatory fields on the Non-Retail page

Scenario: Highlight the mandatory fields which are null and give the user a pop up message

Meta:
@acceptance
@id AFOJ-46-SC001
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH0446_Sriram
					 

Given Admin user is on freight movement page
When select Non-Retail radio button
Then user user enter all mandatory fields expect Trailer Class
And click on submit button
Then verify the mandatory fields which are null are highlight with please enter value


Scenario: Validate all fields contain valid values

Meta:
@acceptance
@id AFOJ-46-SC002
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH0446_Sriram
					 
Given Admin user is on freight movement page
When select Non-Retail radio button
Then select Origin address
Then select Destination address
And Verify Origin and Destination Should not be same message is display 


Scenario: Validate all fields are refresh when we rest page

Meta:
@acceptance
@id AFOJ-46-SC003
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH0446_Sriram
					 
Given Admin user is on freight movement page
When select Non-Retail radio button
Then user user enter all mandatory fields 
And refresh Freight Movement Request page
Then validate all the fields are cleared on Non-Retail page