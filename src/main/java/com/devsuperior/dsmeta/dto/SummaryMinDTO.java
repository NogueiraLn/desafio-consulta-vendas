package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SummaryMinProjection;

public class SummaryMinDTO {

	private String sellerName;
	private Double amount;

	public SummaryMinDTO() {
	}

	public SummaryMinDTO(String sellerName, Double amount) {
		this.sellerName = sellerName;
		this.amount = amount;
	}
	
	public SummaryMinDTO(SummaryMinProjection projection) {
		sellerName = projection.getName();
		amount = projection.getAmount();
	}

	public String getSellerName() {
		return sellerName;
	}

	public Double getAmount() {
		return amount;
	}

}
