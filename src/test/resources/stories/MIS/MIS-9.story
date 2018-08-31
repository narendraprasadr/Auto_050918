MIS-9 Status of Invoices - Wire Status of Invoice page to Java Vendor Invoice Service

MIS-9 Status of Invoices - Wire Status of Invoice page to Java Vendor Invoice Service

Meta:
@issue MIS-9

Narrative:
In order to view my selected invoices based on my search criteria
As a user
I want to the invoice information to be based on Division, account # and invoice #

Scenario: Status of Invoices Information should be displaeyd based division, account# and invoice #

Meta:
@id MIS-9-SC001
@acceptance
@automatedBy BH02727_Sivanarumugam M 
@productName MacysNet
@moduleName Status of Invoices - AP forms

Given user on AP query screen to validate the <activity> for <division> division
When user enter the Account no <accountNo>, Invoice no <invoiceNo> and click on submit button
Then Get the Invoices information from UI
Then Get the Invoices information from Database
And Compare UI values with Database

Examples:
|URL                        |activity         |division|accountNo|invoiceNo |values                                       |
|http://dev.macysnet.com/AP/|Status of Invoice|Macy's  |910927390|9027307930|Document #,Status,Description,Due Date,Amount|