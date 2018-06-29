AFOJ-32 DC2DC Create the DC to DC page and capture minimum required information

Meta:
@issue AFOJ-32

Narrative:

In order to have the required information needed to route DC to DC shipments and integrate my request into our JDA TMS
As a MTO colleague managing shipping
I want to the ability to capture minimum required data Origin Address Elements (internal ship from address; populated from table in FLO that provides DC address)/Destination Address elements (external ship to address)/Freight Type/ Piece count/Weight/Cubic Feet/ Pick Up Date/Estimated Pick Up Time/Time Zone/Driver Notes free form text box (optional)

Scenario:Retrieve data for all the fields in the DC to DC page

Meta:
@acceptance
@id AFOJ-32-SC014
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH05412_Praveenkumar
					 
Given user is on page Freight Movement Request
When select DCTODC radio button
Then validate the below fields are mandatory in DC To DC Pick Up Request page:
|MandatoryFields|
|Origin         |
|Destination    |
|Name           |
|Address        |
|City           |
|State          |
|Zip            |
|Freight Type   |
|Piece Count    |
|Weight (lbs)   |
|Cubic Feet     |
|Pick Up Date   |
|Time           |
|Time Zone      |
|Carrier Type   |
|Trailer Class  |
And verify the Optional fields in DC To DC Pick Up Request page:
|OptionalFields|
|Trailer Number|
|SCAC          |
|Pallets       |
|Units/Cartons |
|Driver Notes  |
Then Mandatory field Frieght type is defaulted to Processed Freight
Examples:
|FieldName   |DefaultValue     |
|Frieght typ |Processed Freight|
|Pick Up Date|Current Date     |
|Pick Up Time|12:00 AM         |