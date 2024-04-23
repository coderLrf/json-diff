package com.lrf.dto;


import com.lrf.annotation.CompareField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangzhili
 */
public class ContractResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @CompareField(isIgnoreMarker = true)
    private String contractSn;
    private String contractNo;
    private String contractTmplNo;
    private String partyaName;
    private String partybName;
    private Integer partaIsSupplier;
    private Date contractTime;
    private Date effectiveTime;
    private Date dueTime;
    private String approvalNo;
    private Integer status;
    private Integer contractNumType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractSn() {
        return contractSn;
    }

    public void setContractSn(String contractSn) {
        this.contractSn = contractSn;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractTmplNo() {
        return contractTmplNo;
    }

    public void setContractTmplNo(String contractTmplNo) {
        this.contractTmplNo = contractTmplNo;
    }

    public String getPartyaName() {
        return partyaName;
    }

    public void setPartyaName(String partyaName) {
        this.partyaName = partyaName;
    }

    public String getPartybName() {
        return partybName;
    }

    public void setPartybName(String partybName) {
        this.partybName = partybName;
    }

    public Integer getPartaIsSupplier() {
        return partaIsSupplier;
    }

    public void setPartaIsSupplier(Integer partaIsSupplier) {
        this.partaIsSupplier = partaIsSupplier;
    }

    public Date getContractTime() {
        return contractTime;
    }

    public void setContractTime(Date contractTime) {
        this.contractTime = contractTime;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getContractNumType() {
        return contractNumType;
    }

    public void setContractNumType(Integer contractNumType) {
        this.contractNumType = contractNumType;
    }
}