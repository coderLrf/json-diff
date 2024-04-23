package com.lrf.dto;

import java.io.Serializable;

/**
 * @author qinzj
 */
public class ContractRuleMaterialResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long ruleId;
    
    private Long materialId;
    
    private Long materialNo;

    private String materialName;

    private Long stockSpecId;
    
    private String stockSpecName;

    private Long unitId;

    private String unitName;

    private Long brandId;

    private String brandName;

    private Long categoryId;

    private String categoryNames;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(Long materialNo) {
        this.materialNo = materialNo;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Long getStockSpecId() {
        return stockSpecId;
    }

    public void setStockSpecId(Long stockSpecId) {
        this.stockSpecId = stockSpecId;
    }

    public String getStockSpecName() {
        return stockSpecName;
    }

    public void setStockSpecName(String stockSpecName) {
        this.stockSpecName = stockSpecName;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(String categoryNames) {
        this.categoryNames = categoryNames;
    }
}