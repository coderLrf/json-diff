package com.lrf.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author qinzj
 */
public class ContractMarketContentResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer settlementWay;

    private Integer settlementWayDay;

    private Integer deliverWay;

    private Integer paymentWay;

    private List<ContractMarketRuleResponseDto> rules;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSettlementWay() {
        return settlementWay;
    }

    public void setSettlementWay(Integer settlementWay) {
        this.settlementWay = settlementWay;
    }

    public Integer getSettlementWayDay() {
        return settlementWayDay;
    }

    public void setSettlementWayDay(Integer settlementWayDay) {
        this.settlementWayDay = settlementWayDay;
    }

    public Integer getDeliverWay() {
        return deliverWay;
    }

    public void setDeliverWay(Integer deliverWay) {
        this.deliverWay = deliverWay;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public List<ContractMarketRuleResponseDto> getRules() {
        return rules;
    }

    public void setRules(List<ContractMarketRuleResponseDto> rules) {
        this.rules = rules;
    }
}