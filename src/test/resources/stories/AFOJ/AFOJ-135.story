AFOJ-135 Create and define labels for DC2DC SRR

Meta:
@issue AFOJ-135

Narrative:

In order to create a DC2DC shipment
As a MTO colleague managing shipping
I want to the labels created and defined for the new DC2DC SRR on the static table SPCL_RTE_RQST_LBL

Scenario:validate new lbl_nbr have maximum 5 for SPCL_RTE_NBR 200 in data base

Meta:
@acceptance
@id AFOJ-135-SC001
@productName Macy'sNET
@moduleName Data Base Validation DC To DC
@automatedBy BH00446_Sriram

Given SPCL_RTE_RQST_LBL table exist in oracle data base
Then validate new max(lbl_nbr) have maximum 5 for SPCL_RTE_NBR 200 is added in SPCL_RTE_RQST_LBL oracle table
Then Validate <DriverNote> is diplayed <FB_NUMBER> in USER_TEXT column in SPCL_RTE_RQST_LBL oracle table

Examples:
|FB_NUMBER|DriverNote    																												                                                                                            |
|10000964 |Hey Dave, lets stop at the coffee shop at 6th and Waverly before picking up the shipment.  They have a really good breakfast special!!|

Scenario:validate new lbl_nbr have maximum 5 for SPCL_RTE_NBR 200 in data base

Meta:
@acceptance
@id AFOJ-135-SC002
@productName Macy'sNET
@moduleName Data Base Validation DC To DC
@automatedBy BH00446_Sriram

Given SPCL_RTE_RQST_LBL table exist in oracle data base
Then validate new max(lbl_nbr) have maximum 5 for SPCL_RTE_NBR 200 is added in SPCL_RTE_RQST_LBL oracle table
Then Validate <DriverNote> is diplayed <FB_NUMBER> in USER_TEXT column in SPCL_RTE_RQST_LBL oracle table

Examples:
|FB_NUMBER|DriverNote         |
|10000999 |Hey Dave, lets stop|


Scenario:validate new lbl_nbr have maximum 5 for SPCL_RTE_NBR 200 in data base

Meta:
@acceptance
@id AFOJ-135-SC003
@productName Macy'sNET
@moduleName Data Base Validation DC To DC
@automatedBy BH00446_Sriram

Given SPCL_RTE_RQST_LBL table exist in oracle data base
Then validate new max(lbl_nbr) have maximum 5 for SPCL_RTE_NBR 200 is added in SPCL_RTE_RQST_LBL oracle table
Then Validate <DriverNote> is diplayed <FB_NUMBER> in USER_TEXT column in SPCL_RTE_RQST_LBL oracle table

Examples:
|FB_NUMBER|DriverNote|
|10001000 |          |
