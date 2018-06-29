AFOJ-17 ALL Create a new page called Freight Movement Request and add CRC/RTV, DC to DC and Non-Retail radio buttons to the Freight Movement Request screen 

Meta:
@issue AFOJ-17

Narrative:
In order to create specific movements via the new Freight Movement Request screen
As a MTO colleague managing shipping
I want to new page created with the following radio buttons added in the left nav bar under the heading Select Movement Type:CRC/RTV/DC to DC/Non-Retail

Scenario:Validate the objects present in "Freight Movement Request" page for admin user

Meta:
@acceptance
@automation
@endToEnd
@id AFOJ-17-SC001
@productName Macy's NET
@moduleName FreightMovementRequest
@automatedBy BH00446 Sriram
					 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
Then validate that CRC/RTV, DC to DC and Non-Retail radio buttons are displayed

Examples:
|MacysNet_URL               |
|http://dev.macysnet.com/AP/|
