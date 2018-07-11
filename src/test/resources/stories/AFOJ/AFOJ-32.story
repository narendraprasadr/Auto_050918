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

Given Admin user is on freight movement page
When Admin user selects DC to DC radio button
Then validate the MandatoryFields in DC To DC Pick Up Request page
Then validate the OptionalFields in DC To DC Pick Up Request page
Then validate fields DefaultValue
