AFOJ-37 DC2DC Ability to capture Driver Notes

Meta:
@issue AFOJ-37

Narrative:

In order to communicate Driver Notes to the driver
As a MTO colleague managing shipping
I want to the ability to capture Driver Notes

Scenario: Validate text box called Driver Notes that allows the user to input data

Meta:
@acceptance
@automation
@id AFOJ-37-SC01
@productName MacysNET
@moduleName Additional Freight Optimization
@automatedBy BH0446_Sriram
					 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
And select DCTODC radio button
Then validate a free form text box called Driver Notes that allows the user to input data
And verify the maximum text length of characters accepted is 128.
Examples:
|InputText                                                                                                                                     |AcceptedText                                                                                                                    |MacysNet_URL               |
|WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW               |WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW |http://dev.macysnet.com/AP/|
|                                                                                                                                              |                                                                                                                                |
|A                                                                                                                                             |A                                                                                                                               |
|WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW|WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW|
|WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW W               |WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW W |

Scenario: Validate text box called Driver Notes that allows the user to input data

Meta:
@acceptance
@automation
@id AFOJ-37-SC02
@productName MacysNET
@moduleName Additional Freight Optimization
@automatedBy BH0446_Sriram
					 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
And select DCTODC radio button
Then validate a free form text box called Driver Notes that allows the user to input data
And verify the maximum text length of characters accepted is 127.
Examples:
|InputText                                                                                                                      |MacysNet_URL               |
|WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW|http://dev.macysnet.com/AP/|

