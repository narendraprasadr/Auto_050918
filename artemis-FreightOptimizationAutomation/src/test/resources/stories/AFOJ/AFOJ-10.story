AFOJ-10 story
Scenario: (12) Validate text box called Driver Notes that allows the user to input data when selecting the RTV radio button

Meta:
@acceptance
@id AFOJ-10-SC012
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH05412_Praveenkumar
					 
Scenario: 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
When select CRC/RTV radio button
Then provide a free form text box called Driver Notes that allows the user to input data