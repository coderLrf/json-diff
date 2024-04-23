package com.lrf.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author qinzj
 */
public class ContractMarketRuleResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long contractId;

    private Integer activityType;
    
    private String userGroup;

    private String activityWay;

    private String activitySupport;

    private String remark;

    private Integer sort;

    private List<ContractRuleSiteResponseDto> sites;

    private List<ContractRuleMaterialResponseDto> materialList;

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

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getActivityWay() {
        return activityWay;
    }

    public void setActivityWay(String activityWay) {
        this.activityWay = activityWay;
    }

    public String getActivitySupport() {
        return activitySupport;
    }

    public void setActivitySupport(String activitySupport) {
        this.activitySupport = activitySupport;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<ContractRuleSiteResponseDto> getSites() {
        return sites;
    }

    public void setSites(List<ContractRuleSiteResponseDto> sites) {
        this.sites = sites;
    }

    public List<ContractRuleMaterialResponseDto> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<ContractRuleMaterialResponseDto> materialList) {
        this.materialList = materialList;
    }
}