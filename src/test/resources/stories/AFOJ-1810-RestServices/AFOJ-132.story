Meta:
@issue AFOJ-132

Narrative:

In order to create a DC2DC shipment
As a MTO colleague managing shipping
I want to new SRR created on the static table SPCL_RTE_RQST

Scenario:validate new SPCL_RTE_NBR 200 is added in SPCL_RTE_RQST table for Outbound DC to DC shipment

Meta:
@acceptance
@id AFOJ-132-SC001
@productName Macy'sNET
@moduleName Data Base Validation DC To DC
@automatedBy BH00446_Sriram

Given SPCL_RTE_RQST table exist in oracle data base
Then validate new SPCL_DESC column is contains Outbound DC to DC for SPCL_RTE_NBR 200 is added in SPCL_RTE_RQST oracle table
Examples:
|SPCL_RTE_RQST    														 |
|select SPCL_DESC,SPCL_RTE_NBR from SPCL_RTE_RQST where SPCL_RTE_NBR=200 |
