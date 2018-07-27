AFOJE2E story
Scenario:  Validate the end to end flow integration with JDA

Meta:
@acceptance
@id AFOJE2E-SC001
@productName Macy'sNET
@mduleName MnetJDAIntegration
@automatedBy BH05412_Praveenkumar

Given Login to MacysNet application as Admin user
When I select the new link Vendor Lookup
Then enter vendor name kayser
Then click submit button
When click account number
Then validate shipment
Then validate extr_ts from Integration table where extr_key is exist
And validate fb_nbr from FB_PO_SHPMT table where fb_nbr is exist
Then validate the order in JDA				 
Then Enter Transportation Smartbench in JDA
Then Search the Order shipment
Then Click on Create Freight movements for CarriertoDC
Then validate appt_nbr from FB table where fb_nbr is exist 
Given Launch FLO Application
Then Enter shipment 1808930 and search
Then validate shipment details page
Then close Logportal application

