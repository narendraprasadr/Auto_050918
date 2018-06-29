AFOJ-28 story
Scenario: (10)  Retrieve Shipments data in the CRC/RTV page

Meta:
@acceptance
@id AFOJ-28-SC010
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH05412_Praveenkumar
					 
Scenario: 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
When select CRC/RTV radio button
When select the Shipments 
Then retrieve Shipments data