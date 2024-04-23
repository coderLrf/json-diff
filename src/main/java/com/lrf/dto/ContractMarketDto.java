package com.lrf.dto;

import java.util.List;

/**
 * @author qinzj
 */
public class ContractMarketDto {
    private static final long serialVersionUID = 1L;
    
    private ContractResponseDto contract;

    private ContractMarketContentResponseDto content;

    private List<ContractFileResponseDto> files;

    public ContractResponseDto getContract() {
        return contract;
    }

    public void setContract(ContractResponseDto contract) {
        this.contract = contract;
    }

    public ContractMarketContentResponseDto getContent() {
        return content;
    }

    public void setContent(ContractMarketContentResponseDto content) {
        this.content = content;
    }

    public List<ContractFileResponseDto> getFiles() {
        return files;
    }

    public void setFiles(List<ContractFileResponseDto> files) {
        this.files = files;
    }
}