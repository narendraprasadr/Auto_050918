AFOJ-7 CRC-  Create the CRC/RTV page and capture minimum required information

Meta:
@issue AFOJ-7

Narrative:

In order to have the required information needed to route CRC outbound shipments and integrate my request into our JDA TMS

As a MTO colleague managing shipping

I want to the ability to capture minimum required data

Scenario: (4) Enter data for all the fields in the CRC/RTV page

Meta:
@acceptance
@id AFOJ-7-SC004
@productName Macy'sNET
@moduleName FreightMovementRequest
@automatedBy 
					 

Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
When select CRC/RTV radio button
Then enter data for all the fields in CRC/RTV page