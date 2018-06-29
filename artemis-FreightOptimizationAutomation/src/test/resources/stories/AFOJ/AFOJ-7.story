AFOJ-7 story
Scenario: (4)  Enter data for all the fields in the CRC/RTV page

Meta:
@acceptance
@id AFOJ-7-SC004
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH05412_Praveenkumar
					 
Scenario: 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
When select CRC/RTV radio button
Then enter data for all the fields in CRC/RTV page