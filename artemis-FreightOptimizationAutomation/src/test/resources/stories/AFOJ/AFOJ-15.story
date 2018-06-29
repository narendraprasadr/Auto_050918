AFOJ-15 ALL Add a new selection above Document Library called Freight Movement Request

AFOJ-15 ALL Add a new selection above Document Library called Freight Movement Request

Meta:
@issue AFOJ-15

Narrative:
In order to gain access to a new screen called Freight Movement Request
As a MTO collegue managing shipping
I want to see a new link "Request Freight Movement" added above Document Library on MacysNet Home page.

Scenario: Verify new link "Request Freight Movement" is displayed above Document Library on MacysNet Home Page when logged in as Admin user

Meta:
@id AFOJ-15-SC01
@acceptance
@automation
@automated_by
@ProductName MNET
@ModuleName FreightMovementRequest
@automatedBy BH00446 Sriram

Given MacysNet URL is launched
When user logged in as Admin user
Then MacysNet home page should be displayed
And a new link called Request Freight Movement should be displayed above Document Library on MacysNet Home Page

Examples:
|MacysNet_URL               |
|http://dev.macysnet.com/AP/| 
Scenario: Verify new link "Request Freight Movement" is not displayed above Document Library on MacysNet Home Page when logged in as Vendor user

Meta:
@id AFOJ-15-SC02
@acceptance
@automation
@regression
@automated_by
@ProductName MNET
@ModuleName FreightMovementRequest
@automatedBy BH00446 Sriram

Given MacysNet URL is launched
When user logged in as Vendor user
Then MacysNet home page should be displayed
And a new link called Request Freight Movement should not be displayed above Document Library on MacysNet Home Page

Examples:
|MacysNet_URL               |
|http://dev.macysnet.com/AP/| 