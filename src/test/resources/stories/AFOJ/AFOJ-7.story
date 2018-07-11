AFOJ-7 CRC-  Create the CRC/RTV page and capture minimum required information

Meta:
@issue AFOJ-7

Narrative:

In order to have the required information needed to route CRC outbound shipments and integrate my request into our JDA TMS

As a MTO colleague managing shipping

I want to the ability to capture minimum required data

Scenario: (1) Enter data for all the fields in the CRC/RTV page

Meta:
@acceptance
@id AFOJ-7-SC001
@productName Macy'sNET
@moduleName FreightMovementRequest
@automatedBy 
					 

Given Admin user is on freight movement page
When Admin user selects CRC/RTV radio button
Then validate the MandatoryFields in CRC/RTV Pick Up Request page
Then validate the OptionalFields in CRC/RTV Pick Up Request page
Then validate fields DefaultValue