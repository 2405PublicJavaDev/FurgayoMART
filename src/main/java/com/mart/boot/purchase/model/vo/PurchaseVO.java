package com.mart.boot.purchase.model.vo;

import java.time.LocalDateTime;

public class PurchaseVO {
	private int purchaseNo;
	private int pNo;
	private Long memberNo;
	private int quantity;
	private int totalAmount;
	private String paymentMethod;
	private String purchaseStatus;
	private LocalDateTime purchaseDate;
	
	public int getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPurchaseStatus() {
		return purchaseStatus;
	}
	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}
	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	@Override
	public String toString() {
		return "PurchaseVO [purchaseNo=" + purchaseNo + ", pNo=" + pNo + ", memberNo=" + memberNo + ", quantity="
				+ quantity + ", totalAmount=" + totalAmount + ", paymentMethod=" + paymentMethod + ", purchaseStatus="
				+ purchaseStatus + ", purchaseDate=" + purchaseDate + "]";
	}
}
