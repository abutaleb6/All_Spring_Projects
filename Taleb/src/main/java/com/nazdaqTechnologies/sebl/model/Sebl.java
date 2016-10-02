package com.nazdaqTechnologies.sebl.model;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sebl {
	private static Logger log = LoggerFactory.getLogger(Sebl.class);

	private static Map<String, String> sql2BeanMap = null;
	private static Map<String, String> rs2BeanMap = null;

	private String fromDate;
	private String toDate;
	
	private String date;
	private String bank;
	private Integer refNo;
	private String statusLocal;
	private String statusDhaka;
	private String customerName;
	private BigDecimal amountBdt;
	private BigDecimal amountFcy;
	private BigDecimal totalDue;	
	private String customerAc;
	private String paymentMethod;
	private String notes;
	private boolean paid;
	
	

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Integer getRefNo() {
		return refNo;
	}

	public void setRefNo(Integer refNo) {
		this.refNo = refNo;
	}

	public String getStatusLocal() {
		return statusLocal;
	}

	public void setStatusLocal(String statusLocal) {
		this.statusLocal = statusLocal;
	}

	public String getStatusDhaka() {
		return statusDhaka;
	}

	public void setStatusDhaka(String statusDhaka) {
		this.statusDhaka = statusDhaka;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getAmountBdt() {
		return amountBdt;
	}

	public void setAmountBdt(BigDecimal amountBdt) {
		this.amountBdt = amountBdt;
	}

	public BigDecimal getAmountFcy() {
		return amountFcy;
	}

	public void setAmountFcy(BigDecimal amountFcy) {
		this.amountFcy = amountFcy;
	}

	public BigDecimal getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(BigDecimal totalDue) {
		this.totalDue = totalDue;
	}

	public String getCustomerAc() {
		return customerAc;
	}

	public void setCustomerAc(String customerAc) {
		this.customerAc = customerAc;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public static final Map<String, String> getSql2BeanMap() {

		if (sql2BeanMap == null) {
			sql2BeanMap = new LinkedHashMap<String, String>();
			
			sql2BeanMap.put("@dt_order_from", "fromDate");
			sql2BeanMap.put("@dt_order_to", "toDate");
		}

		return sql2BeanMap;
	}

	public static final Map<String, String> getRs2BeanMap() {

		if (rs2BeanMap == null) {
			rs2BeanMap = new LinkedHashMap<String, String>();
			
			rs2BeanMap.put("dt_date", "date");
			rs2BeanMap.put("tx_bank", "bank");
			rs2BeanMap.put("tx_ref_no", "refNo");
			rs2BeanMap.put("tx_status_local", "statusLocal");
			rs2BeanMap.put("tx_status_dhaka", "statusDhaka");
			rs2BeanMap.put("tx_customer_name", "customerName");
			rs2BeanMap.put("tx_amount_bdt", "amountBdt");
			rs2BeanMap.put("tx_amount_fcy", "amountFcy");
			rs2BeanMap.put("tx_total_due", "totalDue");
			rs2BeanMap.put("tx_customer_ac", "customerAc");
			rs2BeanMap.put("tx_payment_method", "paymentMethod");
			rs2BeanMap.put("tx_notes", "notes");
			rs2BeanMap.put("tx_paid", "paid");

		}

		return rs2BeanMap;
	}

}
