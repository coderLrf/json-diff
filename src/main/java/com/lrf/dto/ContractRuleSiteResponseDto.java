package com.lrf.dto;

import java.io.Serializable;

/**
 * @author qinzj
 */
public class ContractRuleSiteResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long contractId;
    private Long ruleId;
    private Long siteId;
    private String siteNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(String siteNo) {
        this.siteNo = siteNo;
    }
}